package nl.hva.backend.domain.api

import nl.hva.backend.application.dto.DiagnosisDTO
import nl.hva.backend.application.dto.many_to_many.DiagnosisCareProviderDTO
import nl.hva.backend.domain.Diagnosis
import nl.hva.backend.domain.Exercise
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Note
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.many_to_many.DiagnosisCareProviderRelation
import nl.hva.backend.domain.many_to_many.ExerciseCareProviderRelation
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

    fun removeSelectedMedicationPermission(medicationId: MedicationId, careProviderId: CareProviderId)


    /**
     ********************************** Notes **********************************
     */

    fun getNoteCareProviderRelationById(careProviderId: CareProviderId): List<NoteCareProviderRelation>

    fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): Note

    fun createPermissionLinkNote(noteCareProviderRelation: NoteCareProviderRelation)

    fun removeExpiredNotePermissions(currentDay: LocalDate)

    fun removeSelectedNotePermission(noteId: NoteId)

    /**
     ********************************** Diagnosis **********************************
     */

    fun getDiagnosisCareProviderRelationById(careProviderId: CareProviderId): List<DiagnosisCareProviderRelation>

    fun getDiagnosisByIdAndMr(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): Diagnosis

    fun createPermissionLinkDiagnosis(diagnosisCareProviderRelation: DiagnosisCareProviderRelation)

    fun removeExpiredDiagnosisPermissions(currentDay: LocalDate)

    fun removeSelectedDiagnosisPermission(diagnosisId: DiagnosisId)

    /**
     ********************************** Exercise **********************************
     */
    fun getExerciseCareProviderRelationById(careProviderId: CareProviderId): List<ExerciseCareProviderRelation>

    fun getExerciseByIdAndMr(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): Exercise

    fun createPermissionLinkExercise(exerciseCareProviderRelation: ExerciseCareProviderRelation)

    fun removeExpiredExercisePermissions(currentDay: LocalDate)

    fun removeSelectedExercisePermission(exerciseId: ExerciseId)


}
