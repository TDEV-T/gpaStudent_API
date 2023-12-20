package com.gpa.gpaStudent.handler

import org.springframework.data.jpa.repository.JpaRepository


interface BaseHandlerInterface<T> {
    fun index() : List<T>

    fun create(obj: T) : T

    fun show(studentId:String):T?

    fun update(studentId:String,obj:T): T?

    fun delete(studentId: String) : T?
}
abstract class BaseHandler<out R:JpaRepository<T,String>,T: Any> (val repository: R) : BaseHandlerInterface<T> {
    override fun create(obj: T): T = repository.save(obj)
    override fun index(): List<T> = repository.findAll()
    override fun show(studentId:String): T? = repository.getOne(studentId)
    override fun update(studentId: String,obj:T) : T? {
        val existedObj : T? = repository.getOne(studentId)

        if(existedObj != null) {
            return repository.save(obj)
        }

        return null
    }
    override fun delete(studentId:String): T? {
        val existedObj : T? = repository.getOne(studentId)

        if(existedObj != null){
            repository.deleteById(studentId)

            return existedObj
        }

        return null
    }

}