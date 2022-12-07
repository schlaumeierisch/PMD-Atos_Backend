package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Note
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class NoteDTO {
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var date: LocalDate

    // one-to-one
    private lateinit var medicalRecordId: String

    companion object {
        fun fromNote(note: Note): NoteDTO {
            val noteDTO = NoteDTO()

            noteDTO.id = note.domainId().id()
            noteDTO.title = note.title()
            noteDTO.description = note.description()
            noteDTO.date = note.date()

            noteDTO.medicalRecordId = note.medicalRecordDomainId().id()

            return noteDTO
        }

        fun fromNotes(notes: List<Note>): List<NoteDTO> {
            val noteDTOS: ArrayList<NoteDTO> = arrayListOf()

            for (note in notes) {
                noteDTOS.add(fromNote(note))
            }

            return noteDTOS
        }
    }

    // getter
    fun id(): String = this.id
    fun title(): String = this.title
    fun description(): String = this.description
    fun date(): LocalDate = this.date
    fun medicalRecordId(): String = this.medicalRecordId
}