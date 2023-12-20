package com.gpa.gpaStudent.entity

import jakarta.persistence.*

@Entity
@Table(name="students")
data class Student(
    @Id
    val studentId:String,


    val fname:String,
    val lname:String,
    val nationalId:String,
)