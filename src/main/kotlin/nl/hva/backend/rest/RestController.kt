package nl.hva.backend.rest

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class RestController {

    @Autowired
    private lateinit var patientService: PatientService

    @Autowired
    private lateinit var generalPractitionerService: GeneralPractitionerService

    @GetMapping("/patients/getAll")
    fun getAllPatients(): List<PatientDTO> = this.patientService.getAllAccounts()

    @GetMapping("/patients/getByGeneralPractitionerId/{id}")
    fun getPatientsOfGeneralPractitioner(
        @PathVariable("id") id: String
    ): List<PatientDTO> {
        // todo

        return emptyList()
    }

    @GetMapping("/generalPractitioners/getAll")
    @ResponseBody
    fun getAllGeneralPractitioners(): List<GeneralPractitionerDTO> {
        return this.generalPractitionerService.getAllAccounts()
    }
}