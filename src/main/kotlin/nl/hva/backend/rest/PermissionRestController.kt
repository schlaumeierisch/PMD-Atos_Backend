package nl.hva.backend.rest

import nl.hva.backend.application.api.PermissionService
import nl.hva.backend.application.dto.DiagnosisDTO
import nl.hva.backend.application.dto.ExerciseDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.application.dto.many_to_many.DiagnosisCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.ExerciseCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.MedicationCareProviderDTO
import nl.hva.backend.application.dto.many_to_many.NoteCareProviderDTO
import nl.hva.backend.domain.ids.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/rest/permissions")
class PermissionRestController {

    @Autowired
    private lateinit var permissionService: PermissionService

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
    ): List<MedicationDTO> {
        //Check all permissions the care provider has
        val medicineCareProviderDTOs: List<MedicationCareProviderDTO> =
            this.permissionService.getMedicationCareProviderRelationById(
                CareProviderId(cpId)
            )

        //Return only the medication of the requested patient
        val medicationDTOs: ArrayList<MedicationDTO> = arrayListOf()
        for (medicineCareProviderDTO in medicineCareProviderDTOs) {
            medicationDTOs.add(
                this.permissionService.getMedicationByIdAndMr(
                    MedicationId(medicineCareProviderDTO.medId()),
                    MedicalRecordId(mrId)
                )
            )
        }
        return medicationDTOs
    }

    @PostMapping("/createMedicationPermission")
    fun createMedicationCareProviderLink(
        medicationId: String,
        careProviderId: String,
        @RequestParam("validDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) validDate: LocalDate
    ) {
        this.permissionService.createPermissionLinkMedication(
            MedicationId(medicationId),
            CareProviderId(careProviderId),
            validDate
        )
    }

    @DeleteMapping("/removeMedicationPermission")
    fun deleteMedicationPermission(
        medicationId: String,
        careProviderId: String
    ) {
        this.permissionService.removeSelectedMedicationPermission(
            MedicationId(medicationId),
            CareProviderId(careProviderId)
        )
    }


    /**
     ********************************** Note **********************************
     */

    @GetMapping("/getNoteOfMedicalRecord")
    @ResponseBody
    fun getNoteCareProviderRelationById(
        mrId: String,
        cpId: String
    ): List<NoteDTO> {
        //Check all permissions the care provider has
        val noteCareProviderDTOs: List<NoteCareProviderDTO> =
            this.permissionService.getNoteCareProviderRelationById(
                CareProviderId(cpId)
            )

        //Return only the note of the requested patient
        val noteDTOs: ArrayList<NoteDTO> = arrayListOf()
        for (noteCareProviderDTO in noteCareProviderDTOs) {
            noteDTOs.add(
                this.permissionService.getNoteByIdAndMr(
                    NoteId(noteCareProviderDTO.noteId()),
                    MedicalRecordId(mrId)
                )
            )
        }
        return noteDTOs
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
    ): List<DiagnosisDTO> {
        //Check all permissions the care provider has
        val diagnosisCareProviderDTOs: List<DiagnosisCareProviderDTO> =
            this.permissionService.getDiagnosisCareProviderRelationById(
                CareProviderId(cpId)
            )

        //Return only the medication of the requested patient
        val diagnosisDTOs: ArrayList<DiagnosisDTO> = arrayListOf()
        for (noteCareProviderDTO in diagnosisCareProviderDTOs) {
            diagnosisDTOs.add(
                this.permissionService.getDiagnosisByIdAndMr(
                    DiagnosisId(noteCareProviderDTO.diagId()),
                    MedicalRecordId(mrId)
                )
            )
        }
        return diagnosisDTOs
    }

    @PostMapping("/createDiagnosisPermission")
    fun createDiagnosisCareProviderLink(
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
    ): List<ExerciseDTO> {
        //Check all permissions the care provider has
        val exerciseCareProviderDTOs: List<ExerciseCareProviderDTO> =
            this.permissionService.getExerciseCareProviderRelationById(
                CareProviderId(cpId)
            )

        //Return only the medication of the requested patient
        val exerciseDTOs: ArrayList<ExerciseDTO> = arrayListOf()
        for (exerciseCareProviderDTO in exerciseCareProviderDTOs) {
            exerciseDTOs.add(
                this.permissionService.getExerciseByIdAndMr(
                    ExerciseId(exerciseCareProviderDTO.exerId()),
                    MedicalRecordId(mrId)
                )
            )
        }
        return exerciseDTOs
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