package nl.hva.backend.rest

import nl.hva.backend.application.api.CalendarService
import nl.hva.backend.application.dto.AppointmentDTO
import nl.hva.backend.domain.ids.AppointmentId
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/rest/calendar/")
class CalendarRestController {

    @Autowired
    private lateinit var calendarService: CalendarService

    @PostMapping("/createAppointment")
    fun createAppointment(
        @RequestParam("dateTime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) dateTime: LocalDateTime,
        reason: String,
        patientId: String,
        @RequestParam(required = false) gpId: String? = null,
        @RequestParam(required = false) cpId: String? = null
    ) {
        this.calendarService.createAppointment(dateTime, reason, patientId, gpId, cpId)
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