package nl.hva.backend.application.dto.many_to_many

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.many_to_many.ExerciseCareProviderRelation
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ExerciseCareProviderDTO {
    private lateinit var cpId: String
    private lateinit var exerId: String
    private lateinit var validDate: LocalDate


    companion object {
        fun fromExerciseCareProviderRelation(exerciseCareProviderRelation: ExerciseCareProviderRelation): ExerciseCareProviderDTO {
            val medicationCareProviderDTO = ExerciseCareProviderDTO()

            medicationCareProviderDTO.exerId = exerciseCareProviderRelation.exerciseId().id()
            medicationCareProviderDTO.cpId = exerciseCareProviderRelation.cpDomainId().id()
            medicationCareProviderDTO.validDate = exerciseCareProviderRelation.validDate()

            return medicationCareProviderDTO
        }

        fun fromExerciseCareProviderRelations(exerciseCareProviderRelation: List<ExerciseCareProviderRelation>): List<ExerciseCareProviderDTO> {
            val exerciseCareProviderDTOs: ArrayList<ExerciseCareProviderDTO> = arrayListOf()

            for (exerciseCareProvider in exerciseCareProviderRelation) {
                exerciseCareProviderDTOs.add(fromExerciseCareProviderRelation(exerciseCareProvider))
            }

            return exerciseCareProviderDTOs
        }
    }

    //Getters
    fun cpId(): String = this.cpId
    fun exerId(): String = this.exerId
    fun validDate(): LocalDate = this.validDate

    
}