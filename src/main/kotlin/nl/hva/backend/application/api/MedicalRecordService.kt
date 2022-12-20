package nl.hva.backend.application.api

import nl.hva.backend.application.dto.*
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId
import nl.hva.backend.application.dto.ExerciseDTO
import nl.hva.backend.application.dto.DiagnosisDTO
import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.value_objects.DiagnosisType
import java.time.LocalDate

interface MedicalRecordService {

    fun createMedicalRecord(): MedicalRecordId

    fun getMedicalRecord(medicalRecordId: MedicalRecordId): List<MedicalRecordDTO>

    fun getAllNotes(medicalRecordId: MedicalRecordId): List<NoteDTO>

    fun createNote(title: String, description: String, medicalRecordId: MedicalRecordId)

    fun getAllMedication(medicalRecordId: MedicalRecordId): List<MedicationDTO>

    fun getMedicationById(medicationId: MedicationId): List<MedicationDTO>

    fun createMedication(title: String, description: String, startDate: LocalDate, endDate: LocalDate?, medicalRecordId: MedicalRecordId)

    fun getIntakeByMedicationId(medicationId: MedicationId): List<IntakeDTO>

    fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<DiagnosisDTO>

    fun createDiagnosis(title: String, diagnosisType: Enum<DiagnosisType>, dateDiagnosed: LocalDate, cause: String, treatment: String, advice: String, medicalRecordId: MedicalRecordId)

    fun getAllExercises(medicalRecordId: MedicalRecordId): List<ExerciseDTO>

    fun createExercise(title: String, description: String, startDate: LocalDate, endDate: LocalDate?, medicalRecordId: MedicalRecordId)

    fun deleteNote(noteId: NoteId)

    fun getMedicationByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): MedicationDTO

    fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): NoteDTO

    fun getDiagnosisByIdAndMr(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): DiagnosisDTO

    fun getExerciseByIdAndMr(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): ExerciseDTO

}