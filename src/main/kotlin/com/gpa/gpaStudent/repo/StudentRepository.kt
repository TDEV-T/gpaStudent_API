package com.gpa.gpaStudent.repo

import com.gpa.gpaStudent.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface StudentRepository  : JpaRepository<Student,String> {
    fun findBynationalId(nationalId:String) : Student?
}