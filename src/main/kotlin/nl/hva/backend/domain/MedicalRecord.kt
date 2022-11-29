package nl.hva.backend.domain

import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId

open class MedicalRecord {
    private val id: Long = 0
    private lateinit var domainId: MedicalRecordId

    // one-to-one
    private var patientDomainId: PatientId = PatientId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: MedicalRecordId,
        patientDomainId: PatientId
    ) {
        this.domainId = domainId
        this.patientDomainId = patientDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): MedicalRecordId = this.domainId
    fun patientDomainId(): PatientId = this.patientDomainId
}