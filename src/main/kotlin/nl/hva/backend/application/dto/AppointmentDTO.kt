package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Appointment
import java.time.LocalDateTime
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class AppointmentDTO {
    private lateinit var id: String
    private lateinit var time: LocalDateTime
    private lateinit var reason: String

    // one-to-one - can be either GP or CP (not both!)
    private var gpId: String = ""
    private var cpId: String = ""

    companion object {
        fun fromAppointment(appointment: Appointment): AppointmentDTO {
            val appointmentDTO = AppointmentDTO()

            appointmentDTO.id = appointment.domainId().id()
            appointmentDTO.time = appointment.time()
            appointmentDTO.reason = appointment.reason()

            appointmentDTO.gpId = appointment.gpDomainId().id()
            appointmentDTO.cpId = appointment.cpDomainId().id()

            return appointmentDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun time(): LocalDateTime = this.time
    fun reason(): String = this.reason
    fun gpId(): String = this.gpId
    fun cpId(): String = this.cpId

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: AppointmentDTO = AppointmentDTO()

        fun withId(id: String): Builder {
            instance.id = id
            return this
        }

        fun withTime(time: LocalDateTime): Builder {
            instance.time = time
            return this
        }

        fun withReason(reason: String): Builder {
            instance.reason = reason
            return this
        }

        fun withGpId(gpId: String): Builder {
            instance.gpId = gpId
            return this
        }

        fun withCpId(cpId: String): Builder {
            instance.cpId = cpId
            return this
        }

        fun build(): AppointmentDTO {
            Objects.requireNonNull(instance.time, "time must be set in AppointmentDTO")
            Objects.requireNonNull(instance.reason, "reason must be set in AppointmentDTO")
            Objects.requireNonNull(instance.gpId, "gpId must be set in AppointmentDTO")
            Objects.requireNonNull(instance.cpId, "cpId must be set in AppointmentDTO")

            return instance
        }
    }
}