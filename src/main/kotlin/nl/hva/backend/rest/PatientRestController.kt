package nl.hva.backend.rest

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.GeneralPractitionerId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/patients/")
class PatientRestController {

    @Autowired
    private lateinit var patientService: PatientService

    @Autowired
    private lateinit var generalPractitionerService: GeneralPractitionerService

    @GetMapping("/getAll")
    fun getAll(): List<PatientDTO> {
        return this.patientService.getAllAccounts()
    }

    @GetMapping("/getByGeneralPractitionerId/{id}")
    @ResponseBody
    fun getPatientsOfGeneralPractitioner(
        @PathVariable("id") id: String
    ): MutableSet<PatientDTO> {
        val generalPractitionerDTO = this.generalPractitionerService.getAccountById(GeneralPractitionerId(id))

        // TODO: fix
        return mutableSetOf()
    }
}