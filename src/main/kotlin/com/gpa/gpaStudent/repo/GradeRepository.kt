package com.gpa.gpaStudent.repo

import com.gpa.gpaStudent.entity.Grade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GradeRepository : JpaRepository<Grade,Long> {
}