package com.gpa.gpaStudent.controller

import com.gpa.gpaStudent.entity.Student
import com.gpa.gpaStudent.handler.StudentHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class StudentController  {

    @Autowired
    lateinit var studentRepository:StudentHandler

    @GetMapping("/students")
    fun index() = viewList(studentRepository.index())

//    @GetMapping("/students/{id}")
//    fun show(@PathVariable id: String) = view(studentRepository.show(id))


    fun viewList(data:List<Student>) = mapOf("data" to data)

    fun view(data:Student) = mapOf("data" to data)

}