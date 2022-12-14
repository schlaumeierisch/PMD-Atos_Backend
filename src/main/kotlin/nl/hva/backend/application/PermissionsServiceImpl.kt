package nl.hva.backend.application

import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.api.PermissionRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PermissionsServiceImpl : PermissionService {

    @Autowired
    private lateinit var permissionsRepository: PermissionRepository

    /**
     ********************************** Medication **********************************
     */

    @Transactional
    override fun getMedicationCareProviderRelationById(
        careProviderId: CareProviderId
    ): List<MedicationCareProviderDTO> {
        val medicationCareProviderRelations: List<MedicationCareProviderRelation> =
            this.permissionsRepository.getMedicationCareProviderRelationById(careProviderId)
        return MedicationCareProviderDTO.fromMedicationCareProviderRelations(medicationCareProviderRelations)
    }

    @Transactional
    override fun getMedicationByIdAndMr(medicationId: MedicationId, medicalRecordId: MedicalRecordId): MedicationDTO {
        val medication: Medication =
            this.permissionsRepository.getMedicationOfPatientByIdAndMr(medicationId, medicalRecordId)

        return MedicationDTO.fromMedication(medication)
    }

    @Transactional
    override fun createPermissionLink(
        medicationId: MedicationId,
        careProviderId: CareProviderId,
        validDate: LocalDate
    ) {
        val medicationCareProviderRelation = MedicationCareProviderRelation (careProviderId, medicationId, validDate)
        this.permissionsRepository.createPermissionLinkMedication(medicationCareProviderRelation)
    }

    @Transactional
    override fun removeExpiredMedicationPermissions(currentDay: LocalDate) {
        this.permissionsRepository.removeExpiredMedicationPermissions(currentDay)
    }


}