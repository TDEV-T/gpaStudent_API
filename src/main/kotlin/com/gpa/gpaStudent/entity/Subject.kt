package com.gpa.gpaStudent.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="subjects")
data class Subject(
        @Id
        val subjectCode : String,
        var subjectName : String,

)