package com.bookstore.book_store.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/")
class HomeController {

    @GetMapping
    fun swaggerUi(): String {
        return "redirect:/swagger-ui.html"
    }
}