package nl.hva.backend.rest

import nl.hva.backend.application.api.CalendarService
import nl.hva.backend.application.api.CareProviderService
import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.AppointmentDTO
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.rest.exceptions.InvalidParameterException
import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/rest/calendar/")
class CalendarRestController {

    @Autowired
    private lateinit var calendarService: CalendarService

    @Autowired
    private lateinit var patientService: PatientService

    @Autowired
    private lateinit var generalPractitionerService: GeneralPractitionerService

    @Autowired
    private lateinit var careProviderService: CareProviderService

    // SwaggerUI: LocalDateTime must be passed in the format '2022-12-12T14:00:00.000'
    @PostMapping("/createAppointment")
    fun createAppointment(
        @RequestParam("dateTime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) dateTime: LocalDateTime,
        reason: String,
        patientId: String,
        @RequestParam(required = false) gpId: String? = null,
        @RequestParam(required = false) cpId: String? = null
    ): ResponseEntity<String> {
        val patientDTO: List<PatientDTO> = this.patientService.getAccountById(PatientId(patientId))

        if (patientDTO.isEmpty()) {
            throw NotExistingException("Patient with id \'$patientId\' does not exist.")
        }

        if (gpId.isNullOrBlank() && !cpId.isNullOrBlank()) {
            val careProviderDTO: List<CareProviderDTO> = this.careProviderService.getAccountById(CareProviderId(cpId))

            if (careProviderDTO.isNotEmpty()) {
                this.calendarService.createAppointment(dateTime, reason, patientId, null, cpId)

                return ResponseEntity.status(HttpStatus.OK).body("New appointment for patient with id \'$patientId\' & care provider with id \'$cpId\' successfully created.")
            } else {
                throw NotExistingException("Care provider with id \'$cpId\' does not exist.")
            }
        } else if (!gpId.isNullOrBlank() && cpId.isNullOrBlank()) {
            val generalPractitionerDTO: List<GeneralPractitionerDTO> = this.generalPractitionerService.getAccountById(GeneralPractitionerId(gpId))

            if (generalPractitionerDTO.isNotEmpty()) {
                this.calendarService.createAppointment(dateTime, reason, patientId, gpId, null)

                return ResponseEntity.status(HttpStatus.OK).body("New appointment for patient with id \'$patientId\' & general practitioner with id \'$gpId\' successfully created.")
            } else {
                throw NotExistingException("Care provider with id \'$gpId\' does not exist.")
            }
        } else if (!gpId.isNullOrBlank() && !cpId.isNullOrBlank()) {
            throw InvalidParameterException("Only one id (general practitioner or care provider) may be set.")
        } else {
            throw InvalidParameterException("Either the general practitioner id or the care provider id must be set.")
        }
    }

    @PostMapping("/cancelAppointment/{id}")
    fun cancelAppointment(
        @PathVariable("id") id: String
    ) {
        this.calendarService.cancelAppointment(AppointmentId(id))
    }

    @GetMapping("/getAllAppointmentsByPatientId/{id}")
    @ResponseBody
    fun getAllAppointmentsByPatientId(
        @PathVariable("id") id: String
    ): List<AppointmentDTO> {
        return this.calendarService.getAllAppointmentsByPatientId(PatientId(id))
    }

    @GetMapping("/getAllAppointmentsByGeneralPractitionerId/{id}")
    @ResponseBody
    fun getAllAppointmentsByGeneralPractitionerId(
        @PathVariable("id") id: String
    ): List<AppointmentDTO> {
        return this.calendarService.getAllAppointmentsByGeneralPractitionerId(GeneralPractitionerId(id))
    }

    @GetMapping("/getAllAppointmentsByCareProviderId/{id}")
    @ResponseBody
    fun getAllAppointmentsByCareProviderId(
        @PathVariable("id") id: String
    ): List<AppointmentDTO> {
        return this.calendarService.getAllAppointmentsByCareProviderId(CareProviderId(id))
    }
}