package nl.hva.backend.application.dto.many_to_many

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.many_to_many.PatientCareProviderRelation

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class PatientCareProviderRelationDTO {
    private lateinit var patientId: String
    private lateinit var cpId: String

    companion object {
        fun fromPatientCareProviderRelation(patientCareProviderRelation: PatientCareProviderRelation): PatientCareProviderRelationDTO {
            val patientCareProviderRelationDTO = PatientCareProviderRelationDTO()

            patientCareProviderRelationDTO.patientId = patientCareProviderRelation.patientDomainId().id()
            patientCareProviderRelationDTO.cpId = patientCareProviderRelation.cpDomainId().id()

            return patientCareProviderRelationDTO
        }

        fun fromPatientCareProviderRelations(patientCareProviderRelations: List<PatientCareProviderRelation>): List<PatientCareProviderRelationDTO> {
            val patientCareProviderRelationDTOS: ArrayList<PatientCareProviderRelationDTO> = arrayListOf()

            for (patientCareProvider in patientCareProviderRelations) {
                patientCareProviderRelationDTOS.add(fromPatientCareProviderRelation(patientCareProvider))
            }

            return patientCareProviderRelationDTOS
        }
    }

    // getter
    fun patientId(): String = this.patientId
    fun cpId(): String = this.cpId
}