package nl.hva.backend.rest

import nl.hva.backend.application.api.CareProviderService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/careProviders/")
class CareProviderRestController {

    @Autowired
    private lateinit var careProviderService: CareProviderService

    @GetMapping("/getAll")
    fun getAll(): List<CareProviderDTO> {
        return this.careProviderService.getAllAccounts()
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    fun getAccountById(
        @PathVariable("id") id: String
    ): CareProviderDTO? {
        // TODO: return something else than null (maybe ResponseEntity<>)
        return try {
            this.careProviderService.getAccountById(CareProviderId(id))
        } catch (e: Exception) {
            null
        }
    }
}