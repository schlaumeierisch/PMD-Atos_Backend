package nl.hva.backend.domain.api

import nl.hva.backend.domain.*
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.many_to_many.DiagnosisCareProviderRelation

interface MedicalRecordRepository {

    fun nextIdentity(): MedicalRecordId

    fun nextNoteIdentity(): NoteId

    fun nextDiagnosisIdentity(): DiagnosisId

    fun nextMedicationIdentity(): MedicationId

    fun nextExerciseIdentity(): ExerciseId

    fun createMedicalRecord(medicalRecord: MedicalRecord)

    fun getAllNotes(medicalRecordId: MedicalRecordId): List<Note>

    fun createNote(note: Note)

    fun getAllMedication(medicalRecordId: MedicalRecordId): List<Medication>

    fun createMedication(medication: Medication)

    fun getIntakeByMedicationId(medicationId: MedicationId): List<Intake>

    fun getAllExercises(medicalRecordId: MedicalRecordId): List<Exercise>

    fun createExercise(exercise: Exercise)

    fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<Diagnosis>

    fun createDiagnosis(diagnosis: Diagnosis)

    fun deleteNote(noteId: NoteId)

    fun getMedicationOfPatientByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): Medication

    fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): Note

    fun getDiagnosisByIdAndMr(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): Diagnosis

    fun getExerciseByIdAndMr(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): Exercise


}