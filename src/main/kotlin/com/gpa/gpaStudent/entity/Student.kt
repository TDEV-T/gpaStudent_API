package com.gpa.gpaStudent.entity

import jakarta.persistence.*

@Entity
@Table(name="students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,

    val studentID:String,
    val fname:String,
    val lname:String,
    val nationalID:String,
)