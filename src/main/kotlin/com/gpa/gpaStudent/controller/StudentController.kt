package com.gpa.gpaStudent.controller

import com.gpa.gpaStudent.entity.Student
import com.gpa.gpaStudent.repo.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
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
import java.util.*


@RestController
@RequestMapping("/api")
class StudentController  {

    @Autowired
    lateinit var studentRepository:StudentRepository

    @GetMapping("/students")
    fun index(): MutableList<Student> {
        return studentRepository.findAll()
    }

    @GetMapping("/students/{id}")
    fun getStudentById(@PathVariable id : String): Optional<Student> {
        return studentRepository.findById(id)
    }

    @PostMapping("/students")
    fun create(@RequestBody data:Student) : String {
        return try{
            studentRepository.save(data);
            "Create Success !"
        }catch (e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : ${e.message}").toString()
        }
    }

    @PutMapping("/students/update/{id}")
    fun update(@PathVariable id : String,@RequestBody stuReq : Student ) : String{
        return try{
         val  stu:Student  =  studentRepository.findById(id).orElse(null)

            if(stu != null) {
                stu.fname = stuReq.fname
                stu.lname = stuReq.lname
                stu.nationalId = stuReq.nationalId

                studentRepository.save(stu)

                "Update Success"
            }else{
                "Student ID : $id Not found"
            }
        }catch(e : Exception){
            "Error: ${e.message}"
        }
    }

    @DeleteMapping("/students/delete/{id}")
    fun delete(@PathVariable id : String) : String{
        return try{
            studentRepository.deleteById(id)
            "Delete Success !"
        }catch(e : Exception){
            "Error : ${e.message}"
        }
    }



}