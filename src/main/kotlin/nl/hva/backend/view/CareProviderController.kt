package nl.hva.backend.view

import nl.hva.backend.application.api.CareProviderService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.value_objects.Specialism
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

@Controller
@RequestMapping("care-provider")
class CareProviderController {

    @Autowired
    private lateinit var careProviderService: CareProviderService


    @GetMapping("overview")
    fun careProviderOverview(model: Model): ModelAndView {
        // get list of existing CPs & add them to the model to display them in the overview
        val careProviders: List<CareProviderDTO> = this.careProviderService.getAllAccounts()
        model.addAttribute("careProviders", careProviders)

        // navigate to patientOverview.html in resources > templates > careProvider
        return ModelAndView("careProvider/careProviderOverview")
    }

    @GetMapping("create")
    fun createCareProvider(model: Model): ModelAndView {
        // navigate to createPatient.html in resources > templates > careProvider
        return ModelAndView("careProvider/createCareProvider")
    }

    @PostMapping("create")
    fun submitCreateCareProvider(
            @RequestParam("first_name") firstName: String,
            @RequestParam("last_name") lastName: String,
            @RequestParam("street") street: String,
            @RequestParam("zip") zip: String,
            @RequestParam("city") city: String,
            @RequestParam("country") country: String,
            @RequestParam("phone_number") phoneNumber: String,
            @RequestParam("specialism") specialism: String,
            redirectAttributes: RedirectAttributes
    ): RedirectView {
        // use careProviderService to create a new CP account
        this.careProviderService.createAccount(
                firstName,
                lastName,
                street,
                zip,
                city,
                country,
                phoneNumber,
                Specialism.valueOf(specialism)
        )

        // add new flash attribute to show the state in a card
        redirectAttributes.addFlashAttribute("successfulAction", "create")

        // redirect to @GetMapping("overview")
        return RedirectView("overview")
    }

    @PostMapping("edit")
    fun redirectToEditCareProvider(
            @RequestParam("care_provider_id") careProviderId: String,
            redirectAttributes: RedirectAttributes
    ): RedirectView {
        // add new flash attribute (CP id) to forward it to the next page
        redirectAttributes.addFlashAttribute("careProviderId", careProviderId)

        // redirect to @GetMapping("edit")
        return RedirectView("edit")
    }

    @GetMapping("edit")
    fun editCareProvider(
            model: Model
    ): ModelAndView {
        // create CP id and find the right CP to edit
        val careProviderId = CareProviderId(model.getAttribute("careProviderId") as String)
        val careProvider = this.careProviderService.getAccountById(careProviderId)

        // add the CP to the model
        model.addAttribute("careProvider", careProvider)

        // navigate to editCareProvider.html in resources > templates > careProvider
        return ModelAndView("careProvider/editCareProvider")
    }

    @PostMapping("edit/submit")
    fun submitEditCareProvider(
            @RequestParam("care_provider_id") careProviderId: String,
            @RequestParam("first_name") firstName: String,
            @RequestParam("last_name") lastName: String,
            @RequestParam("street") street: String,
            @RequestParam("zip") zip: String,
            @RequestParam("city") city: String,
            @RequestParam("country") country: String,
            @RequestParam("phone_number") phoneNumber: String,
            @RequestParam("specialism") specialism: String,
            redirectAttributes: RedirectAttributes
    ): RedirectView {
        // use careProviderService to edit the CP account
        this.careProviderService.editAccount(
                CareProviderId(careProviderId),
                firstName,
                lastName,
                street,
                zip,
                city,
                country,
                phoneNumber,
                Specialism.valueOf(specialism)
        )

        // add new flash attribute to show the state in a card
        redirectAttributes.addFlashAttribute("successfulAction", "edit")

        // redirect to @GetMapping("overview")
        return RedirectView("/care-provider/overview")
    }

    @PostMapping("delete")
    fun deleteCareProvider(
            @RequestParam("care_provider_id") id: String,
            redirectAttributes: RedirectAttributes
    ): RedirectView {
        // use careProviderService to delete the CP account
        this.careProviderService.deleteAccount(CareProviderId(id))

        // add new flash attribute to show the state in a card
        redirectAttributes.addFlashAttribute("successfulAction", "delete")

        // redirect to @GetMapping("overview")
        return RedirectView("overview")
    }
}