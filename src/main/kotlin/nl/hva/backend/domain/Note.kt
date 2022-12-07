package nl.hva.backend.domain

import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.NoteId
import java.time.LocalDate

open class Note {
    private val id: Long = 0
    private lateinit var domainId: NoteId

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var date: LocalDate

    // one-to-one
    private var medicalRecordDomainId: MedicalRecordId = MedicalRecordId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: NoteId,
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
    fun domainId(): NoteId = this.domainId
    fun title(): String = this.title
    fun description(): String = this.description
    fun date(): LocalDate = this.date
    fun medicalRecordDomainId(): MedicalRecordId = this.medicalRecordDomainId
}