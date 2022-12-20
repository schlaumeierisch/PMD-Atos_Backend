package nl.hva.backend.domain.api

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

    fun createPermissionLinkMedication(medicationCareProviderRelation: MedicationCareProviderRelation)

    fun removeExpiredMedicationPermissions(currentDay: LocalDate)

    fun removeSelectedMedicationPermission(medicationId: MedicationId, careProviderId: CareProviderId)


    /**
     ********************************** Notes **********************************
     */

    fun getNoteCareProviderRelationById(careProviderId: CareProviderId): List<NoteCareProviderRelation>

    fun createPermissionLinkNote(noteCareProviderRelation: NoteCareProviderRelation)

    fun removeExpiredNotePermissions(currentDay: LocalDate)

    fun removeSelectedNotePermission(noteId: NoteId, careProviderId: CareProviderId)

    /**
     ********************************** Diagnosis **********************************
     */

    fun getDiagnosisCareProviderRelationById(careProviderId: CareProviderId): List<DiagnosisCareProviderRelation>

    fun createPermissionLinkDiagnosis(diagnosisCareProviderRelation: DiagnosisCareProviderRelation)


    fun removeExpiredDiagnosisPermissions(currentDay: LocalDate)

    fun removeSelectedDiagnosisPermission(diagnosisId: DiagnosisId, careProviderId: CareProviderId)

    /**
     ********************************** Exercise **********************************
     */
    fun getExerciseCareProviderRelationById(careProviderId: CareProviderId): List<ExerciseCareProviderRelation>

    fun createPermissionLinkExercise(exerciseCareProviderRelation: ExerciseCareProviderRelation)

    fun removeExpiredExercisePermissions(currentDay: LocalDate)

    fun removeSelectedExercisePermission(exerciseId: ExerciseId, careProviderId: CareProviderId)


}
