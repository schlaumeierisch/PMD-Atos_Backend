package nl.hva.backend.infrastructure

import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.Observation
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.MedicalRecordId
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Component
class HibernateMedicalRecordRepository : MedicalRecordRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): MedicalRecordId = MedicalRecordId(UUID.randomUUID().toString())

    override fun createMedicalRecord(medicalRecord: MedicalRecord) {
        this.entityManager.persist(medicalRecord)
    }

    override fun getAllObservationsByMedicalRecordId(medicalRecordId: MedicalRecordId): List<Observation> {
        val query: TypedQuery<Observation> = this.entityManager.createQuery(
            "SELECT obs FROM Observation obs WHERE obs.medicalRecordDomainId = ?1", Observation::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

}