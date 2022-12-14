package nl.hva.backend.rest

import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CommonExceptionHandler {

    /**
     * ExceptionHandler for not existing content (e.g., getting patient by invalid id)
     */
    @ExceptionHandler(NotExistingException::class)
    fun handleNotExistingException(e: NotExistingException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
    }

}