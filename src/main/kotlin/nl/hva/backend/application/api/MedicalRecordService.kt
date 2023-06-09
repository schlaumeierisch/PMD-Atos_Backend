package nl.hva.backend.application.api

import nl.hva.backend.application.dto.*
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.value_objects.DiagnosisType
import java.time.LocalDate

interface MedicalRecordService {

    fun getMedicalRecord(medicalRecordId: MedicalRecordId): List<MedicalRecordDTO>


    // NOTES -----------------------------------------------------------------------------------------------------------
    fun getAllNotes(medicalRecordId: MedicalRecordId): List<NoteDTO>

    fun getNoteByIdAndMedicalRecordId(noteId: NoteId, medicalRecordId: MedicalRecordId): List<NoteDTO>

    fun createNote(title: String, description: String, medicalRecordId: MedicalRecordId)

    fun deleteNote(noteId: NoteId)


    // MEDICATION ------------------------------------------------------------------------------------------------------
    fun getAllMedication(medicalRecordId: MedicalRecordId): List<MedicationDTO>

    fun getMedicationById(medicationId: MedicationId): List<MedicationDTO>

    fun getMedicationByIdAndMedicalRecordId(medicationId: MedicationId, medicalRecordId: MedicalRecordId): List<MedicationDTO>

    fun createMedication(title: String, description: String, startDate: LocalDate, endDate: LocalDate?, medicalRecordId: MedicalRecordId)

    fun getIntakeByMedicationId(medicationId: MedicationId): List<IntakeDTO>


    // DIAGNOSES -------------------------------------------------------------------------------------------------------
    fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<DiagnosisDTO>

    fun getDiagnosisByIdAndMedicalRecordId(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): List<DiagnosisDTO>

    fun createDiagnosis(title: String, diagnosisType: Enum<DiagnosisType>, dateDiagnosed: LocalDate, cause: String, treatment: String, advice: String, medicalRecordId: MedicalRecordId)


    // EXERCISES -------------------------------------------------------------------------------------------------------
    fun getAllExercises(medicalRecordId: MedicalRecordId): List<ExerciseDTO>

    fun getExerciseByIdAndMedicalRecordId(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): List<ExerciseDTO>

    fun createExercise(title: String, description: String, startDate: LocalDate, endDate: LocalDate?, medicalRecordId: MedicalRecordId)

    fun deleteExercise(exerciseId: ExerciseId)

}