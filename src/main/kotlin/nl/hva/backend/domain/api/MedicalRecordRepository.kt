package nl.hva.backend.domain.api

import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.Observation
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.ObservationId

interface MedicalRecordRepository {

    fun nextIdentity(): MedicalRecordId

    fun nextObservationIdentity(): ObservationId

    fun createMedicalRecord(medicalRecord: MedicalRecord)

    fun getAllObservations(medicalRecordId: MedicalRecordId): List<Observation>

    fun createObservation(observation: Observation)

}