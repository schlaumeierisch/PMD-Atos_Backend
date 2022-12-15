package nl.hva.backend.application.api

import nl.hva.backend.application.dto.AppointmentDTO
import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import java.time.LocalDateTime

interface CalendarService {

    fun createAppointment(dateTime: LocalDateTime, reason: String, patientId: String, gpId: String?, cpId: String?)

    fun cancelAppointment(appointmentId: AppointmentId)

    fun getAppointmentById(appointmentId: AppointmentId): List<AppointmentDTO>

    fun getAllAppointmentsByPatientId(patientId: PatientId): List<AppointmentDTO>

    fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<AppointmentDTO>

    fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<AppointmentDTO>

}