package nl.hva.backend.domain.api

import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.ids.MedicalRecordId

interface MedicalRecordRepository {

    fun nextIdentity(): MedicalRecordId

    fun createMedicalRecord(medicalRecord: MedicalRecord)

}