package kr.happynewyear.configuration.exception

import jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/error")
class ErrorRedirectedController : ErrorController {

    @GetMapping
    fun on(req: HttpServletRequest): String {
        val code = req.getAttribute(ERROR_STATUS_CODE)?.let { Integer.valueOf(it.toString()) } ?: 200
        val status = HttpStatus.valueOf(code)
        return status.toString()
    }

}
