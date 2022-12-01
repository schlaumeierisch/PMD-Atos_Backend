package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Appointment
import java.sql.Time
import java.time.LocalDate
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class AppointmentDTO {
    private lateinit var id: String
    private lateinit var date: LocalDate
    private lateinit var time: Time
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
            appointmentDTO.date = appointment.date()
            appointmentDTO.time = appointment.time()
            appointmentDTO.reason = appointment.reason()

            appointmentDTO.patientId = appointment.patientDomainId().id()
            appointmentDTO.gpId = appointment.gpDomainId().id()
            appointmentDTO.cpId = appointment.cpDomainId().id()

            return appointmentDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun date(): LocalDate = this.date
    fun time(): Time = this.time
    fun reason(): String = this.reason
    fun patientId(): String = this.patientId
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

        fun withDate(date: LocalDate): Builder {
            instance.date = date
            return this
        }

        fun withTime(time: Time): Builder {
            instance.time = time
            return this
        }

        fun withReason(reason: String): Builder {
            instance.reason = reason
            return this
        }

        fun withPatientId(patientId: String): Builder {
            instance.patientId = patientId
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
            Objects.requireNonNull(instance.date, "time must be set in AppointmentDTO")
            Objects.requireNonNull(instance.time, "time must be set in AppointmentDTO")
            Objects.requireNonNull(instance.reason, "reason must be set in AppointmentDTO")
            Objects.requireNonNull(instance.patientId, "patientId must be set in AppointmentDTO")
            Objects.requireNonNull(instance.gpId, "gpId must be set in AppointmentDTO")
            Objects.requireNonNull(instance.cpId, "cpId must be set in AppointmentDTO")

            return instance
        }
    }
}