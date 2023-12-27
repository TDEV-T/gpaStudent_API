package com.gpa.gpaStudent.entity

import java.time.LocalDate

data class GradeAndTerm(
        val termCode: String,
        val termName: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val grade: GradeDetails
){
    // Default constructor
    constructor() : this("", "", LocalDate.MIN, LocalDate.MIN, GradeDetails())
}
