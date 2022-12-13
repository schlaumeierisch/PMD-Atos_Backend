package nl.hva.backend.rest

import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/permissions")
class PermissionRestController {

    @Autowired
    private lateinit var permissionService: PermissionService

    @GetMapping ("/getMedicationOfMedicalRecord")
    @ResponseBody
    fun getMedicationCareProviderRelationByIds(
         mcId: String,
         cpId: String
    ): List<MedicationCareProviderDTO> {
        return this.permissionService.getMedicationCareProviderRelationByIds(
            MedicationId(mcId), CareProviderId(cpId)
        )
    }
}