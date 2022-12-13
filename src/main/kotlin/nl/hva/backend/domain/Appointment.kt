package nl.hva.backend.domain

import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import java.time.LocalDate
import java.time.LocalTime

open class Appointment {
    private val id: Long = 0
    private lateinit var domainId: AppointmentId

    private lateinit var date: LocalDate
    private lateinit var time: LocalTime
    private lateinit var reason: String

    // one-to-one
    private var patientDomainId: PatientId = PatientId("")

    // one-to-one - can be either GP or CP (not both!)
    private var gpDomainId: GeneralPractitionerId = GeneralPractitionerId("")
    private var cpDomainId: CareProviderId = CareProviderId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: AppointmentId,
        date: LocalDate,
        time: LocalTime,
        reason: String,
        patientDomainId: PatientId,
        gpDomainId: GeneralPractitionerId,
        cpDomainId: CareProviderId
    ) {
        this.domainId = domainId
        this.date = date
        this.time = time
        this.reason = reason
        this.patientDomainId = patientDomainId
        this.gpDomainId = gpDomainId
        this.cpDomainId = cpDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): AppointmentId = this.domainId
    fun date(): LocalDate = this.date
    fun time(): LocalTime = this.time
    fun reason(): String = this.reason
    fun patientDomainId(): PatientId = this.patientDomainId
    fun gpDomainId(): GeneralPractitionerId = this.gpDomainId
    fun cpDomainId(): CareProviderId = this.cpDomainId
}