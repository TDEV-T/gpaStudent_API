package com.gpa.gpaStudent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class GpaStudentApplication

fun main(args: Array<String>) {
	runApplication<GpaStudentApplication>(*args)
}


@RestController
class MessageController{
	@GetMapping("/")
	fun index(@RequestParam("name") name : String) = "Hello m $name"
}
