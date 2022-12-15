package nl.hva.backend.rest

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.ExerciseDTO
import nl.hva.backend.application.dto.DiagnosisDTO
import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId
import nl.hva.backend.domain.value_objects.DiagnosisType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

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
        this.medicalRecordService.createNote(title, description, MedicalRecordId(medicalRecordId))
    }

    @GetMapping("/medication/getAllMedication/{id}")
    @ResponseBody
    fun getAllMedication(
        @PathVariable("id") id: String
    ): List<MedicationDTO> {
        return this.medicalRecordService.getAllMedication(MedicalRecordId(id))
    }

    @PostMapping("/medication/createMedication")
    fun createMedication(
        title: String,
        description: String,
        @RequestParam("startDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
        @RequestParam("endDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate? = null,
        medicalRecordId: String
    ) {
        this.medicalRecordService.createMedication(
            title,
            description,
            startDate,
            endDate,
            MedicalRecordId(medicalRecordId)
        )
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

    @PostMapping("/diagnoses/createDiagnosis")
    fun createDiagnosis(
        title: String,
        diagnosisType: String,
        @RequestParam("dateDiagnosed")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) dateDiagnosed: LocalDate,
        cause: String,
        treatment: String,
        advice: String,
        medicalRecordId: String
    ) {
        this.medicalRecordService.createDiagnosis(
            title,
            DiagnosisType.valueOf(diagnosisType),
            dateDiagnosed,
            cause,
            treatment,
            advice,
            MedicalRecordId(medicalRecordId)
        )
    }

    @GetMapping("/exercises/getAllExercises/{id}")
    @ResponseBody
    fun getAllExercises(
        @PathVariable("id") id: String
    ): List<ExerciseDTO> {
        return this.medicalRecordService.getAllExercises(MedicalRecordId(id))
    }

    @PostMapping("/exercises/createExercise")
    fun createExercise(
        title: String,
        description: String,
        @RequestParam("startDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
        @RequestParam("endDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate? = null,
        medicalRecordId: String
    ) {
        this.medicalRecordService.createExercise(
            title,
            description,
            startDate,
            endDate,
            MedicalRecordId(medicalRecordId)
        )
    }
    @DeleteMapping("/note/deleteNote")
    fun deleteNote(
        noteId: String
    ){
        this.medicalRecordService.deleteNote(NoteId(noteId))
    }

}