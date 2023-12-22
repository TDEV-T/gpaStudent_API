package com.gpa.gpaStudent.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate


@Entity
@Table(name="terms")
data class Term(
        @Id
        val termCode : String,
        var termName : String,
        var startDate : LocalDate,
        var endDate : LocalDate
)
