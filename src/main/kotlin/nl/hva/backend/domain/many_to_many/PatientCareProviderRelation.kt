package nl.hva.backend.domain.many_to_many

import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.PatientId

open class PatientCareProviderRelation {
    private val id: Long = 0

    private lateinit var patientDomainId: PatientId
    private lateinit var cpDomainId: CareProviderId

    // required by hibernate
    protected constructor()

    constructor(
        patientDomainId: PatientId,
        cpDomainId: CareProviderId
    ) {
        this.patientDomainId = patientDomainId
        this.cpDomainId = cpDomainId
    }

    // getter
    fun id(): Long = this.id
    fun patientDomainId(): PatientId = this.patientDomainId
    fun cpDomainId(): CareProviderId = this.cpDomainId
}