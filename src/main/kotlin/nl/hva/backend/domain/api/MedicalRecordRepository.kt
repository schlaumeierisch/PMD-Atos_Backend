package nl.hva.backend.domain.api

import nl.hva.backend.domain.*
import nl.hva.backend.domain.ids.*

interface MedicalRecordRepository {

    fun nextIdentity(): MedicalRecordId

    fun nextNoteIdentity(): NoteId

    fun nextDiagnosisIdentity(): DiagnosisId

    fun nextMedicationIdentity(): MedicationId

    fun nextExerciseIdentity(): ExerciseId

    fun getMedicalRecord(medicalRecordId: MedicalRecordId): List<MedicalRecord>


    // NOTES -----------------------------------------------------------------------------------------------------------
    fun getAllNotes(medicalRecordId: MedicalRecordId): List<Note>

    fun getNoteByIdAndMedicalRecordId(noteId: NoteId, medicalRecordId: MedicalRecordId): List<Note>

    fun createNote(note: Note)

    fun deleteNote(noteId: NoteId)


    // MEDICATION ------------------------------------------------------------------------------------------------------
    fun getAllMedication(medicalRecordId: MedicalRecordId): List<Medication>

    fun getMedicationById(medicationId: MedicationId): List<Medication>

    fun getMedicationByIdAndMedicalRecordId(medicationId: MedicationId, medicalRecordId: MedicalRecordId): List<Medication>

    fun createMedication(medication: Medication)

    fun getIntakeByMedicationId(medicationId: MedicationId): List<Intake>


    // DIAGNOSES -------------------------------------------------------------------------------------------------------
    fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<Diagnosis>

    fun getDiagnosisByIdAndMedicalRecordId(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): List<Diagnosis>

    fun createDiagnosis(diagnosis: Diagnosis)


    // EXERCISES -------------------------------------------------------------------------------------------------------
    fun getAllExercises(medicalRecordId: MedicalRecordId): List<Exercise>

    fun getExerciseByIdAndMedicalRecordId(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): List<Exercise>

    fun createExercise(exercise: Exercise)

    fun deleteExercise(exerciseId: ExerciseId)

}