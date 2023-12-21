package com.gpa.gpaStudent.entity

import jakarta.persistence.*

@Entity
@Table(name="students")
data class Student(
    @Id
    val studentId:String,


    var fname:String,
    var lname:String,
    var nationalId:String,
)