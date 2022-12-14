package nl.hva.backend.domain.many_to_many

import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.NoteId
import java.time.LocalDate

open class NoteCareProviderRelation {
    private val id: Long = 0

    private lateinit var cpDomainId: CareProviderId
    private lateinit var noteId: NoteId
    private lateinit var validDate: LocalDate


    // required by hibernate
    protected constructor()

    constructor(
        cpDomainId: CareProviderId,
        noteId: NoteId,
        validDate: LocalDate
    ) {
        this.cpDomainId = cpDomainId
        this.noteId = noteId
        this.validDate = validDate
    }

    fun id(): Long = this.id
    fun cpDomainId(): CareProviderId = this.cpDomainId
    fun noteId(): NoteId = this.noteId
    fun validDate(): LocalDate = this.validDate


}