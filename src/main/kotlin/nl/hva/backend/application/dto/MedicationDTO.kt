package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Medication
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class MedicationDTO {
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var startDate: LocalDate
    private var endDate: LocalDate? = null

    // one-to-one
    private lateinit var medicalRecordId: String

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

        fun fromMedication(medication: List<Medication>): List<MedicationDTO> {
            val medicationDTOs: ArrayList<MedicationDTO> = arrayListOf()

            for (med in medication) {
                medicationDTOs.add(fromMedication(med))
            }

            return medicationDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun title(): String = this.title
    fun description(): String = this.description
    fun startDate(): LocalDate = this.startDate
    fun endDate(): LocalDate? = this.endDate
    fun medicalRecordId(): String = this.medicalRecordId
}