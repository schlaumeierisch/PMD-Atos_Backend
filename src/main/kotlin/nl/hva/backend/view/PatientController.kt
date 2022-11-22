package nl.hva.backend.view

import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.PatientDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("patient")
class PatientController {

    @Autowired
    private lateinit var patientService: PatientService


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
        // navigate to createPatient.html in resources > templates > patient
        return ModelAndView("patient/createPatient")
    }
//
//    @PostMapping("create")
//    fun submitCreateCareProvider(
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
//        // use patientService to create a new CP account
//        this.patientService.createAccount(
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
//        redirectAttributes.addFlashAttribute("successfulAction", "create")
//
//        // redirect to @GetMapping("overview")
//        return RedirectView("overview")
//    }
//
//    @PostMapping("edit")
//    fun redirectToEditCareProvider(
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
//    fun editCareProvider(
//            model: Model
//    ): ModelAndView {
//        // create CP id and find the right CP to edit
//        val careProviderId = CareProviderId(model.getAttribute("careProviderId") as String)
//        val careProvider = this.patientService.getAccountById(careProviderId)
//
//        // add the CP to the model
//        model.addAttribute("careProvider", careProvider)
//
//        // navigate to editPatient.html in resources > templates > careProvider
//        return ModelAndView("careProvider/editCareProvider")
//    }
//
//    @PostMapping("edit/submit")
//    fun submitEditCareProvider(
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
//                CareProviderId(careProviderId),
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
//    fun deleteCareProvider(
//            @RequestParam("care_provider_id") id: String,
//            redirectAttributes: RedirectAttributes
//    ): RedirectView {
//        // use patientService to delete the CP account
//        this.patientService.deleteAccount(CareProviderId(id))
//
//        // add new flash attribute to show the state in a card
//        redirectAttributes.addFlashAttribute("successfulAction", "delete")
//
//        // redirect to @GetMapping("overview")
//        return RedirectView("overview")
//    }
}