package com.gpa.gpaStudent.controller

import com.gpa.gpaStudent.entity.*
import com.gpa.gpaStudent.repo.GradeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController







@RestController
@RequestMapping("/api")
class GradeController {
    @Autowired
    lateinit var gradeRepository: GradeRepository

    @GetMapping("/grades")
    fun index() : MutableList<Grade> {
        return gradeRepository.findAll()
    }

    @GetMapping("/grades/{id}")
    fun getGradeById(@PathVariable id : Long) : Grade? {
        return gradeRepository.findByIdOrNull(id)
    }

    @PostMapping("/grades")
    fun createGrade(@RequestBody gradeReq : Grade) : ResponseEntity<String>{
        return try{
            gradeRepository.save(gradeReq)
            ResponseEntity.ok("Create Success")
        }catch(e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : ${e.message}")
        }
    }

    @PutMapping("/grades/update/{id}")
    fun update(@PathVariable id : Long , @RequestBody gradeReq : Grade) : ResponseEntity<String>{
        return try{
            val gradeExists = gradeRepository.findByIdOrNull(id);

            if(gradeExists != null){
                gradeExists.creditHourse = gradeReq.creditHourse
                gradeExists.term = gradeReq.term
                gradeExists.score = gradeReq.score
                gradeExists.student = gradeReq.student
                gradeExists.subject = gradeReq.subject

                gradeRepository.save(gradeExists)

                return ResponseEntity.ok("Update Success")
            }

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : Can't find grade ID : $id")

        }catch(e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : ${e.message}")
        }
    }

    @DeleteMapping("/grades/delete/{id}")
    fun delete(@PathVariable id : Long) : ResponseEntity<String>{
        return try{
            gradeRepository.deleteById(id)
            ResponseEntity.ok("Delete Success !")
        }catch(e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : ${e.message}")
        }
    }

    @GetMapping("/grades/student/{studentId}")
    fun getGradesByStudentId(@PathVariable studentId: String): ResponseEntity<List<GradeAndTerm>> {
        val grades = gradeRepository.findGradesAndTermsByStudentId(studentId)
        return ResponseEntity.ok(grades)
    }


}