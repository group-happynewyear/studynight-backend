package kr.happynewyear.api

import com.fasterxml.jackson.databind.ObjectMapper
import kr.happynewyear.authentication.application.service.AccountService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders.LOCATION
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.isEqualTo

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
abstract class ApiTest {

    private val log = LoggerFactory.getLogger(javaClass)


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper


    @Autowired
    private lateinit var accountService: AccountService


    protected final fun loginNewAccount(): String {
        log.debug("Login started from create account...")
        val email = "test@email.com"
        val password = "password"
        accountService.create(email, password)
        val token = accountService.login(email, password)
        log.debug("Login finished.")
        return token.accessToken
    }


    protected final fun run(
        method: HttpMethod, url: String,
        status: HttpStatus
    ) {
        perform(
            build(method, url),
            status
        )
    }

    protected final fun run(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus
    ) {
        perform(
            jwt(build(method, url), jwt),
            status
        )
    }

    protected final fun run(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ) {
        perform(
            json(build(method, url), requestBody),
            status
        )
    }

    protected final fun run(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus
    ) {
        perform(
            jwt(json(build(method, url), requestBody), jwt),
            status
        )
    }


    protected final fun redirect(
        method: HttpMethod, url: String,
        status: HttpStatus
    ): String {
        return performAndRedirect(
            build(method, url),
            status
        )
    }

    protected final fun redirect(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus
    ): String {
        return performAndRedirect(
            jwt(build(method, url), jwt),
            status
        )
    }

    protected final fun redirect(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ): String {
        return performAndRedirect(
            json(build(method, url), requestBody),
            status
        )
    }

    protected final fun redirect(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus
    ): String {
        return performAndRedirect(
            jwt(json(build(method, url), requestBody), jwt),
            status
        )
    }


    protected final fun call(
        method: HttpMethod, url: String,
        status: HttpStatus
    ): String {
        return performAndReturn(
            build(method, url),
            status
        )
    }

    protected final fun call(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus
    ): String {
        return performAndReturn(
            jwt(build(method, url), jwt),
            status
        )
    }

    protected final fun call(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ): String {
        return performAndReturn(
            json(build(method, url), requestBody),
            status
        )
    }

    protected final fun call(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus
    ): String {
        return performAndReturn(
            jwt(json(build(method, url), requestBody), jwt),
            status
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return performAndUnmarshal(
            build(method, url),
            status, responseType
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return performAndUnmarshal(
            jwt(build(method, url), jwt),
            status, responseType
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return performAndUnmarshal(
            json(build(method, url), requestBody),
            status, responseType
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return performAndUnmarshal(
            jwt(json(build(method, url), requestBody), jwt),
            status, responseType
        )
    }


    private fun build(method: HttpMethod, url: String): MockHttpServletRequestBuilder {
        return MockMvcRequestBuilders.request(method, url)
    }

    private fun json(request: MockHttpServletRequestBuilder, requestBody: Any): MockHttpServletRequestBuilder {
        return request.contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(requestBody))
    }

    private fun jwt(request: MockHttpServletRequestBuilder, jwt: String): MockHttpServletRequestBuilder {
        return request.header("Authorization", "Bearer $jwt")
    }


    private fun perform(
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

    private fun performAndRedirect(
        request: MockHttpServletRequestBuilder,
        status: HttpStatus
    ): String {
        return try {
            perform(request, status)
                .andReturn().response.getHeader(LOCATION)!!
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun performAndReturn(
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

    private fun <T> performAndUnmarshal(
        request: MockHttpServletRequestBuilder,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return try {
            val responseBody = performAndReturn(request, status)
            objectMapper.readValue(responseBody, responseType)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}
