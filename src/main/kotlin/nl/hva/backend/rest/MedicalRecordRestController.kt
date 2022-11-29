package nl.hva.backend.rest

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.ObservationDTO
import nl.hva.backend.domain.ids.MedicalRecordId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/medicalRecords/")
class MedicalRecordRestController {

    @Autowired
    private lateinit var medicalRecordService: MedicalRecordService

    @GetMapping("/getAllObservations/{id}")
    @ResponseBody
    fun getPatientsOfGeneralPractitioner(
        @PathVariable("id") id: String
    ): List<ObservationDTO> {
        return this.medicalRecordService.getAllObservations(MedicalRecordId(id))
    }

    @PostMapping("/createObservation")
    fun createObservation(
        title: String,
        description: String,
        medicalRecordId: String
    ) {
        this.medicalRecordService.createObservation(title, description, medicalRecordId)
    }
}