package nl.hva.backend.rest

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.ObservationDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/medicalRecords/")
class MedicalRecordRestController {

    @Autowired
    private lateinit var medicalRecordService: MedicalRecordService

    @Autowired
    private lateinit var patientService: PatientService

    @GetMapping("/getAllObservationsByPatientId/{id}")
    @ResponseBody
    fun getPatientsOfGeneralPractitioner(
        @PathVariable("id") id: String
    ): List<ObservationDTO> {
        val patientDTO: PatientDTO = this.patientService.getAccountById(PatientId(id))

        return this.medicalRecordService.getAllObservationsByMedicalRecordId(MedicalRecordId(patientDTO.medicalRecordId()))
    }

}