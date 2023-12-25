package com.gpa.gpaStudent.controller

import com.gpa.gpaStudent.entity.Student
import com.gpa.gpaStudent.repo.StudentRepository
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional


data class LoginRequest(
        val studentId: String,
        val nationalId: String
)


@RestController
@RequestMapping("/api/students")
class StudentController {

    @Autowired
    lateinit var studentRepository: StudentRepository

    @GetMapping("/")
    fun index(): ResponseEntity<MutableList<Student>> {
        val students: MutableList<Student> = studentRepository.findAll()
        return ResponseEntity(students, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: String): ResponseEntity<Student> {
        val student: Optional<Student> = studentRepository.findById(id)
        return if (student.isPresent) {
            ResponseEntity(student.get(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/")
    fun create(@RequestBody data: Student): ResponseEntity<String> {
        return try {

            val studenExist = studentRepository.findByIdOrNull(data.studentId)



            if(studenExist !== null){

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : ID Already to Create !")
            }

            studentRepository.save(data)
            ResponseEntity.ok("Create Success!")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: ${e.message}")
        }
    }

    @PutMapping("/update/{id}")
    fun update(@PathVariable id: String, @RequestBody stuReq: Student): ResponseEntity<String> {
        return try {
            val stu: Student = studentRepository.findById(id).orElse(null)

            return if (stu != null) {
                stu.fname = stuReq.fname
                stu.lname = stuReq.lname
                stu.nationalId = stuReq.nationalId

                studentRepository.save(stu)

                ResponseEntity.ok("Update Success")
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ID: $id Not found")
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: ${e.message}")
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<String> {
        return try {
            studentRepository.deleteById(id)
            ResponseEntity.ok("Delete Success!")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: ${e.message}")
        }
    }

    @PostMapping("/login")
    fun getStudentByStudentIdandNationalId(@RequestBody stuReq : LoginRequest ) : ResponseEntity<Student> {
        return try{
            val student : Student ? = studentRepository.findByStudentIdAndNationalId(stuReq.studentId,stuReq.nationalId);
            return if(student != null){
                ResponseEntity(student,HttpStatus.OK)
            }else{
                ResponseEntity(null,HttpStatus.OK)
            }
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
