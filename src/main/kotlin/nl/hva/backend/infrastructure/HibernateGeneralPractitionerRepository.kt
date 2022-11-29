package nl.hva.backend.infrastructure

import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.api.GeneralPractitionerRepository
import nl.hva.backend.domain.ids.GeneralPractitionerId
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.persistence.TypedQuery

@Repository
class HibernateGeneralPractitionerRepository : GeneralPractitionerRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): GeneralPractitionerId = GeneralPractitionerId(UUID.randomUUID().toString())

    override fun createAccount(generalPractitioner: GeneralPractitioner) {
        this.entityManager.persist(generalPractitioner)
    }

    override fun editAccount(
        generalPractitionerId: GeneralPractitionerId, firstName: String, lastName: String, street: String,
        zip: String, city: String, country: String, phoneNumber: String
    ) {
        val updateQuery: Query = this.entityManager.createQuery(
            "UPDATE GeneralPractitioner gp SET gp.firstName = ?1, gp.lastName = ?2, gp.address.street = ?3, gp.address.zip = ?4, gp.address.city = ?5, gp.address.country = ?6, gp.phoneNumber = ?7 WHERE gp.domainId = ?8"
        )
            .setParameter(1, firstName)
            .setParameter(2, lastName)
            .setParameter(3, street)
            .setParameter(4, zip)
            .setParameter(5, city)
            .setParameter(6, country)
            .setParameter(7, phoneNumber)
            .setParameter(8, generalPractitionerId)

        updateQuery.executeUpdate()
    }

    override fun deleteAccount(generalPractitionerId: GeneralPractitionerId) {
        val deleteQuery: TypedQuery<GeneralPractitioner> = this.entityManager.createQuery(
            "SELECT gp FROM GeneralPractitioner gp WHERE gp.domainId = ?1", GeneralPractitioner::class.java
        )
        val result: GeneralPractitioner = deleteQuery.setParameter(1, generalPractitionerId).singleResult
        this.entityManager.remove(result)

        // delete GP reference from patient(s) as well
        val updateQuery: Query = this.entityManager.createQuery(
            "UPDATE Patient p SET p.gpDomainId = ?1 WHERE p.gpDomainId = ?2"
        )
            .setParameter(1, GeneralPractitionerId(""))
            .setParameter(2, generalPractitionerId)

        updateQuery.executeUpdate()
    }

    override fun getAccountById(generalPractitionerId: GeneralPractitionerId): GeneralPractitioner {
        val query: TypedQuery<GeneralPractitioner> = this.entityManager.createQuery(
            "SELECT gp FROM GeneralPractitioner gp WHERE gp.domainId = ?1", GeneralPractitioner::class.java
        )
        return query.setParameter(1, generalPractitionerId).singleResult
    }

    override fun getAllAccounts(): List<GeneralPractitioner> {
        val query: TypedQuery<GeneralPractitioner> = this.entityManager.createQuery(
            "SELECT gp FROM GeneralPractitioner gp", GeneralPractitioner::class.java
        )
        return query.resultList
    }

}