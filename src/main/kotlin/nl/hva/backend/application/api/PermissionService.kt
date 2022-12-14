package nl.hva.backend.application.api

import nl.hva.backend.application.dto.DiagnosisDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.application.dto.many_to_many.DiagnosisCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.NoteCareProviderDTO
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import java.time.LocalDate

interface PermissionService {

    /**
     ********************************** Medication **********************************
     */

    fun getMedicationCareProviderRelationById(careProviderId: CareProviderId): List<MedicationCareProviderDTO>

    fun getMedicationByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): MedicationDTO

    fun createPermissionLinkMedication(medicationId: MedicationId, careProviderId: CareProviderId, validDate: LocalDate)

    fun removeExpiredMedicationPermissions(currentDay: LocalDate)

    /**
     ********************************** Notes **********************************
     */

    fun getNoteCareProviderRelationById(careProviderId: CareProviderId): List<NoteCareProviderDTO>

    fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): NoteDTO

    fun createPermissionLinkNote(noteId: NoteId, careProviderId: CareProviderId, validDate: LocalDate)

    fun removeExpiredNotePermissions(currentDay: LocalDate)

    /**
     ********************************** Diagnosis **********************************
     */

    fun getDiagnosisCareProviderRelationById(careProviderId: CareProviderId): List<DiagnosisCareProviderDTO>

    fun getDiagnosisByIdAndMr(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): DiagnosisDTO

    fun createPermissionLinkDiagnosis(diagnosisId: DiagnosisId, careProviderId: CareProviderId, validDate: LocalDate)

    fun removeExpiredDiagnosisPermissions(currentDay: LocalDate)

}