package nl.hva.backend.application

import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.domain.api.PermissionRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PermissionsServiceImpl : PermissionService {

    @Autowired
    private lateinit var permissionsRepository: PermissionRepository


    @Transactional
    override fun getMedicationCareProviderRelationByIds(
        medicationId: MedicationId,
        careProviderId: CareProviderId
    ): List<MedicationCareProviderDTO> {
        val medicationCareProviderRelations: List<MedicationCareProviderRelation> =
            this.permissionsRepository.getMedicationCareProviderRelationByIds(medicationId, careProviderId)
        return MedicationCareProviderDTO.fromMedicationCareProviderRelations(medicationCareProviderRelations)
    }
}