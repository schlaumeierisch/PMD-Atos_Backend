package nl.hva.backend.domain

import nl.hva.backend.domain.ids.*
import java.time.LocalDateTime

open class Appointment {
    private val id: Long = 0
    private lateinit var domainId: AppointmentId

    private lateinit var time: LocalDateTime
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
        time: LocalDateTime,
        reason: String,
        gpDomainId: GeneralPractitionerId
    ) {
        this.domainId = domainId
        this.time = time
        this.reason = reason
        this.gpDomainId = gpDomainId
    }

    constructor(
        domainId: AppointmentId,
        time: LocalDateTime,
        reason: String,
        cpDomainId: CareProviderId
    ) {
        this.domainId = domainId
        this.time = time
        this.reason = reason
        this.cpDomainId = cpDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): AppointmentId = this.domainId
    fun time(): LocalDateTime = this.time
    fun reason(): String = this.reason
    fun gpDomainId(): GeneralPractitionerId = this.gpDomainId
    fun cpDomainId(): CareProviderId = this.cpDomainId
}