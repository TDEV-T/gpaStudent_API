package com.gpa.gpaStudent.repo

import com.gpa.gpaStudent.entity.Term
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TermRepository : JpaRepository<Term,String> {
}