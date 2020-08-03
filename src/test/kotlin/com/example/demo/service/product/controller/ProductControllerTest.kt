package com.example.demo.service.product.controller

import com.example.demo.service.product.entity.Product
import com.example.demo.service.product.entity.Response
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(
    controllers = [ProductController::class],
    properties = ["server.port=0", "management.server.port=0"]
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProductControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    val products: List<Product> = listOf(
        Product().also {
            it.id = "G0017"
            it.name = ""
            it.numOfProduct = 150
        },
        Product().also
        {
            it.id = "G0019"
            it.name = "Glock 19"
            it.numOfProduct = 100
        }
        ,
        Product().also
        {
            it.id = "B0001"
            it.name = "Beretta M9"
            it.numOfProduct = 99
        })

    @Test
    fun `should return claim reward offer reward success test`() {
        val response = Response().apply {
            respCode = "success"
            respMessage = jacksonObjectMapper().writeValueAsString(products)
        }
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn()

        val actualResponseBody = mvcResult.response.contentAsString
        JSONAssert.assertEquals(jacksonObjectMapper().writeValueAsString(response), actualResponseBody, true)
    }
}
