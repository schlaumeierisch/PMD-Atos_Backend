package nl.hva.backend.rest

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Null

@RestController
@RequestMapping("/rest/permissions")
class PermissionRestController {

    @Autowired
    private lateinit var permissionService: PermissionService


    @GetMapping("/getMedicationOfMedicalRecord")
    @ResponseBody
    fun getMedicationCareProviderRelationById(
        mrId: String,
        cpId: String
    ): List<MedicationDTO> {
        val medicineCareProviderDTOs: List<MedicationCareProviderDTO> =
            this.permissionService.getMedicationCareProviderRelationById(
                CareProviderId(cpId)
            )

        val medicationDTOs: ArrayList<MedicationDTO> = arrayListOf()
        for (medicineCareProviderDTO in medicineCareProviderDTOs) {
            val medication = this.permissionService.getMedicationByIdAndMr(
                MedicationId(medicineCareProviderDTO.medId()),
                MedicalRecordId(mrId)
            )

            if(medication.id().isNotEmpty()) {
                medicationDTOs.add(medication)
            }
        }

        return medicationDTOs
    }
}