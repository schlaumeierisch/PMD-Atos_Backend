package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.MedicalRecord
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class MedicalRecordDTO {
    private lateinit var id: String

    // one-to-one
    private var patientId: String = ""

    companion object {
        fun fromMedicalRecord(medicalRecord: MedicalRecord): MedicalRecordDTO {
            val medicalRecordDTO = MedicalRecordDTO()

            medicalRecordDTO.id = medicalRecord.domainId().id()

            medicalRecordDTO.patientId = medicalRecord.patientDomainId().id()

            return medicalRecordDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun patientId(): String = this.patientId

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: MedicalRecordDTO = MedicalRecordDTO()

        fun withId(id: String): Builder {
            instance.id = id
            return this
        }

        fun withPatientId(patientId: String): Builder {
            instance.patientId = patientId
            return this
        }

        fun build(): MedicalRecordDTO {
            Objects.requireNonNull(instance.patientId, "patientId must be set in MedicalRecordDTO")

            return instance
        }
    }
}