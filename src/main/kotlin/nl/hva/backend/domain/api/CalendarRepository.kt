package nl.hva.backend.domain.api

import nl.hva.backend.domain.Appointment
import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId

interface CalendarRepository {

    fun nextIdentity(): AppointmentId

    fun createAppointment(appointment: Appointment)

    fun getAllAppointmentsByPatientId(patientId: PatientId): List<Appointment>

    fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<Appointment>

    fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<Appointment>

}