package nl.hva.backend.application.dto.many_to_many

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.many_to_many.DiagnosisCareProviderRelation
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class DiagnosisCareProviderDTO {
    private lateinit var cpId: String
    private lateinit var diagId: String
    private lateinit var validDate: LocalDate


    companion object {
        fun fromDiagnosisCareProviderRelation(diagnosisCareProviderRelation: DiagnosisCareProviderRelation): DiagnosisCareProviderDTO {
            val diagnosisCareProviderDTO = DiagnosisCareProviderDTO()

            diagnosisCareProviderDTO.diagId = diagnosisCareProviderRelation.diagnosisId().id()
            diagnosisCareProviderDTO.cpId = diagnosisCareProviderRelation.cpDomainId().id()
            diagnosisCareProviderDTO.validDate = diagnosisCareProviderRelation.validDate()

            return diagnosisCareProviderDTO
        }

        fun fromDiagnosisCareProviderRelations(diagnosisCareProviderRelations: List<DiagnosisCareProviderRelation>): List<DiagnosisCareProviderDTO> {
            val diagnosisCareProviderDTOs: ArrayList<DiagnosisCareProviderDTO> = arrayListOf()

            for (diagnosisCareProvider in diagnosisCareProviderRelations) {
                diagnosisCareProviderDTOs.add(fromDiagnosisCareProviderRelation(diagnosisCareProvider))
            }

            return diagnosisCareProviderDTOs
        }
    }

    //Getters
    fun cpId(): String = this.cpId
    fun diagId(): String = this.diagId
    fun validDate(): LocalDate = this.validDate

    
}