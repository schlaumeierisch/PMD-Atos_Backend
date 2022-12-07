package nl.hva.backend.application

import nl.hva.backend.application.api.CalendarService
import nl.hva.backend.application.dto.AppointmentDTO
import nl.hva.backend.domain.Appointment
import nl.hva.backend.domain.api.CalendarRepository
import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CalendarServiceImpl : CalendarService {

    @Autowired
    private lateinit var calendarRepository: CalendarRepository

    @Transactional
    override fun createAppointment(
        time: LocalDateTime,
        reason: String,
        patientId: String,
        gpId: String?,
        cpId: String?
    ) {
        val appointmentId: AppointmentId = this.calendarRepository.nextIdentity()
        var generalPractitionerId = GeneralPractitionerId("")
        var careProviderId = CareProviderId("")

        if (gpId != null && cpId == null) {
            generalPractitionerId = GeneralPractitionerId(gpId)
            careProviderId = CareProviderId("")
        } else if (gpId == null && cpId != null) {
            generalPractitionerId = GeneralPractitionerId("")
            careProviderId = CareProviderId(cpId)
        }

        val appointment = Appointment(
            appointmentId, time, reason, PatientId(patientId), generalPractitionerId, careProviderId
        )

        this.calendarRepository.createAppointment(appointment)
    }

    @Transactional
    override fun getAllAppointmentsByPatientId(patientId: PatientId): List<AppointmentDTO> {
        val appointments: List<Appointment> = this.calendarRepository.getAllAppointmentsByPatientId(patientId)

        return AppointmentDTO.fromAppointments(appointments)
    }

    @Transactional
    override fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<AppointmentDTO> {
        val appointments: List<Appointment> = this.calendarRepository.getAllAppointmentsByGeneralPractitionerId(generalPractitionerId)

        return AppointmentDTO.fromAppointments(appointments)
    }

    @Transactional
    override fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<AppointmentDTO> {
        val appointments: List<Appointment> = this.calendarRepository.getAllAppointmentsByCareProviderId(careProviderId)

        return AppointmentDTO.fromAppointments(appointments)
    }

}