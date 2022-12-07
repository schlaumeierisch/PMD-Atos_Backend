package nl.hva.backend.rest

import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.PatientId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/patients/")
class PatientRestController {

    @Autowired
    private lateinit var patientService: PatientService

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

}