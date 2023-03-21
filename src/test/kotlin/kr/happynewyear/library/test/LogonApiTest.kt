package kr.happynewyear.library.test

import kr.happynewyear.authentication.application.service.AccountService
import org.junit.jupiter.api.BeforeEach
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@Import(RequestBuilder::class, RequestPerformer::class)
abstract class LogonApiTest {

    private val log = LoggerFactory.getLogger(javaClass)


    @Autowired
    private lateinit var b: RequestBuilder

    @Autowired
    private lateinit var p: RequestPerformer


    @Autowired
    private lateinit var accountService: AccountService // TODO user

    private lateinit var accessToken: String


    @BeforeEach
    fun setup() {
        loginWithNewAccount()
    }

    protected fun loginWithNewAccount() {
        log.debug("Login started from create account...")
        val email = "${UUID.randomUUID().toString().substring(8)}@email.com"
        val password = "password"
        accountService.create(email, password)
        val token = accountService.login(email, password)
        accessToken = token.accessToken
        log.debug("Login finished.")
    }


    protected final fun run(
        method: HttpMethod, url: String,
        status: HttpStatus
    ) {
        p.perform(
            b.jwt(b.build(method, url), accessToken),
            status
        )
    }

    protected final fun run(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ) {
        p.perform(
            b.jwt(b.json(b.build(method, url), requestBody), accessToken),
            status
        )
    }


    protected final fun redirect(
        method: HttpMethod, url: String,
        status: HttpStatus
    ): String {
        return p.performAndRedirect(
            b.jwt(b.build(method, url), accessToken),
            status
        )
    }

    protected final fun redirect(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ): String {
        return p.performAndRedirect(
            b.jwt(b.json(b.build(method, url), requestBody), accessToken),
            status
        )
    }


    protected final fun call(
        method: HttpMethod, url: String,
        status: HttpStatus
    ): String {
        return p.performAndReturn(
            b.jwt(b.build(method, url), accessToken),
            status
        )
    }

    protected final fun call(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ): String {
        return p.performAndReturn(
            b.jwt(b.json(b.build(method, url), requestBody), accessToken),
            status
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return p.performAndUnmarshal(
            b.jwt(b.build(method, url), accessToken),
            status, responseType
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return p.performAndUnmarshal(
            b.jwt(b.json(b.build(method, url), requestBody), accessToken),
            status, responseType
        )
    }

}
