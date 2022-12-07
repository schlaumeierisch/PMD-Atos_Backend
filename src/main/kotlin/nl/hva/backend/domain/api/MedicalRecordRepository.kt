package nl.hva.backend.domain.api

import nl.hva.backend.domain.Intake
import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Note
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId

interface MedicalRecordRepository {

    fun nextIdentity(): MedicalRecordId

    fun nextNoteIdentity(): NoteId

    fun createMedicalRecord(medicalRecord: MedicalRecord)

    fun getAllNotes(medicalRecordId: MedicalRecordId): List<Note>

    fun createNote(note: Note)

    fun getAllMedication(medicalRecordId: MedicalRecordId): List<Medication>

    fun getIntakeByMedicationId(medicationId: MedicationId): List<Intake>

    fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<Diagnosis>

}