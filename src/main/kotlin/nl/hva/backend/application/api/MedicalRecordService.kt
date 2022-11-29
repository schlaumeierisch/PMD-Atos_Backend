package nl.hva.backend.application.api

import nl.hva.backend.application.dto.ObservationDTO
import nl.hva.backend.domain.ids.MedicalRecordId

interface MedicalRecordService {

    fun createMedicalRecord(): MedicalRecordId

    fun getAllObservations(medicalRecordId: MedicalRecordId): List<ObservationDTO>

    fun createObservation(title: String, description: String, medicalRecordId: String)

}