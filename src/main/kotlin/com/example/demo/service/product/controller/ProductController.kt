package com.example.demo.service.product.controller

import com.example.demo.service.product.entity.Product
import com.example.demo.service.product.entity.Response
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

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

    @GetMapping(
        value = ["/product"]
    )
    fun handshake(): Response {
        var response = Response()
        response.respCode = "success"
        response.respMessage = jacksonObjectMapper().writeValueAsString(products)
        return response
    }

    @GetMapping(
        value = ["/product/{id}"]
    )
    fun getName(
        @PathVariable("id") id: String
    ): Product {
        return products.find { it.id == id }!!
    }
}
