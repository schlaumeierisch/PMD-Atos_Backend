package nl.hva.backend.infrastructure

import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.PatientRepository
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.persistence.TypedQuery

@Component
class HibernatePatientRepository : PatientRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): PatientId = PatientId(UUID.randomUUID().toString())

    override fun createAccount(patient: Patient) {
        this.entityManager.persist(patient)
    }

    override fun editAccount(
        patientId: PatientId, firstName: String, lastName: String, street: String, zip: String,
        city: String, country: String, gender: String, birthDate: LocalDate, phoneNumber: String,
        email: String, isUsingApp: Boolean
    ) {
        val query: Query = this.entityManager.createQuery(
            "UPDATE Patient p SET p.firstName = ?1, p.lastName = ?2, p.address.street = ?3, p.address.zip = ?4," +
                    "p.address.city = ?5, p.address.country = ?6, p.gender = ?7, p.birthDate = ?8, p.phoneNumber = ?9," +
                    "p.email = ?10, p.isUsingApp = ?11" +
                    "WHERE p.domainId = ?12"
        )
            .setParameter(1, firstName)
            .setParameter(2, lastName)
            .setParameter(3, street)
            .setParameter(4, zip)
            .setParameter(5, city)
            .setParameter(6, country)
            .setParameter(7, gender)
            .setParameter(8, birthDate)
            .setParameter(9, phoneNumber)
            .setParameter(10, email)
            .setParameter(11, isUsingApp)
            .setParameter(12, patientId)

        query.executeUpdate()
    }

    override fun deleteAccount(patientId: PatientId) {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.domainId = ?1", Patient::class.java
        )
        val result: Patient = query.setParameter(1, patientId).singleResult
        this.entityManager.remove(result)
    }

    override fun getAccountById(patientId: PatientId): Patient {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.domainId = ?1", Patient::class.java
        )
        return query.setParameter(1, patientId).singleResult
    }

    override fun getAccountByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<Patient> {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.gpDomainId = ?1", Patient::class.java
        )
        return query.setParameter(1, generalPractitionerId).resultList
    }

    override fun getAllAccounts(): List<Patient> {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p", Patient::class.java
        )
        return query.resultList
    }

}