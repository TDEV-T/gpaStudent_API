package com.gpa.gpaStudent.controller

import com.gpa.gpaStudent.entity.Term
import com.gpa.gpaStudent.repo.TermRepository
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class TermController {
    @Autowired
    lateinit var termRepository: TermRepository

    @GetMapping("/terms")
    fun index(): MutableList<Term>{
        return termRepository.findAll()
    }

    @GetMapping("/terms/{id}")
    fun getTermById(@PathVariable id : String) : Term? {
        return termRepository.findByIdOrNull(id)
    }

    @PostMapping("/terms")
    fun createTerm(@RequestBody termReq : Term) : ResponseEntity<String>{
        return try{
            termRepository.save(termReq)
            ResponseEntity.ok("Create Success !")
        }catch(e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : ${e.message}")
        }
    }

    @PutMapping("/terms/{id}")
    fun updateTerm(@RequestBody termReq : Term , @PathVariable id : String) : ResponseEntity<String>{
        return try{
            var existingTerm = termRepository.findById(id).orElse(null)

            if(existingTerm != null) {
                existingTerm.termName = termReq.termName
                existingTerm.startDate = termReq.startDate
                existingTerm.endDate = termReq.endDate

                termRepository.save(existingTerm)
                ResponseEntity.ok("Update Success !")

            }else{
                ResponseEntity.badRequest().body("Can't find Term ID : $id")
            }
        }catch(e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : ${e.message}")
        }
    }

}