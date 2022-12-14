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
    ): GeneralPractitionerDTO? {
        // TODO: return something else than null (maybe ResponseEntity<>)
        return try {
            this.generalPractitionerService.getAccountById(GeneralPractitionerId(id))
        } catch (e: Exception) {
            null
        }
    }

    @GetMapping("/getPatientsOfGeneralPractitionerById/{id}")
    @ResponseBody
    fun getPatientsOfGeneralPractitioner(
        @PathVariable("id") id: String
    ): List<PatientDTO> {
        return this.generalPractitionerService.getPatientsOfGeneralPractitionerById(GeneralPractitionerId(id))
    }

    @ExceptionHandler(NotExistingException::class)
    fun handleNotExistingException(e: NotExistingException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }

}