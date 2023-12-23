package com.gpa.gpaStudent.repo

import com.gpa.gpaStudent.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface StudentRepository  : JpaRepository<Student,String> {

    @Query("SELECT s FROM Student s WHERE s.studentId = :studentId AND s.nationalId = :nationalId")
    fun findByStudentIdAndNationalId(studentId: String, nationalId: String): Student?
}