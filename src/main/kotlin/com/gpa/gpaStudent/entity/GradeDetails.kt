package com.gpa.gpaStudent.entity

data class GradeDetails(
        val id: Long,
        val student: Student,
        val subject: Subject,
        val score: Double,
        val creditHourse: Int
){
    constructor() : this(0L, Student(), Subject(), 0.0, 0)
}