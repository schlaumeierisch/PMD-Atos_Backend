package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Exercise

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ExerciseDTO {
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var duration: Number
    private lateinit var progress: Number


    // one-to-one
    private lateinit var medicalRecordId: String

    companion object {
        fun fromExercise(exercise: Exercise): ExerciseDTO {
            val exerciseDTO = ExerciseDTO()

            exerciseDTO.id = exercise.domainId().id()
            exerciseDTO.title = exercise.title()
            exerciseDTO.description = exercise.description()
            exerciseDTO.progress = exercise.progress()
            exerciseDTO.duration = exercise.duration()

            exerciseDTO.medicalRecordId = exercise.medicalRecordDomainId().id()

            return exerciseDTO
        }

        fun fromExercises(exercises: List<Exercise>): List<ExerciseDTO> {
            val exerciseDTOs: ArrayList<ExerciseDTO> = arrayListOf()

            for (exercise in exercises) {
                exerciseDTOs.add(fromExercise(exercise))
            }

            return exerciseDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun title(): String = this.title
    fun description(): String = this.description
    fun progress(): Number = this.progress
    fun duration(): Number = this.duration
    fun medicalRecordId(): String = this.medicalRecordId
}

