package nl.hva.backend.rest

import nl.hva.backend.application.api.CareProviderService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.many_to_many.PatientCareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.PatientId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/patients/")
class PatientRestController {

    @Autowired
    private lateinit var patientService: PatientService

    @Autowired
    private lateinit var careProviderService: CareProviderService

    @GetMapping("/getAll")
    fun getAll(): List<PatientDTO> {
        return this.patientService.getAllAccounts()
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    fun getAccountById(
        @PathVariable("id") id: String
    ): PatientDTO? {
        // TODO: return something else than null (maybe ResponseEntity<>)
        return try {
            this.patientService.getAccountById(PatientId(id))
        } catch (e: Exception) {
            null
        }
    }

    @GetMapping("/getCareProvidersOfPatientById/{id}")
    @ResponseBody
    fun getCareProvidersOfPatientById(
        @PathVariable("id") id: String
    ): List<CareProviderDTO> {
        val patientCareProviderDTOs: List<PatientCareProviderDTO> =
            this.patientService.getPatientCareProviderRelationsByPatientId(PatientId(id))

        val careProviderDTOs: ArrayList<CareProviderDTO> = arrayListOf()
        for (patientCareProviderDTO in patientCareProviderDTOs) {
            careProviderDTOs.add(
                this.careProviderService.getAccountById(CareProviderId(patientCareProviderDTO.cpId()))
            )
        }

        return careProviderDTOs
    }

    @GetMapping("/createPatientCareProviderLink")
    @ResponseBody
    fun createPatientCareProviderLink(
        careProviderId: String,
        patientId: String
    ){
        this.patientService.createLinkPatientCareProvider(PatientId(patientId), CareProviderId(careProviderId))
    }

}