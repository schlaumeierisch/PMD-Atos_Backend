package nl.hva.backend.application.api

import nl.hva.backend.application.dto.ExerciseDTO
import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId

interface MedicalRecordService {

    fun createMedicalRecord(): MedicalRecordId

    fun getAllNotes(medicalRecordId: MedicalRecordId): List<NoteDTO>

    fun createNote(title: String, description: String, medicalRecordId: String)

    fun getAllMedication(medicalRecordId: MedicalRecordId): List<MedicationDTO>

    fun getIntakeByMedicationId(medicationId: MedicationId): List<IntakeDTO>

    fun getAllExercises(medicalRecordId: MedicalRecordId): List<ExerciseDTO>

}