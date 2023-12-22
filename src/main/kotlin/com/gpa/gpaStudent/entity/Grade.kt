package com.gpa.gpaStudent.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name="grades")
data class Grade(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    val student : Student,

    @ManyToOne
    val subject : Subject,

    @ManyToOne
    val term:Term,

    val score : Double,

    val creditHourse : Int
)