package nl.hva.backend.domain

import nl.hva.backend.domain.ids.MedicalRecordId

open class MedicalRecord {
    private val id: Long = 0
    private lateinit var domainId: MedicalRecordId

    // required by hibernate
    protected constructor()

    constructor(
        domainId: MedicalRecordId
    ) {
        this.domainId = domainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): MedicalRecordId = this.domainId
}