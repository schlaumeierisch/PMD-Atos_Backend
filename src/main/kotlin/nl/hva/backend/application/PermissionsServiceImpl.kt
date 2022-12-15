package nl.hva.backend.application

import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.DiagnosisDTO
import nl.hva.backend.application.dto.ExerciseDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.application.dto.many_to_many.DiagnosisCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.ExerciseCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.NoteCareProviderDTO
import nl.hva.backend.domain.Diagnosis
import nl.hva.backend.domain.Exercise
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Note
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.api.PermissionRepository
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.many_to_many.DiagnosisCareProviderRelation
import nl.hva.backend.domain.many_to_many.ExerciseCareProviderRelation
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
        this.permissionsRepository.createPermissionLinkMedication(
            MedicationCareProviderRelation(
                careProviderId,
                medicationId,
                validDate
            )
        )
    }

    @Transactional
    override fun removeExpiredMedicationPermissions(currentDay: LocalDate) {
        this.permissionsRepository.removeExpiredMedicationPermissions(currentDay)
    }

    @Transactional
    override fun removeSelectedMedicationPermission(medicationId: MedicationId, careProviderId: CareProviderId) {
        this.permissionsRepository.removeSelectedMedicationPermission(medicationId, careProviderId)
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

    @Transactional
    override fun createPermissionLinkNote(noteId: NoteId, careProviderId: CareProviderId, validDate: LocalDate) {
        this.permissionsRepository.createPermissionLinkNote(NoteCareProviderRelation(careProviderId, noteId, validDate))
    }

    @Transactional
    override fun removeExpiredNotePermissions(currentDay: LocalDate) {
        this.permissionsRepository.removeExpiredNotePermissions(currentDay)
    }

    @Transactional
    override fun removeSelectedNotePermission(noteId: NoteId, careProviderId: CareProviderId) {
        this.permissionsRepository.removeSelectedNotePermission(noteId, careProviderId)
    }

    /**
     ********************************** Diagnosis **********************************
     */
    @Transactional
    override fun getDiagnosisCareProviderRelationById(careProviderId: CareProviderId): List<DiagnosisCareProviderDTO> {
        val diagnosisCareProviderRelations: List<DiagnosisCareProviderRelation> =
            this.permissionsRepository.getDiagnosisCareProviderRelationById(careProviderId)
        return DiagnosisCareProviderDTO.fromDiagnosisCareProviderRelations(diagnosisCareProviderRelations)
    }

    @Transactional
    override fun getDiagnosisByIdAndMr(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): DiagnosisDTO {
        val diagnosis: Diagnosis =
            this.permissionsRepository.getDiagnosisByIdAndMr(diagnosisId, medicalRecordId)

        return DiagnosisDTO.fromDiagnosis(diagnosis)
    }

    @Transactional
    override fun createPermissionLinkDiagnosis(
        diagnosisId: DiagnosisId,
        careProviderId: CareProviderId,
        validDate: LocalDate
    ) {
        this.permissionsRepository.createPermissionLinkDiagnosis(
            DiagnosisCareProviderRelation(
                careProviderId,
                diagnosisId,
                validDate
            )
        )
    }

    @Transactional
    override fun removeExpiredDiagnosisPermissions(currentDay: LocalDate) {
        this.permissionsRepository.removeExpiredDiagnosisPermissions(currentDay)
    }

    @Transactional
    override fun removeSelectedDiagnosisPermission(diagnosisId: DiagnosisId, careProviderId: CareProviderId) {
        this.permissionsRepository.removeSelectedDiagnosisPermission(diagnosisId, careProviderId)
    }

    /**
     ********************************** Exercise **********************************
     */
    @Transactional
    override fun getExerciseCareProviderRelationById(careProviderId: CareProviderId): List<ExerciseCareProviderDTO> {
        val exerciseCareProviderRelations: List<ExerciseCareProviderRelation> =
            this.permissionsRepository.getExerciseCareProviderRelationById(careProviderId)
        return ExerciseCareProviderDTO.fromExerciseCareProviderRelations(exerciseCareProviderRelations)
    }

    @Transactional
    override fun getExerciseByIdAndMr(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): ExerciseDTO {
        val exercise: Exercise =
            this.permissionsRepository.getExerciseByIdAndMr(exerciseId, medicalRecordId)

        return ExerciseDTO.fromExercise(exercise)
    }

    @Transactional
    override fun createExerciseLinkDiagnosis(
        exerciseId: ExerciseId,
        careProviderId: CareProviderId,
        validDate: LocalDate
    ) {
        this.permissionsRepository.createPermissionLinkExercise(
            ExerciseCareProviderRelation(
                careProviderId,
                exerciseId,
                validDate
            )
        )
    }

    @Transactional
    override fun removeExpiredExercisePermissions(currentDay: LocalDate) {
        this.permissionsRepository.removeExpiredExercisePermissions(currentDay)
    }

    @Transactional
    override fun removeSelectedExercisePermission(exerciseId: ExerciseId, careProviderId: CareProviderId) {
        this.permissionsRepository.removeSelectedExercisePermission(exerciseId, careProviderId)
    }
}