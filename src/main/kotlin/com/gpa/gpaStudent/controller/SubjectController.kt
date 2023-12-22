package com.gpa.gpaStudent.controller

import com.gpa.gpaStudent.entity.Subject
import com.gpa.gpaStudent.repo.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SubjectController {

    @Autowired
    lateinit var subjectRepository: SubjectRepository

    @GetMapping("/subjects")
    fun index(): ResponseEntity<MutableList<Subject>> {
        val subjects: MutableList<Subject> = subjectRepository.findAll()
        return ResponseEntity(subjects, HttpStatus.OK)
    }

    @GetMapping("/subjects/{id}")
    fun getSubjectById(@PathVariable id: String): ResponseEntity<Subject?> {
        val subject: Subject? = subjectRepository.findByIdOrNull(id)
        return if (subject != null) {
            ResponseEntity(subject, HttpStatus.OK)
        } else {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/subjects")
    fun createSubject(@RequestBody subReq: Subject): ResponseEntity<String> {
        return try {
            subjectRepository.save(subReq)
            ResponseEntity("Create Success!", HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity("Error Create", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/subjects/{id}")
    fun updateSubject(@PathVariable id: String, @RequestBody subReq: Subject): ResponseEntity<String> {
        return try {
            val existingSubject: Subject? = subjectRepository.findByIdOrNull(id)

            return if (existingSubject != null) {
                existingSubject.subjectName = subReq.subjectName
                subjectRepository.save(existingSubject)
                ResponseEntity("Update Success", HttpStatus.OK)
            } else {
                ResponseEntity("Subject ID: $id Not found !", HttpStatus.NOT_FOUND)
            }
        } catch (e: Exception) {
            ResponseEntity("Can't find Subject ID: $id", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}