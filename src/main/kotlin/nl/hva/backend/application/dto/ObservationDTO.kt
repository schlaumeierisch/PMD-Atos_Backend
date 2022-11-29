package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Observation
import java.time.LocalDate
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ObservationDTO {
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var date: LocalDate

    // one-to-one
    private var medicalRecordId: String = ""

    companion object {
        fun fromObservation(observation: Observation): ObservationDTO {
            val observationDTO = ObservationDTO()

            observationDTO.id = observation.domainId().id()
            observationDTO.title = observation.title()
            observationDTO.description = observation.description()
            observationDTO.date = observation.date()

            observationDTO.medicalRecordId = observation.medicalRecordDomainId().id()

            return observationDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun title(): String = this.title
    fun description(): String = this.description
    fun date(): LocalDate = this.date
    fun medicalRecordId(): String = this.medicalRecordId

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: ObservationDTO = ObservationDTO()

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

        fun withDate(date: LocalDate): Builder {
            instance.date = date
            return this
        }

        fun withMedicalRecordId(medicalRecordId: String): Builder {
            instance.medicalRecordId = medicalRecordId
            return this
        }

        fun build(): ObservationDTO {
            Objects.requireNonNull(instance.title, "title must be set in ObservationDTO")
            Objects.requireNonNull(instance.description, "description must be set in ObservationDTO")
            Objects.requireNonNull(instance.date, "date must be set in ObservationDTO")
            Objects.requireNonNull(instance.medicalRecordId, "medicalRecordId must be set in ObservationDTO")

            return instance
        }
    }
}