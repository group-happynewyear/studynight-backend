package kr.happynewyear.library.test

import kr.happynewyear.library.utility.JsonIO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestComponent
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.isEqualTo

@TestComponent
class RequestPerformer {

    private val log = LoggerFactory.getLogger(javaClass)


    @Autowired
    private lateinit var mockMvc: MockMvc


    fun perform(
        request: MockHttpServletRequestBuilder,
        status: HttpStatus
    ): ResultActions {
        return try {
            log.debug("Performing...")
            val result = mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isEqualTo(status.value()))
            log.debug("Performed.")
            result
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun performAndRedirect(
        request: MockHttpServletRequestBuilder,
        status: HttpStatus
    ): String {
        return try {
            perform(request, status)
                .andReturn().response.getHeader(HttpHeaders.LOCATION)!!
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun performAndReturn(
        request: MockHttpServletRequestBuilder,
        status: HttpStatus
    ): String {
        return try {
            perform(request, status)
                .andReturn().response.contentAsString
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun <T> performAndUnmarshal(
        request: MockHttpServletRequestBuilder,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return try {
            val responseBody = performAndReturn(request, status)
            JsonIO.read(responseBody, responseType)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}
