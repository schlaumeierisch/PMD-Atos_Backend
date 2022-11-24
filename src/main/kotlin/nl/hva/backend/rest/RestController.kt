package nl.hva.backend.rest

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.GeneralPractitionerId
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
    @ResponseBody
    fun getAllPatients(): List<PatientDTO> {
        return this.patientService.getAllAccounts()
    }

    @GetMapping("/patients/getByGeneralPractitionerId/{id}")
    @ResponseBody
    fun getPatientsOfGeneralPractitioner(
        @PathVariable("id") id: String
    ): MutableSet<PatientDTO> {
        val generalPractitionerDTO = this.generalPractitionerService.getAccountById(GeneralPractitionerId(id))
        return generalPractitionerDTO.patientDTOs()
    }

    @GetMapping("/generalPractitioners/getAll")
    @ResponseBody
    fun getAllGeneralPractitioners(): List<GeneralPractitionerDTO> {
        return this.generalPractitionerService.getAllAccounts()
    }
}