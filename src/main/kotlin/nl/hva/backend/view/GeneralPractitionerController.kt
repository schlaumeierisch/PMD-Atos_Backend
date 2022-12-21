package nl.hva.backend.view

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.domain.ids.GeneralPractitionerId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.time.LocalTime

@Controller
@RequestMapping("general-practitioner")
class GeneralPractitionerController {

    @Autowired
    private lateinit var generalPractitionerService: GeneralPractitionerService


    @GetMapping("overview")
    fun generalPractitionerOverview(model: Model): ModelAndView {
        // get list of existing GPs & add them to the model to display them in the overview
        val generalPractitioners: List<GeneralPractitionerDTO> = this.generalPractitionerService.getAllAccounts()
        model.addAttribute("generalPractitioners", generalPractitioners)

        // navigate to generalPractitionerOverview.html in resources > templates > generalPractitioner
        return ModelAndView("generalPractitioner/generalPractitionerOverview")
    }

    @GetMapping("create")
    fun createGeneralPractitioner(model: Model): ModelAndView {
        // navigate to createGeneralPractitioner.html in resources > templates > generalPractitioner
        return ModelAndView("generalPractitioner/createGeneralPractitioner")
    }

    @PostMapping("create")
    fun submitCreateGeneralPractitioner(
        @RequestParam("first_name") firstName: String,
        @RequestParam("last_name") lastName: String,
        @RequestParam("street") street: String,
        @RequestParam("zip") zip: String,
        @RequestParam("city") city: String,
        @RequestParam("country") country: String,
        @RequestParam("phone_number") phoneNumber: String,
        @RequestParam("start_time_shift") startTimeShift: LocalTime,
        @RequestParam("end_time_shift") endTimeShift: LocalTime,
        @RequestParam("break_times") breakTimes: String,
        @RequestParam("break_duration") breakDuration: Long,
        @RequestParam("appointment_duration") appointmentDuration: Long,
        redirectAttributes: RedirectAttributes
    ): RedirectView {
        // use generalPractitionerService to create a new GP account
        this.generalPractitionerService.createAccount(
            firstName,
            lastName,
            street,
            zip,
            city,
            country,
            phoneNumber,
            startTimeShift,
            endTimeShift,
            breakTimes,
            breakDuration,
            appointmentDuration
        )

        // add new flash attribute to show the state in a card
        redirectAttributes.addFlashAttribute("successfulAction", "create")

        // redirect to @GetMapping("overview")
        return RedirectView("overview")
    }

    @PostMapping("edit")
    fun redirectToEditGeneralPractitioner(
        @RequestParam("general_practitioner_id") generalPractitionerId: String,
        redirectAttributes: RedirectAttributes
    ): RedirectView {
        // add new flash attribute (GP id) to forward it to the next page
        redirectAttributes.addFlashAttribute("generalPractitionerId", generalPractitionerId)

        // redirect to @GetMapping("edit")
        return RedirectView("edit")
    }

    @GetMapping("edit")
    fun editGeneralPractitioner(
        model: Model
    ): ModelAndView {
        // create GP id and find the right GP to edit
        val generalPractitionerId = GeneralPractitionerId(model.getAttribute("generalPractitionerId") as String)
        val generalPractitioner = this.generalPractitionerService.getAccountById(generalPractitionerId)

        // add the GP to the model
        model.addAttribute("generalPractitioner", generalPractitioner)

        // navigate to editGeneralPractitioner.html in resources > templates > generalPractitioner
        return ModelAndView("generalPractitioner/editGeneralPractitioner")
    }

    @PostMapping("edit/submit")
    fun submitEditGeneralPractitioner(
        @RequestParam("general_practitioner_id") generalPractitionerId: String,
        @RequestParam("first_name") firstName: String,
        @RequestParam("last_name") lastName: String,
        @RequestParam("street") street: String,
        @RequestParam("zip") zip: String,
        @RequestParam("city") city: String,
        @RequestParam("country") country: String,
        @RequestParam("phone_number") phoneNumber: String,
        @RequestParam("start_time_shift") startTimeShift: LocalTime,
        @RequestParam("end_time_shift") endTimeShift: LocalTime,
        @RequestParam("break_times") breakTimes: String,
        @RequestParam("break_duration") breakDuration: Long,
        @RequestParam("appointment_duration") appointmentDuration: Long,
        redirectAttributes: RedirectAttributes
    ): RedirectView {
        // use generalPractitionerService to edit the GP account
        this.generalPractitionerService.editAccount(
            GeneralPractitionerId(generalPractitionerId),
            firstName,
            lastName,
            street,
            zip,
            city,
            country,
            phoneNumber,
            startTimeShift,
            endTimeShift,
            breakTimes,
            breakDuration,
            appointmentDuration
        )
