package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Appointment
import java.time.LocalDateTime

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class AppointmentDTO {
    private lateinit var id: String
    private lateinit var time: LocalDateTime
    private lateinit var reason: String

    // one-to-one
    private var patientId: String = ""

    // one-to-one - can be either GP or CP (not both!)
    private var gpId: String = ""
    private var cpId: String = ""

    companion object {
        fun fromAppointment(appointment: Appointment): AppointmentDTO {
            val appointmentDTO = AppointmentDTO()

            appointmentDTO.id = appointment.domainId().id()
            appointmentDTO.time = appointment.time()
            appointmentDTO.reason = appointment.reason()

            appointmentDTO.patientId = appointment.patientDomainId().id()
            appointmentDTO.gpId = appointment.gpDomainId().id()
            appointmentDTO.cpId = appointment.cpDomainId().id()

            return appointmentDTO
        }

        fun fromAppointments(appointments: List<Appointment>): List<AppointmentDTO> {
            val appointmentDTOs: ArrayList<AppointmentDTO> = arrayListOf()

            for (appointment in appointments) {
                appointmentDTOs.add(fromAppointment(appointment))
            }

            return appointmentDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun time(): LocalDateTime = this.time
    fun reason(): String = this.reason
    fun patientId(): String = this.patientId
    fun gpId(): String = this.gpId
    fun cpId(): String = this.cpId
}