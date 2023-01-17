package nl.hva.backend.rest

import nl.hva.backend.application.api.AccountService
import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.*
import nl.hva.backend.application.dto.many_to_many.DiagnosisCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.ExerciseCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.NoteCareProviderDTO
import nl.hva.backend.domain.ids.*
import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/rest/permissions")
class PermissionRestController {

    @Autowired
    private lateinit var permissionService: PermissionService

    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var medicalRecordService: MedicalRecordService

    /**
     ********************************** General **********************************
     */

    @DeleteMapping("/removeExpiredPermissions")
    fun removeExpiredPermissions(
        @RequestParam("currentDay")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) currentDay: LocalDate,
    ) {
        this.permissionService.removeExpiredMedicationPermissions(currentDay)
        this.permissionService.removeExpiredNotePermissions(currentDay)
        this.permissionService.removeExpiredDiagnosisPermissions(currentDay)
        this.permissionService.removeExpiredExercisePermissions(currentDay)
    }


    /**
     ********************************** Medication **********************************
     */
    @GetMapping("/getMedicationOfMedicalRecord")
    @ResponseBody
    fun getMedicationCareProviderRelationById(
        mrId: String,
        cpId: String
    ): ResponseEntity<List<MedicationDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(mrId))

        if (medicalRecordDTO.isNotEmpty()) {
            val careProviderDTO: List<CareProviderDTO> = this.accountService.getCareProviderById(CareProviderId(cpId))

            if (careProviderDTO.isNotEmpty()) {
                //Check all permissions the care provider has
                val medicationCareProviderDTOs: List<MedicationCareProviderDTO> =
                    this.permissionService.getMedicationCareProviderRelationById(CareProviderId(cpId))

                //Return only the medication of the requested patient
                val medicationDTOs: ArrayList<MedicationDTO> = arrayListOf()
                for (medicationCareProviderDTO in medicationCareProviderDTOs) {
                    val medicationDTO: List<MedicationDTO> =
                        this.medicalRecordService.getMedicationByIdAndMedicalRecordId(
                            MedicationId(medicationCareProviderDTO.medId()),
                            MedicalRecordId(mrId)
                        )
                    medicationDTOs.add(medicationDTO[0])
                }

                return ResponseEntity.status(HttpStatus.OK).body(medicationDTOs)
            } else {
                throw NotExistingException("Care provider with id \'$cpId\' does not exist.")
            }
        } else {
            throw NotExistingException("Medical record with id \'$mrId\' does not exist.")
        }
    }

    @PostMapping("/createMedicationPermission")
    fun createMedicationCareProviderLink(
        medicationId: String,
        careProviderId: String,
        @RequestParam("validDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) validDate: LocalDate
    ): ResponseEntity<String> {
        val medicationDTO: List<MedicationDTO> = this.medicalRecordService.getMedicationById(MedicationId(medicationId))

        if (medicationDTO.isNotEmpty()) {
            val careProviderDTO: List<CareProviderDTO> = this.accountService.getCareProviderById(CareProviderId(careProviderId))

            if (careProviderDTO.isNotEmpty()) {
                this.permissionService.createPermissionLinkMedication(
                    MedicationId(medicationId),
                    CareProviderId(careProviderId),
                    validDate
                )

                return ResponseEntity.status(HttpStatus.OK).body("New permission for care provider with id \'$careProviderId\' for medication with id \'$medicationId\' successfully created.")
            } else {
                throw NotExistingException("Care provider with id \'$careProviderId\' does not exist.")
            }
        } else {
            throw NotExistingException("Medication with id \'$medicationId\' does not exist.")
        }
    }

    @DeleteMapping("/removeMedicationPermission")
    fun deleteMedicationPermission(
        medicationId: String,
        careProviderId: String
    ): ResponseEntity<String> {
        val medicationDTO: List<MedicationDTO> = this.medicalRecordService.getMedicationById(MedicationId(medicationId))

        if (medicationDTO.isNotEmpty()) {
            val careProviderDTO: List<CareProviderDTO> = this.accountService.getCareProviderById(CareProviderId(careProviderId))

            if (careProviderDTO.isNotEmpty()) {
                this.permissionService.removeSelectedMedicationPermission(
                    MedicationId(medicationId),
                    CareProviderId(careProviderId)
                )

                return ResponseEntity.status(HttpStatus.OK).body("Permission for care provider with id \'$careProviderId\' for medication with id \'$medicationId\' successfully removed.")
            } else {
                throw NotExistingException("Care provider with id \'$careProviderId\' does not exist.")
            }
        } else {
            throw NotExistingException("Medication with id \'$medicationId\' does not exist.")
        }
    }


    /**
     ********************************** Note **********************************
     */

    @GetMapping("/getNoteOfMedicalRecord")
    @ResponseBody
    fun getNoteCareProviderRelationById(
        mrId: String,
        cpId: String
    ): ResponseEntity<List<NoteDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(mrId))

        if (medicalRecordDTO.isNotEmpty()) {
            val careProviderDTO: List<CareProviderDTO> = this.accountService.getCareProviderById(CareProviderId(cpId))

            if (careProviderDTO.isNotEmpty()) {
                //Check all permissions the care provider has
                val noteCareProviderDTOs: List<NoteCareProviderDTO> =
                    this.permissionService.getNoteCareProviderRelationById(
                        CareProviderId(cpId)
                    )

                //Return only the note of the requested patient
                val noteDTOs: ArrayList<NoteDTO> = arrayListOf()
                for (noteCareProviderDTO in noteCareProviderDTOs) {
                    val noteDTO: List<NoteDTO> = this.medicalRecordService.getNoteByIdAndMedicalRecordId(
                        NoteId(noteCareProviderDTO.noteId()),
                        MedicalRecordId(mrId)
                    )
                    noteDTOs.add(noteDTO[0])
                }

                return ResponseEntity.status(HttpStatus.OK).body(noteDTOs)
            } else {
                throw NotExistingException("Care provider with id \'$cpId\' does not exist.")
            }
        } else {
            throw NotExistingException("Medical record with id \'$mrId\' does not exist.")
        }
    }

    @PostMapping("/createNotePermission")
    fun createNoteCareProviderLink(
        noteId: String,
        careProviderId: String,
        @RequestParam("validDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) validDate: LocalDate
    ) {
        this.permissionService.createPermissionLinkNote(
            NoteId(noteId),
            CareProviderId(careProviderId),
            validDate
        )
    }

    @DeleteMapping("/removeNotePermission")
    fun deleteNotePermission(
        noteId: String,
        careProviderId: String
    ) {
        this.permissionService.removeSelectedNotePermission(
            NoteId(noteId),
            CareProviderId(careProviderId)
        )
    }

    /**
     ********************************** Diagnosis **********************************
     */


    @GetMapping("/getDiagnosisOfMedicalRecord")
    @ResponseBody
    fun getDiagnosisCareProviderRelationById(
        mrId: String,
        cpId: String
    ): ResponseEntity<List<DiagnosisDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(mrId))

        if (medicalRecordDTO.isNotEmpty()) {
            val careProviderDTO: List<CareProviderDTO> = this.accountService.getCareProviderById(CareProviderId(cpId))

            if (careProviderDTO.isNotEmpty()) {
                //Check all permissions the care provider has
                val diagnosisCareProviderDTOs: List<DiagnosisCareProviderDTO> =
                    this.permissionService.getDiagnosisCareProviderRelationById(
                        CareProviderId(cpId)
                    )

                //Return only the medication of the requested patient
                val diagnosisDTOs: ArrayList<DiagnosisDTO> = arrayListOf()
                for (noteCareProviderDTO in diagnosisCareProviderDTOs) {
                    val diagnosisDTO: List<DiagnosisDTO> = this.medicalRecordService.getDiagnosisByIdAndMedicalRecordId(
                        DiagnosisId(noteCareProviderDTO.diagId()),
                        MedicalRecordId(mrId)
                    )
                    diagnosisDTOs.add(diagnosisDTO[0])
                }

                return ResponseEntity.status(HttpStatus.OK).body(diagnosisDTOs)
            } else {
                throw NotExistingException("Care provider with id \'$cpId\' does not exist.")
            }
        } else {
            throw NotExistingException("Medical record with id \'$mrId\' does not exist.")
        }
    }

    @PostMapping("/createDiagnosisPermission")
    fun createDiagnosisCareProviderLink(
        diagnosisId: String,
        careProviderId: String,
        @RequestParam("validDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) validDate: LocalDate
    ) {
        this.permissionService.createPermissionLinkDiagnosis(
            DiagnosisId(diagnosisId),
            CareProviderId(careProviderId),
            validDate
        )
    }

    @DeleteMapping("/removeDiagnosisPermission")
    fun deleteDiagnosisPermission(
        diagnosisId: String,
        careProviderId: String
    ) {
        this.permissionService.removeSelectedDiagnosisPermission(
            DiagnosisId(diagnosisId),
            CareProviderId(careProviderId)
        )
    }

    /**
     ********************************** Exercises **********************************
     */


    @GetMapping("/getExerciseOfMedicalRecord")
    @ResponseBody
    fun getExerciseCareProviderRelationById(
        mrId: String,
        cpId: String
    ): ResponseEntity<List<ExerciseDTO>> {
        val medicalRecordDTO: List<MedicalRecordDTO> = this.medicalRecordService.getMedicalRecord(MedicalRecordId(mrId))

        if (medicalRecordDTO.isNotEmpty()) {
            val careProviderDTO: List<CareProviderDTO> = this.accountService.getCareProviderById(CareProviderId(cpId))

            if (careProviderDTO.isNotEmpty()) {
                //Check all permissions the care provider has
                val exerciseCareProviderDTOs: List<ExerciseCareProviderDTO> =
                    this.permissionService.getExerciseCareProviderRelationById(
                        CareProviderId(cpId)
                    )

                //Return only the medication of the requested patient
                val exerciseDTOs: ArrayList<ExerciseDTO> = arrayListOf()
                for (exerciseCareProviderDTO in exerciseCareProviderDTOs) {
                    val exerciseDTO: List<ExerciseDTO> = this.medicalRecordService.getExerciseByIdAndMedicalRecordId(
                        ExerciseId(exerciseCareProviderDTO.exerId()),
                        MedicalRecordId(mrId)
                    )
                    exerciseDTOs.add(exerciseDTO[0])
                }

                return ResponseEntity.status(HttpStatus.OK).body(exerciseDTOs)

            } else {
                throw NotExistingException("Care provider with id \'$cpId\' does not exist.")
            }
        } else {
            throw NotExistingException("Medical record with id \'$mrId\' does not exist.")
        }
    }

    @PostMapping("/createExercisePermission")
    fun createExerciseCareProviderLink(
        exerciseId: String,
        careProviderId: String,
        @RequestParam("validDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) validDate: LocalDate
    ) {
        this.permissionService.createExerciseLinkDiagnosis(
            ExerciseId(exerciseId),
            CareProviderId(careProviderId),
            validDate
        )
    }

    @DeleteMapping("/removeExercisePermission")
    fun deleteExercisePermission(
        exerciseId: String,
        careProviderId: String
    ) {
        this.permissionService.removeSelectedExercisePermission(
            ExerciseId(exerciseId),
            CareProviderId(careProviderId)
        )
    }
}