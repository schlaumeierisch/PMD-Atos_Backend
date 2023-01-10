package nl.hva.backend.rest

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.*
import nl.hva.backend.domain.ids.ExerciseId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId
import nl.hva.backend.domain.value_objects.DiagnosisType
import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    ): ResponseEntity<List<NoteDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(id))

        if (medicalRecordDTO.isNotEmpty()) {
            val noteDTOs: List<NoteDTO> = this.medicalRecordService.getAllNotes(MedicalRecordId(id))

            return ResponseEntity.status(HttpStatus.OK).body(noteDTOs)
        } else {
            throw NotExistingException("Medical record with id \'$id\' does not exist.")
        }
    }

    @PostMapping("/notes/createNote")
    fun createNote(
        title: String,
        description: String,
        medicalRecordId: String
    ): ResponseEntity<String> {
        val medicalRecordDTO: List<MedicalRecordDTO> =
            this.medicalRecordService.getMedicalRecord(MedicalRecordId(medicalRecordId))

        if (medicalRecordDTO.isNotEmpty()) {
            this.medicalRecordService.createNote(title, description, MedicalRecordId(medicalRecordId))

            return ResponseEntity.status(HttpStatus.OK)
                .body("New node for medical record with id \'$medicalRecordId\' successfully created.")
        } else {
            throw NotExistingException("Medical record with id \'$medicalRecordId\' does not exist.")
        }
    }

    @DeleteMapping("/note/deleteNote")
    fun deleteNote(
        noteId: String
    ) {
        this.medicalRecordService.deleteNote(NoteId(noteId))
    }

    @GetMapping("/medication/getAllMedication/{id}")
    @ResponseBody
    fun getAllMedication(
        @PathVariable("id") id: String
    ): ResponseEntity<List<MedicationDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(id))

        if (medicalRecordDTO.isNotEmpty()) {
            val medicationDTOs: List<MedicationDTO> = this.medicalRecordService.getAllMedication(MedicalRecordId(id))

            return ResponseEntity.status(HttpStatus.OK).body(medicationDTOs)
        } else {
            throw NotExistingException("Medical record with id \'$id\' does not exist.")
        }
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
    ): ResponseEntity<String> {
        val medicalRecordDTO: List<MedicalRecordDTO> =
            this.medicalRecordService.getMedicalRecord(MedicalRecordId(medicalRecordId))

        if (medicalRecordDTO.isNotEmpty()) {
            this.medicalRecordService.createMedication(
                title,
                description,
                startDate,
                endDate,
                MedicalRecordId(medicalRecordId)
            )

            return ResponseEntity.status(HttpStatus.OK)
                .body("New medication for medical record with id \'$medicalRecordId\' successfully created.")
        } else {
            throw NotExistingException("Medical record with id \'$medicalRecordId\' does not exist.")
        }
    }

    @GetMapping("/medication/getIntakeByMedicationId/{id}")
    @ResponseBody
    fun getIntakeByMedicationId(
        @PathVariable("id") id: String
    ): ResponseEntity<List<IntakeDTO>> {
        val medicationDTO: List<MedicationDTO> = this.medicalRecordService.getMedicationById(MedicationId(id))

        if (medicationDTO.isNotEmpty()) {
            val intakeDTOs: List<IntakeDTO> = this.medicalRecordService.getIntakeByMedicationId(MedicationId(id))

            return ResponseEntity.status(HttpStatus.OK).body(intakeDTOs)
        } else {
            throw NotExistingException("Medication with id \'$id\' does not exist.")
        }
    }

    @GetMapping("/diagnoses/getAllDiagnoses/{id}")
    @ResponseBody
    fun getAllDiagnoses(
        @PathVariable("id") id: String
    ): ResponseEntity<List<DiagnosisDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(id))

        if (medicalRecordDTO.isNotEmpty()) {
            val diagnosisDTOs: List<DiagnosisDTO> = this.medicalRecordService.getAllDiagnoses(MedicalRecordId(id))

            return ResponseEntity.status(HttpStatus.OK).body(diagnosisDTOs)
        } else {
            throw NotExistingException("Medical record with id \'$id\' does not exist.")
        }
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
    ): ResponseEntity<String> {
        val medicalRecordDTO: List<MedicalRecordDTO> =
            this.medicalRecordService.getMedicalRecord(MedicalRecordId(medicalRecordId))

        if (medicalRecordDTO.isNotEmpty()) {
            this.medicalRecordService.createDiagnosis(
                title,
                DiagnosisType.valueOf(diagnosisType.uppercase()),
                dateDiagnosed,
                cause,
                treatment,
                advice,
                MedicalRecordId(medicalRecordId)
            )

            return ResponseEntity.status(HttpStatus.OK)
                .body("New diagnosis for medical record with id \'$medicalRecordId\' successfully created.")
        } else {
            throw NotExistingException("Medical record with id \'$medicalRecordId\' does not exist.")
        }
    }

    @GetMapping("/exercises/getAllExercises/{id}")
    @ResponseBody
    fun getAllExercises(
        @PathVariable("id") id: String
    ): ResponseEntity<List<ExerciseDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(id))

        if (medicalRecordDTO.isNotEmpty()) {
            val exerciseDTOs: List<ExerciseDTO> = this.medicalRecordService.getAllExercises(MedicalRecordId(id))

            return ResponseEntity.status(HttpStatus.OK).body(exerciseDTOs)
        } else {
            throw NotExistingException("Medical record with id \'$id\' does not exist.")
        }
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
    ): ResponseEntity<String> {
        val medicalRecordDTO: List<MedicalRecordDTO> =
            this.medicalRecordService.getMedicalRecord(MedicalRecordId(medicalRecordId))

        if (medicalRecordDTO.isNotEmpty()) {
            this.medicalRecordService.createExercise(
                title,
                description,
                startDate,
                endDate,
                MedicalRecordId(medicalRecordId)
            )

            return ResponseEntity.status(HttpStatus.OK)
                .body("New exercise for medical record with id \'$medicalRecordId\' successfully created.")
        } else {
            throw NotExistingException("Medical record with id \'$medicalRecordId\' does not exist.")
        }
    }

    @DeleteMapping("/exercises/deleteExercise")
    fun deleteExercise(
        exerciseId: String
    ) {
        this.medicalRecordService.deleteExercise(ExerciseId(exerciseId))
    }

}