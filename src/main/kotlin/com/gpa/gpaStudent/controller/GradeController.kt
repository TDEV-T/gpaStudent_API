package com.gpa.gpaStudent.controller

import com.gpa.gpaStudent.entity.Grade
import com.gpa.gpaStudent.repo.GradeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class GradeController {
    @Autowired
    lateinit var gradeRepository: GradeRepository

    @GetMapping("/grades")
    fun index() : MutableList<Grade> {
        return gradeRepository.findAll()
    }


}