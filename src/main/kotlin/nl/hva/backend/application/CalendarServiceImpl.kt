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
import java.sql.Time
import java.time.LocalDate

@Service
class CalendarServiceImpl : CalendarService {

    @Autowired
    private lateinit var calendarRepository: CalendarRepository

    @Transactional
    override fun createAppointment(
        date: LocalDate,
        time: Time,
        reason: String,
        patientId: String,
        gpId: String,
        cpId: String
    ) {
        val appointmentId: AppointmentId = this.calendarRepository.nextIdentity()

        val appointment = Appointment(
            appointmentId, date, time, reason, PatientId(patientId), GeneralPractitionerId(gpId), CareProviderId(cpId)
        )

        this.calendarRepository.createAppointment(appointment)
    }

    @Transactional
    override fun getAllAppointmentsByPatientId(patientId: PatientId): List<AppointmentDTO> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<AppointmentDTO> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<AppointmentDTO> {
        TODO("Not yet implemented")
    }

}