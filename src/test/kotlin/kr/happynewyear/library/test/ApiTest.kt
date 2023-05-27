package kr.happynewyear.library.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@Import(RequestBuilder::class, RequestPerformer::class, PortableMqTestConfiguration::class)
abstract class ApiTest {

    @Autowired
    private lateinit var b: RequestBuilder

    @Autowired
    private lateinit var p: RequestPerformer


    protected final fun run(
        method: HttpMethod, url: String,
        status: HttpStatus
    ) {
        p.perform(
            b.build(method, url),
            status
        )
    }

    protected final fun run(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus
    ) {
        p.perform(
            b.jwt(b.build(method, url), jwt),
            status
        )
    }

    protected final fun run(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ) {
        p.perform(
            b.json(b.build(method, url), requestBody),
            status
        )
    }

    protected final fun run(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus
    ) {
        p.perform(
            b.jwt(b.json(b.build(method, url), requestBody), jwt),
            status
        )
    }


    protected final fun redirect(
        method: HttpMethod, url: String,
        status: HttpStatus
    ): String {
        return p.performAndRedirect(
            b.build(method, url),
            status
        )
    }

    protected final fun redirect(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus
    ): String {
        return p.performAndRedirect(
            b.jwt(b.build(method, url), jwt),
            status
        )
    }

    protected final fun redirect(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ): String {
        return p.performAndRedirect(
            b.json(b.build(method, url), requestBody),
            status
        )
    }

    protected final fun redirect(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus
    ): String {
        return p.performAndRedirect(
            b.jwt(b.json(b.build(method, url), requestBody), jwt),
            status
        )
    }


    protected final fun call(
        method: HttpMethod, url: String,
        status: HttpStatus
    ): String {
        return p.performAndReturn(
            b.build(method, url),
            status
        )
    }

    protected final fun call(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus
    ): String {
        return p.performAndReturn(
            b.jwt(b.build(method, url), jwt),
            status
        )
    }

    protected final fun call(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus
    ): String {
        return p.performAndReturn(
            b.json(b.build(method, url), requestBody),
            status
        )
    }

    protected final fun call(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus
    ): String {
        return p.performAndReturn(
            b.jwt(b.json(b.build(method, url), requestBody), jwt),
            status
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return p.performAndUnmarshal(
            b.build(method, url),
            status, responseType
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String, jwt: String,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return p.performAndUnmarshal(
            b.jwt(b.build(method, url), jwt),
            status, responseType
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String, requestBody: Any,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return p.performAndUnmarshal(
            b.json(b.build(method, url), requestBody),
            status, responseType
        )
    }

    protected final fun <T> call(
        method: HttpMethod, url: String, requestBody: Any, jwt: String,
        status: HttpStatus, responseType: Class<T>
    ): T {
        return p.performAndUnmarshal(
            b.jwt(b.json(b.build(method, url), requestBody), jwt),
            status, responseType
        )
    }

}
