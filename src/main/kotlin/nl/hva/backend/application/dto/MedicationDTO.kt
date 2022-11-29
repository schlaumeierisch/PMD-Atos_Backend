package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Medication
import java.time.LocalDate
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class MedicationDTO {
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var startDate: LocalDate
    private var endDate: LocalDate? = null

    // one-to-one
    private var medicalRecordId: String = ""

    companion object {
        fun fromMedication(medication: Medication): MedicationDTO {
            val medicationDTO = MedicationDTO()

            medicationDTO.id = medication.domainId().id()
            medicationDTO.title = medication.title()
            medicationDTO.description = medication.description()
            medicationDTO.startDate = medication.startDate()
            medicationDTO.endDate = medication.endDate()

            medicationDTO.medicalRecordId = medication.medicalRecordDomainId().id()

            return medicationDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun title(): String = this.title
    fun description(): String = this.description
    fun startDate(): LocalDate = this.startDate
    fun endDate(): LocalDate? = this.endDate
    fun medicalRecordId(): String = this.medicalRecordId

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: MedicationDTO = MedicationDTO()

        fun withId(id: String): Builder {
            instance.id = id
            return this
        }

        fun withTitle(title: String): Builder {
            instance.title = title
            return this
        }

        fun withDescription(description: String): Builder {
            instance.description = description
            return this
        }

        fun withStartDate(startDate: LocalDate): Builder {
            instance.startDate = startDate
            return this
        }

        fun withEndDate(endDate: LocalDate?): Builder {
            instance.endDate = endDate
            return this
        }

        fun withMedicalRecordId(medicalRecordId: String): Builder {
            instance.medicalRecordId = medicalRecordId
            return this
        }

        fun build(): MedicationDTO {
            Objects.requireNonNull(instance.title, "title must be set in MedicationDTO")
            Objects.requireNonNull(instance.description, "description must be set in MedicationDTO")
            Objects.requireNonNull(instance.startDate, "startDate must be set in MedicationDTO")
            Objects.requireNonNull(instance.endDate, "endDate must be set in MedicationDTO")
            Objects.requireNonNull(instance.medicalRecordId, "medicalRecordId must be set in MedicationDTO")

            return instance
        }
    }
}