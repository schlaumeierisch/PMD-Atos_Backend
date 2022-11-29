package nl.hva.backend.domain

import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.ObservationId
import java.time.LocalDate

open class Observation {
    private val id: Long = 0
    private lateinit var domainId: ObservationId

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var date: LocalDate

    // one-to-one
    private var medicalRecordDomainId: MedicalRecordId = MedicalRecordId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: ObservationId,
        title: String,
        description: String,
        date: LocalDate,
        medicalRecordDomainId: MedicalRecordId
    ) {
        this.domainId = domainId
        this.title = title
        this.description = description
        this.date = date
        this.medicalRecordDomainId = medicalRecordDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): ObservationId = this.domainId
    fun title(): String = this.title
    fun description(): String = this.description
    fun date(): LocalDate = this.date
    fun medicalRecordDomainId(): MedicalRecordId = this.medicalRecordDomainId
}