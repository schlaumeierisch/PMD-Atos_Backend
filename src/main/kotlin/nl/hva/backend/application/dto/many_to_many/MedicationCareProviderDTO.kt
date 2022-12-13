package nl.hva.backend.application.dto.many_to_many

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class MedicationCareProviderDTO {
    private lateinit var cpId: String
    private lateinit var medId: String
    private lateinit var validDate: LocalDate


    companion object {
        fun fromMedicationCareProviderRelation(medicationCareProviderRelation: MedicationCareProviderRelation): MedicationCareProviderDTO {
            val medicationCareProviderDTO = MedicationCareProviderDTO()

            medicationCareProviderDTO.medId = medicationCareProviderRelation.medicationId().id()
            medicationCareProviderDTO.cpId = medicationCareProviderRelation.cpDomainId().id()
            medicationCareProviderDTO.validDate = medicationCareProviderRelation.validDate()

            return medicationCareProviderDTO
        }

        fun fromMedicationCareProviderRelations(medicationCareProviderRelations: List<MedicationCareProviderRelation>): List<MedicationCareProviderDTO> {
            val medicationCareProviderDTOs: ArrayList<MedicationCareProviderDTO> = arrayListOf()

            for (medicationCareProvider in medicationCareProviderRelations) {
                medicationCareProviderDTOs.add(fromMedicationCareProviderRelation(medicationCareProvider))
            }

            return medicationCareProviderDTOs
        }
    }

    //Getters
    fun cpId(): String = this.cpId
    fun medId(): String = this.medId
    fun validDate(): LocalDate = this.validDate

    
}