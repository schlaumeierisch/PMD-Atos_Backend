package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Exercise
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ExerciseDTO {
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var startDate: LocalDate
    private var endDate: LocalDate? = null

    // one-to-one
    private lateinit var medicalRecordId: String

    companion object {
        fun fromExercise(exercise: Exercise): ExerciseDTO {
            val exerciseDTO = ExerciseDTO()

            exerciseDTO.id = exercise.domainId().id()
            exerciseDTO.title = exercise.title()
            exerciseDTO.description = exercise.description()
            exerciseDTO.startDate = exercise.startDate()
            exerciseDTO.endDate = exercise.endDate()

            exerciseDTO.medicalRecordId = exercise.medicalRecordDomainId().id()

            return exerciseDTO
        }

        fun fromExercises(exercise: List<Exercise>): List<ExerciseDTO> {
            val exerciseDTOs: ArrayList<ExerciseDTO> = arrayListOf()

            for (med in exercise) {
                exerciseDTOs.add(fromExercise(med))
            }

            return exerciseDTOs
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