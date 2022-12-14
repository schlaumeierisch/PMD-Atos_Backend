package nl.hva.backend.application.dto.many_to_many

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.many_to_many.NoteCareProviderRelation
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class NoteCareProviderDTO {
    private lateinit var cpId: String
    private lateinit var noteId: String
    private lateinit var validDate: LocalDate


    companion object {
        fun fromNoteCareProviderRelation(noteCareProviderRelation: NoteCareProviderRelation): NoteCareProviderDTO {
            val noteCareProviderDTO = NoteCareProviderDTO()

            noteCareProviderDTO.noteId = noteCareProviderRelation.noteId().id()
            noteCareProviderDTO.cpId = noteCareProviderRelation.cpDomainId().id()
            noteCareProviderDTO.validDate = noteCareProviderRelation.validDate()

            return noteCareProviderDTO
        }

        fun fromNoteCareProviderRelations(noteCareProviderRelations: List<NoteCareProviderRelation>): List<NoteCareProviderDTO> {
            val noteCareProviderDTOs: ArrayList<NoteCareProviderDTO> = arrayListOf()

            for (noteCareProvider in noteCareProviderRelations) {
                noteCareProviderDTOs.add(fromNoteCareProviderRelation(noteCareProvider))
            }

            return noteCareProviderDTOs
        }
    }

    //Getters
    fun cpId(): String = this.cpId
    fun noteId(): String = this.noteId
    fun validDate(): LocalDate = this.validDate

    
}