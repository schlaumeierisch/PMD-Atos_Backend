package nl.hva.backend.rest

import nl.hva.backend.application.api.CareProviderService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/careProviders/")
class CareProviderRestController {

    @Autowired
    private lateinit var careProviderService: CareProviderService

    @GetMapping("/getAll")
    fun getAll(): ResponseEntity<List<CareProviderDTO>> {
        val careProviderDTOs: List<CareProviderDTO> = this.careProviderService.getAllAccounts()

        return ResponseEntity.status(HttpStatus.OK).body(careProviderDTOs)
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    fun getAccountById(
        @PathVariable("id") id: String
    ): ResponseEntity<CareProviderDTO> {
        val careProviderDTO: List<CareProviderDTO> = this.careProviderService.getAccountById(CareProviderId(id))

        if (careProviderDTO.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(careProviderDTO[0])
        } else {
            throw NotExistingException("Care provider with id '$id' does not exist.")
        }
    }
}