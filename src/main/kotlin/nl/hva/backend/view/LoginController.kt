package nl.hva.backend.view

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/")
class LoginController {

    @GetMapping("login")
    fun login(): ModelAndView {
        // navigate to login.html in resources > templates
        return ModelAndView("login")
    }

    @GetMapping("/")
    fun index(): ModelAndView {
        // navigate to index.html in resources > templates
        return ModelAndView("index")
    }
}