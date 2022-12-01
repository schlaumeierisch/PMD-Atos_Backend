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
import javax.persistence.TypedQuery

@Repository
class HibernateCalendarRepository : CalendarRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): AppointmentId = AppointmentId(UUID.randomUUID().toString())

    override fun createAppointment(appointment: Appointment) {
        this.entityManager.persist(appointment)
    }

    override fun getAllAppointmentsByPatientId(patientId: PatientId): List<Appointment> {
        val query: TypedQuery<Appointment> = this.entityManager.createQuery(
                "SELECT apt FROM Appointment apt WHERE apt.patientDomainId = ?1", Appointment::class.java
        )
        return query.setParameter(1, patientId).resultList
    }

    override fun getAllAppointmentsByGeneralPractitionerId(generalPractitionerId: GeneralPractitionerId): List<Appointment> {
        val query: TypedQuery<Appointment> = this.entityManager.createQuery(
            "SELECT apt FROM Appointment apt WHERE apt.gpDomainId = ?1", Appointment::class.java
        )
        return query.setParameter(1, generalPractitionerId).resultList
    }

    override fun getAllAppointmentsByCareProviderId(careProviderId: CareProviderId): List<Appointment> {
        val query: TypedQuery<Appointment> = this.entityManager.createQuery(
            "SELECT apt FROM Appointment apt WHERE apt.cpDomainId = ?1", Appointment::class.java
        )
        return query.setParameter(1, careProviderId).resultList
    }

}