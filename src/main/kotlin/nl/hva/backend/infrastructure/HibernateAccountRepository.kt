package nl.hva.backend.infrastructure

import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.AccountRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.many_to_many.PatientCareProviderRelation
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
class HibernateAccountRepository : AccountRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun getAllGeneralPractitioners(): List<GeneralPractitioner> {
        val query: TypedQuery<GeneralPractitioner> = this.entityManager.createQuery(
            "SELECT gp FROM GeneralPractitioner gp", GeneralPractitioner::class.java
        )
        return query.resultList
    }

    override fun getGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<GeneralPractitioner> {
        val query: TypedQuery<GeneralPractitioner> = this.entityManager.createQuery(
            "SELECT gp FROM GeneralPractitioner gp WHERE gp.domainId = ?1", GeneralPractitioner::class.java
        )
        return query.setParameter(1, generalPractitionerId).resultList
    }

    override fun getGeneralPractitionerByEmail(email: String): List<GeneralPractitioner> {
        val query: TypedQuery<GeneralPractitioner> = this.entityManager.createQuery(
            "SELECT gp FROM GeneralPractitioner gp WHERE gp.email = ?1", GeneralPractitioner::class.java
        )
        return query.setParameter(1, email).resultList
    }

    override fun getPatientsOfGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<Patient> {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.gpDomainId = ?1", Patient::class.java
        )
        return query.setParameter(1, generalPractitionerId).resultList
    }

    override fun getAllPatients(): List<Patient> {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p", Patient::class.java
        )
        return query.resultList
    }

    override fun getPatientById(patientId: PatientId): List<Patient> {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.domainId = ?1", Patient::class.java
        )
        return query.setParameter(1, patientId).resultList
    }

    override fun getPatientByEmail(email: String): List<Patient> {
        val query: TypedQuery<Patient> = this.entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.email = ?1", Patient::class.java
        )
        return query.setParameter(1, email).resultList
    }

    override fun getPatientCareProviderRelationsByPatientId(patientId: PatientId): List<PatientCareProviderRelation> {
        val query: TypedQuery<PatientCareProviderRelation> = this.entityManager.createQuery(
            "SELECT pcp FROM PatientCareProviderRelation pcp WHERE pcp.patientDomainId = ?1", PatientCareProviderRelation::class.java
        )
        return query.setParameter(1, patientId).resultList
    }

    override fun createPatientCareProviderRelation(patientCareProviderRelation: PatientCareProviderRelation) {
        this.entityManager.persist(patientCareProviderRelation)
    }

    override fun getAllCareProviders(): List<CareProvider> {
        val query: TypedQuery<CareProvider> = this.entityManager.createQuery(
            "SELECT cp FROM CareProvider cp", CareProvider::class.java
        )
        return query.resultList
    }

    override fun getCareProviderById(careProviderId: CareProviderId): List<CareProvider> {
        val query: TypedQuery<CareProvider> = this.entityManager.createQuery(
            "SELECT cp FROM CareProvider cp WHERE cp.domainId = ?1", CareProvider::class.java
        )
        return query.setParameter(1, careProviderId).resultList
    }

    override fun getCareProviderByEmail(email: String): List<CareProvider> {
        val query: TypedQuery<CareProvider> = this.entityManager.createQuery(
            "SELECT cp FROM CareProvider cp WHERE cp.email = ?1", CareProvider::class.java
        )
        return query.setParameter(1, email).resultList
    }

}