package com.gpa.gpaStudent.repo

import com.gpa.gpaStudent.entity.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SubjectRepository : JpaRepository<Subject,String> {
}