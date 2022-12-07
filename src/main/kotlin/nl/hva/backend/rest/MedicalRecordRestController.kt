package nl.hva.backend.rest

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.ExerciseDTO
import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/medicalRecords/")
class MedicalRecordRestController {

    @Autowired
    private lateinit var medicalRecordService: MedicalRecordService

    @GetMapping("/notes/getAllNotes/{id}")
    @ResponseBody
    fun getAllNotes(
        @PathVariable("id") id: String
    ): List<NoteDTO> {
        return this.medicalRecordService.getAllNotes(MedicalRecordId(id))
    }

    @PostMapping("/notes/createNote")
    fun createNote(
        title: String,
        description: String,
        medicalRecordId: String
    ) {
        this.medicalRecordService.createNote(title, description, medicalRecordId)
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

    @GetMapping("/medication/getAllExercises/{id}")
    @ResponseBody
    fun getAllExercises(
        @PathVariable("id") id: String
    ): List<ExerciseDTO> {
        return this.medicalRecordService.getAllExercises(MedicalRecordId(id))
    }

}