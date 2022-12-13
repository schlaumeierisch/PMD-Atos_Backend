package nl.hva.backend.domain.api

import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
interface PermissionRepository {


    fun getMedicationCareProviderRelationById(careProviderId: CareProviderId): List<MedicationCareProviderRelation>

    fun getMedicationOfPatientByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): Medication

}