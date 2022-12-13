package nl.hva.backend.application.api

import nl.hva.backend.application.dto.AppointmentDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import java.time.LocalDate
import java.time.LocalTime

interface CalendarService {

    fun createAppointment(date: LocalDate, time: LocalTime, reason: String, patientId: String, gpId: String?, cpId: String?)

    fun getAllAppointmentsByPatientId(patientId: PatientId): List<AppointmentDTO>

    fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<AppointmentDTO>

    fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<AppointmentDTO>

}