package com.gpa.gpaStudent.repo

import com.gpa.gpaStudent.entity.Grade
import com.gpa.gpaStudent.entity.GradeAndTerm
import com.gpa.gpaStudent.entity.Term
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import com.gpa.gpaStudent.entity.GradeDetails

@Repository
interface GradeRepository : JpaRepository<Grade,Long> {
    @Query("SELECT new com.gpa.gpaStudent.entity.GradeAndTerm(t.termCode, t.termName, t.startDate, t.endDate, new com.gpa.gpaStudent.entity.GradeDetails(g.id, s, sub, g.score, g.creditHourse)) FROM Grade g JOIN g.student s JOIN g.term t JOIN g.subject sub WHERE s.studentId = :studentId")
    fun findGradesAndTermsByStudentId(@Param("studentId") studentId: String): List<GradeAndTerm>
}