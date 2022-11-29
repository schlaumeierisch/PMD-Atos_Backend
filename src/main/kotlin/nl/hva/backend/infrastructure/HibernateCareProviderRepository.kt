package nl.hva.backend.infrastructure

import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.api.CareProviderRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.value_objects.Specialism
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.persistence.TypedQuery

@Repository
class HibernateCareProviderRepository : CareProviderRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): CareProviderId = CareProviderId(UUID.randomUUID().toString())

    override fun createAccount(careProvider: CareProvider) {
        this.entityManager.persist(careProvider)
    }

    override fun editAccount(
        careProviderId: CareProviderId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String,
        specialism: Enum<Specialism>
    ) {

        val query: Query = this.entityManager.createQuery(
            "UPDATE CareProvider cp SET cp.firstName = ?1, cp.lastName = ?2, cp.address.street = ?3, cp.address.zip = ?4, cp.address.city = ?5, cp.address.country = ?6, cp.phoneNumber = ?7, cp.specialism = ?8 WHERE cp.domainId = ?9"
        )
            .setParameter(1, firstName)
            .setParameter(2, lastName)
            .setParameter(3, street)
            .setParameter(4, zip)
            .setParameter(5, city)
            .setParameter(6, country)
            .setParameter(7, phoneNumber)
            .setParameter(8, specialism)
            .setParameter(9, careProviderId)

        query.executeUpdate()
    }

    override fun deleteAccount(careProviderId: CareProviderId) {
        val query: TypedQuery<CareProvider> = this.entityManager.createQuery(
            "SELECT cp FROM CareProvider cp WHERE cp.domainId = ?1", CareProvider::class.java
        )
        val result: CareProvider = query.setParameter(1, careProviderId).singleResult
        this.entityManager.remove(result)
    }

    override fun getAccountById(careProviderId: CareProviderId): CareProvider {
        val query: TypedQuery<CareProvider> = this.entityManager.createQuery(
            "SELECT cp FROM CareProvider cp WHERE cp.domainId = ?1", CareProvider::class.java
        )
        return query.setParameter(1, careProviderId).singleResult
    }

    override fun getAllAccounts(): List<CareProvider> {
        val query: TypedQuery<CareProvider> = this.entityManager.createQuery(
            "SELECT cp FROM CareProvider cp", CareProvider::class.java
        )
        return query.resultList
    }

}