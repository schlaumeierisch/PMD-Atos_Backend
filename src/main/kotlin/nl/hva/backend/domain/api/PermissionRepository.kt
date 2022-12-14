package nl.hva.backend.domain.api

import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Note
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import nl.hva.backend.domain.many_to_many.NoteCareProviderRelation
import java.time.LocalDate

interface PermissionRepository {

    /**
     ********************************** Medication **********************************
     */

    fun getMedicationCareProviderRelationById(careProviderId: CareProviderId): List<MedicationCareProviderRelation>

    fun getMedicationOfPatientByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): Medication

    fun createPermissionLinkMedication(medicationCareProviderRelation: MedicationCareProviderRelation)

    fun removeExpiredMedicationPermissions(currentDay: LocalDate)

    /**
     ********************************** Notes **********************************
     */

    fun getNoteCareProviderRelationById(careProviderId: CareProviderId): List<NoteCareProviderRelation>

    fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): Note

    fun createPermissionLinkNote(noteCareProviderRelation: NoteCareProviderRelation)


}