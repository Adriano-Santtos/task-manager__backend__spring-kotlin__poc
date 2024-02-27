package poc.taskmanager.taskmanagement.controller.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import poc.taskmanager.taskmanagement.exceptions.StatusNotAllowedException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(StatusNotAllowedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleStatusNotAllowedException(e: StatusNotAllowedException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
    }
}
