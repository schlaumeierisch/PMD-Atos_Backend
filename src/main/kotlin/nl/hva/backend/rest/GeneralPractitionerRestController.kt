package nl.hva.backend.rest

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

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
}