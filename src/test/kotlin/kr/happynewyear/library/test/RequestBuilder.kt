package kr.happynewyear.library.test

import kr.happynewyear.library.utility.JsonIO
import org.springframework.boot.test.context.TestComponent
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@TestComponent
class RequestBuilder {

    fun build(method: HttpMethod, url: String): MockHttpServletRequestBuilder {
        return MockMvcRequestBuilders.request(method, url)
    }

    fun json(request: MockHttpServletRequestBuilder, requestBody: Any): MockHttpServletRequestBuilder {
        return request.contentType(MediaType.APPLICATION_JSON).content(JsonIO.write(requestBody))
    }

    fun jwt(request: MockHttpServletRequestBuilder, jwt: String): MockHttpServletRequestBuilder {
        return request.header("Authorization", "Bearer $jwt")
    }

}
