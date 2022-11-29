package nl.hva.backend.application.api

import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.ObservationDTO
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId

interface MedicalRecordService {

    fun createMedicalRecord(): MedicalRecordId

    fun getAllObservations(medicalRecordId: MedicalRecordId): List<ObservationDTO>

    fun createObservation(title: String, description: String, medicalRecordId: String)

    fun getAllMedication(medicalRecordId: MedicalRecordId): List<MedicationDTO>

    fun getIntakeByMedicationId(medicationId: MedicationId): List<IntakeDTO>

}