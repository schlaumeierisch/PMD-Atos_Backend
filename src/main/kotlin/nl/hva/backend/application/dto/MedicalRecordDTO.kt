package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.MedicalRecord

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class MedicalRecordDTO {
    private lateinit var id: String

    companion object {
        fun fromMedicalRecord(medicalRecord: MedicalRecord): MedicalRecordDTO {
            val medicalRecordDTO = MedicalRecordDTO()

            medicalRecordDTO.id = medicalRecord.domainId().id()

            return medicalRecordDTO
        }
    }

    // getter
    fun id(): String = this.id
}