package nl.hva.backend.rest

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/generalPractitioners")
class GeneralPractitionerRestController {

    @Autowired
    private lateinit var generalPractitionerService: GeneralPractitionerService

    @GetMapping("/getAll")
    @ResponseBody
    fun getAllAccounts(): List<GeneralPractitionerDTO> {
        return this.generalPractitionerService.getAllAccounts()
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    fun getAccountById(
        @PathVariable("id") id: String
    ): ResponseEntity<GeneralPractitionerDTO> {
        val generalPractitionerDTO: List<GeneralPractitionerDTO> = this.generalPractitionerService.getAccountById(GeneralPractitionerId(id))

        if (generalPractitionerDTO.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(generalPractitionerDTO[0])
        } else {
            throw NotExistingException("General practitioner with id '$id' does not exist.")
        }
    }

    @GetMapping("/getPatientsOfGeneralPractitionerById/{id}")
    @ResponseBody
    fun getPatientsOfGeneralPractitioner(
        @PathVariable("id") id: String
    ): List<PatientDTO> {
        return this.generalPractitionerService.getPatientsOfGeneralPractitionerById(GeneralPractitionerId(id))
    }

}