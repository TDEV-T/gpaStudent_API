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
        var student : Student,

        @ManyToOne
        var subject : Subject,

        @ManyToOne
        var term:Term,

        var score : Double,

        var creditHourse : Int
)