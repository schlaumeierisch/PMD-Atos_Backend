package nl.hva.backend.application

import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.NoteCareProviderDTO
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Note
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.api.PermissionRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import nl.hva.backend.domain.many_to_many.NoteCareProviderRelation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PermissionsServiceImpl : PermissionService {

    @Autowired
    private lateinit var permissionsRepository: PermissionRepository

    /**
     ********************************** Medication **********************************
     */

    @Transactional
    override fun getMedicationCareProviderRelationById(
        careProviderId: CareProviderId
    ): List<MedicationCareProviderDTO> {
        val medicationCareProviderRelations: List<MedicationCareProviderRelation> =
            this.permissionsRepository.getMedicationCareProviderRelationById(careProviderId)
        return MedicationCareProviderDTO.fromMedicationCareProviderRelations(medicationCareProviderRelations)
    }

    @Transactional
    override fun getMedicationByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): MedicationDTO {
        val medication: Medication =
            this.permissionsRepository.getMedicationOfPatientByIdAndMr(medicationId, medicalRecordId)

        return MedicationDTO.fromMedication(medication)
    }

    @Transactional
    override fun createPermissionLinkMedication(
        medicationId: MedicationId,
        careProviderId: CareProviderId,
        validDate: LocalDate
    ) {
        this.permissionsRepository.createPermissionLinkMedication(MedicationCareProviderRelation (careProviderId, medicationId, validDate))
    }

    @Transactional
    override fun removeExpiredMedicationPermissions(currentDay: LocalDate) {
        this.permissionsRepository.removeExpiredMedicationPermissions(currentDay)
    }

    /**
     ********************************** Notes **********************************
     */

    @Transactional
    override fun getNoteCareProviderRelationById(careProviderId: CareProviderId): List<NoteCareProviderDTO> {
        val noteCareProviderRelations: List<NoteCareProviderRelation> =
            this.permissionsRepository.getNoteCareProviderRelationById(careProviderId)
        return NoteCareProviderDTO.fromNoteCareProviderRelations(noteCareProviderRelations)
    }

    @Transactional
    override fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): NoteDTO {
        val note: Note =
            this.permissionsRepository.getNoteByIdAndMr(noteId, medicalRecordId)

        return NoteDTO.fromNote(note)
    }

    override fun createPermissionLinkNote(noteId: NoteId, careProviderId: CareProviderId, validDate: LocalDate) {
        this.permissionsRepository.createPermissionLinkNote(NoteCareProviderRelation (careProviderId, noteId, validDate))
    }


}