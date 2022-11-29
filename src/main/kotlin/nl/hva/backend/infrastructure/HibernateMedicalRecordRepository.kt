package nl.hva.backend.infrastructure

import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.MedicalRecordId
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class HibernateMedicalRecordRepository : MedicalRecordRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): MedicalRecordId = MedicalRecordId(UUID.randomUUID().toString())

    override fun createMedicalRecord(medicalRecord: MedicalRecord) {
        this.entityManager.persist(medicalRecord)
    }

}