package nl.hva.backend.view

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.value_objects.Gender
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
import java.time.LocalDate

@Controller
@RequestMapping("patient")
class PatientController {

    @Autowired
    private lateinit var patientService: PatientService

    @Autowired
    private lateinit var generalPractitionerService: GeneralPractitionerService


    @GetMapping("overview")
    fun patientOverview(model: Model): ModelAndView {
        // get list of existing CPs & add them to the model to display them in the overview
        val patients: List<PatientDTO> = this.patientService.getAllAccounts()
        model.addAttribute("patients", patients)

        // navigate to patientOverview.html in resources > templates > careProvider
        return ModelAndView("patient/patientOverview")
    }

    @GetMapping("create")
    fun createPatient(model: Model): ModelAndView {
        // get list of existing GPs & add them to the model to connect them with the new patient
        val generalPractitioners: List<GeneralPractitionerDTO> = this.generalPractitionerService.getAllAccounts()
        model.addAttribute("generalPractitioners", generalPractitioners)

        // navigate to createPatient.html in resources > templates > patient
        return ModelAndView("patient/createPatient")
    }

    @PostMapping("create")
    fun submitCreatePatient(
        @RequestParam("first_name") firstName: String,
        @RequestParam("last_name") lastName: String,
        @RequestParam("street") street: String,
        @RequestParam("zip") zip: String,
        @RequestParam("city") city: String,
        @RequestParam("country") country: String,
        @RequestParam("gender") gender: String,
        @RequestParam("birth_date") birthDate: String,
        @RequestParam("phone_number") phoneNumber: String,
        @RequestParam("email") email: String,
        @RequestParam("general_practitioner") gpId: String,
        redirectAttributes: RedirectAttributes
    ): RedirectView {
        // use patientService to create a new CP account
        this.patientService.createAccount(
            firstName,
            lastName,
            street,
            zip,
            city,
            country,
            Gender.valueOf(gender),
            LocalDate.parse(birthDate),
            phoneNumber,
            email,
            true,
            gpId
        )

        // add new flash attribute to show the state in a card
        redirectAttributes.addFlashAttribute("successfulAction", "create")

        // redirect to @GetMapping("overview")
        return RedirectView("overview")
    }
//
//    @PostMapping("edit")
//    fun redirectToEditPatient(
//            @RequestParam("care_provider_id") careProviderId: String,
//            redirectAttributes: RedirectAttributes
//    ): RedirectView {
//        // add new flash attribute (CP id) to forward it to the next page
//        redirectAttributes.addFlashAttribute("careProviderId", careProviderId)
//
//        // redirect to @GetMapping("edit")
//        return RedirectView("edit")
//    }
//
//    @GetMapping("edit")
//    fun editPatient(
//            model: Model
//    ): ModelAndView {
//        // create CP id and find the right CP to edit
//        val careProviderId = PatientId(model.getAttribute("careProviderId") as String)
//        val careProvider = this.patientService.getAccountById(careProviderId)
//
//        // add the CP to the model
//        model.addAttribute("careProvider", careProvider)
//
//        // navigate to editPatient.html in resources > templates > careProvider
//        return ModelAndView("careProvider/editPatient")
//    }
//
//    @PostMapping("edit/submit")
//    fun submitEditPatient(
//            @RequestParam("care_provider_id") careProviderId: String,
//            @RequestParam("first_name") firstName: String,
//            @RequestParam("last_name") lastName: String,
//            @RequestParam("street") street: String,
//            @RequestParam("zip") zip: String,
//            @RequestParam("city") city: String,
//            @RequestParam("country") country: String,
//            @RequestParam("phone_number") phoneNumber: String,
//            @RequestParam("specialism") specialism: String,
//            redirectAttributes: RedirectAttributes
//    ): RedirectView {
//        // use patientService to edit the CP account
//        this.patientService.editAccount(
//                PatientId(careProviderId),
//                firstName,
//                lastName,
//                street,
//                zip,
//                city,
//                country,
//                phoneNumber,
//                Specialism.valueOf(specialism)
//        )
//
//        // add new flash attribute to show the state in a card
//        redirectAttributes.addFlashAttribute("successfulAction", "edit")
//
//        // redirect to @GetMapping("overview")
//        return RedirectView("/care-provider/overview")
//    }
//
//    @PostMapping("delete")
//    fun deletePatient(
//            @RequestParam("care_provider_id") id: String,
//            redirectAttributes: RedirectAttributes
//    ): RedirectView {
//        // use patientService to delete the CP account
//        this.patientService.deleteAccount(PatientId(id))
//
//        // add new flash attribute to show the state in a card
//        redirectAttributes.addFlashAttribute("successfulAction", "delete")
//
//        // redirect to @GetMapping("overview")
//        return RedirectView("overview")
//    }
}