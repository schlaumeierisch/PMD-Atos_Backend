package nl.hva.backend.rest

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.DiagnosisDTO
import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.ObservationDTO
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/medicalRecords/")
class MedicalRecordRestController {

    @Autowired
    private lateinit var medicalRecordService: MedicalRecordService

    @GetMapping("/observations/getAllObservations/{id}")
    @ResponseBody
    fun getAllObservations(
        @PathVariable("id") id: String
    ): List<ObservationDTO> {
        return this.medicalRecordService.getAllObservations(MedicalRecordId(id))
    }

    @PostMapping("/observations/createObservation")
    fun createObservation(
        title: String,
        description: String,
        medicalRecordId: String
    ) {
        this.medicalRecordService.createObservation(title, description, medicalRecordId)
    }

    @GetMapping("/medication/getAllMedication/{id}")
    @ResponseBody
    fun getAllMedication(
        @PathVariable("id") id: String
    ): List<MedicationDTO> {
        return this.medicalRecordService.getAllMedication(MedicalRecordId(id))
    }

    @GetMapping("/medication/getIntakeByMedicationId/{id}")
    @ResponseBody
    fun getIntakeByMedicationId(
        @PathVariable("id") id: String
    ): List<IntakeDTO> {
        return this.medicalRecordService.getIntakeByMedicationId(MedicationId(id))
    }

    @GetMapping("/diagnoses/getAllDiagnoses/{id}")
    @ResponseBody
    fun getAllDiagnoses(
        @PathVariable("id") id: String
    ): List<DiagnosisDTO> {
        return this.medicalRecordService.getAllDiagnoses(MedicalRecordId(id))
    }

}