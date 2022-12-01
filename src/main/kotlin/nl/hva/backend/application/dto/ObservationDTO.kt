package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Observation
import java.time.LocalDate

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

        fun fromObservations(observations: List<Observation>): List<ObservationDTO> {
            val observationDTOs: ArrayList<ObservationDTO> = arrayListOf()

            for (observation in observations) {
                observationDTOs.add(fromObservation(observation))
            }

            return observationDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun title(): String = this.title
    fun description(): String = this.description
    fun date(): LocalDate = this.date
    fun medicalRecordId(): String = this.medicalRecordId
}