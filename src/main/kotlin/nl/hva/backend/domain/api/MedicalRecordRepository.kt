package nl.hva.backend.domain.api

import nl.hva.backend.domain.*
import nl.hva.backend.domain.ids.*

interface MedicalRecordRepository {

    fun nextIdentity(): MedicalRecordId

    fun nextNoteIdentity(): NoteId

    fun nextDiagnosisIdentity(): DiagnosisId

    fun nextMedicationIdentity(): MedicationId

    fun nextExerciseIdentity(): ExerciseId

    fun createMedicalRecord(medicalRecord: MedicalRecord)

    fun getMedicalRecord(medicalRecordId: MedicalRecordId): List<MedicalRecord>

    fun getAllNotes(medicalRecordId: MedicalRecordId): List<Note>

    fun createNote(note: Note)

    fun deleteNote(noteId: NoteId)

    fun getAllMedication(medicalRecordId: MedicalRecordId): List<Medication>

    fun getMedicationById(medicationId: MedicationId): List<Medication>

    fun createMedication(medication: Medication)

    fun getIntakeByMedicationId(medicationId: MedicationId): List<Intake>

    fun getAllExercises(medicalRecordId: MedicalRecordId): List<Exercise>

    fun createExercise(exercise: Exercise)

    fun deleteExercise(exerciseId: ExerciseId)

    fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<Diagnosis>

    fun createDiagnosis(diagnosis: Diagnosis)

    fun getMedicationOfPatientByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): Medication

    fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): Note

    fun getDiagnosisByIdAndMr(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): Diagnosis

    fun getExerciseByIdAndMr(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): Exercise


}