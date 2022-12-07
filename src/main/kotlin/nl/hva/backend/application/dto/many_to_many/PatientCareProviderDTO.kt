package nl.hva.backend.application.dto.many_to_many

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.many_to_many.PatientCareProviderRelation

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class PatientCareProviderDTO {
    private lateinit var patientId: String
    private lateinit var cpId: String

    companion object {
        fun fromPatientCareProviderRelation(patientCareProviderRelation: PatientCareProviderRelation): PatientCareProviderDTO {
            val patientCareProviderDTO = PatientCareProviderDTO()

            patientCareProviderDTO.patientId = patientCareProviderRelation.patientDomainId().id()
            patientCareProviderDTO.cpId = patientCareProviderRelation.cpDomainId().id()

            return patientCareProviderDTO
        }

        fun fromPatientCareProviderRelations(patientCareProviderRelations: List<PatientCareProviderRelation>): List<PatientCareProviderDTO> {
            val patientCareProviderDTOs: ArrayList<PatientCareProviderDTO> = arrayListOf()

            for (patientCareProvider in patientCareProviderRelations) {
                patientCareProviderDTOs.add(fromPatientCareProviderRelation(patientCareProvider))
            }

            return patientCareProviderDTOs
        }
    }

    // getter
    fun patientId(): String = this.patientId
    fun cpId(): String = this.cpId
}