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
import java.time.LocalDate
import javax.validation.constraints.Null

@RestController
@RequestMapping("/rest/permissions")
class PermissionRestController {

    @Autowired
    private lateinit var permissionService: PermissionService

    /**
     ********************************** Medication **********************************
     */
    @GetMapping("/getMedicationOfMedicalRecord")
    @ResponseBody
    fun getMedicationCareProviderRelationById(
        mrId: String,
        cpId: String
    ): List<MedicationDTO> {
        //Check all permissions the care provider has
        val medicineCareProviderDTOs: List<MedicationCareProviderDTO> =
            this.permissionService.getMedicationCareProviderRelationById(
                CareProviderId(cpId)
            )

        //Return only the medication of the requested patient
        val medicationDTOs: ArrayList<MedicationDTO> = arrayListOf()
        for (medicineCareProviderDTO in medicineCareProviderDTOs) {
            medicationDTOs.add(
                this.permissionService.getMedicationByIdAndMr(
                    MedicationId(medicineCareProviderDTO.medId()),
                    MedicalRecordId(mrId)
                )
            )
        }
        return medicationDTOs
    }

    @GetMapping("/createMedicationPermission")
    @ResponseBody
    fun createMedicationCareProviderLink(
        medicationId: String,
        careProviderId: String,
        validDate: String
    ) {
        this.permissionService.createPermissionLink(MedicationId(medicationId), CareProviderId(careProviderId), LocalDate.parse(validDate))
    }

    @GetMapping("/removeExpiredMedicationPermission")
    @ResponseBody
    fun removeExpiredMedicationPermission(
        currentDay: String
    ) {
        this.permissionService.removeExpiredMedicationPermissions(LocalDate.parse(currentDay))
    }

}