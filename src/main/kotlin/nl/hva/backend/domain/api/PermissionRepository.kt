package nl.hva.backend.domain.api

import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
interface PermissionRepository {


    fun getMedicationCareProviderRelationByIds(medicationId: MedicationId, careProviderId: CareProviderId): List<MedicationCareProviderRelation>
}