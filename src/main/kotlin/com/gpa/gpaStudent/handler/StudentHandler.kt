package com.gpa.gpaStudent.handler

import com.gpa.gpaStudent.entity.Student
import com.gpa.gpaStudent.repo.StudentRepository
import org.springframework.stereotype.Component

@Component
class StudentHandler(val StudnetRepository : StudentRepository) : BaseHandler<StudentRepository,Student>(StudnetRepository)