package nl.hva.backend.application.api

import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicationId

interface PermissionService {

    fun getMedicationCareProviderRelationByIds(medicationId: MedicationId, careProviderId: CareProviderId): List<MedicationCareProviderDTO>
}