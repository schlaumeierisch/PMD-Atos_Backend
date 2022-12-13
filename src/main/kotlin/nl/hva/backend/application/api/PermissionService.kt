package nl.hva.backend.application.api

import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId

interface PermissionService {

    fun getMedicationCareProviderRelationById(careProviderId: CareProviderId): List<MedicationCareProviderDTO>

    fun getMedicationByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): MedicationDTO

}