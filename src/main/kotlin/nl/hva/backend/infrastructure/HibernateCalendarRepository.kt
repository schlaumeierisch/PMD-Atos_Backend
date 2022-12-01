package nl.hva.backend.infrastructure

import nl.hva.backend.domain.Appointment
import nl.hva.backend.domain.api.CalendarRepository
import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class HibernateCalendarRepository : CalendarRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): AppointmentId = AppointmentId(UUID.randomUUID().toString())

    override fun createAppointment(appointment: Appointment) {
        this.entityManager.persist(appointment)
    }

    override fun getAllAppointmentsByPatientId(patientId: PatientId): List<Appointment> {
        TODO("Not yet implemented")
    }

    override fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<Appointment> {
        TODO("Not yet implemented")
    }

    override fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<Appointment> {
        TODO("Not yet implemented")
    }

}