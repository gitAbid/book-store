package com.bookstore.book_store.controller

import com.bookstore.book_store.annotation.Doc
import com.bookstore.book_store.model.Author
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Api(value = "Authors", description = "Authors api allows get all authors, get single author, update author, delete authors")
interface DocumentationInterface {

    /*@ApiOperation(value = "Get all authors. This api also allows sorting[sortBy], list [size] and searching my [name]",
            response = List::class, protocols = "GET")
    */
    fun getAllAuthor(size: Optional<Int>, sortBy: Optional<String>, name: Optional<String>): ResponseEntity<List<Author>>

    @ApiOperation(value = "Get single author by id", protocols = "GET")
    fun getAuthor(@PathVariable id: Long): ResponseEntity<Author>

    @ApiOperation(value = "Add new author", protocols = "POST")
    fun addAuthor(@RequestBody author: Author): ResponseEntity<Author>

    @ApiOperation(value = "Update author by id",
            response = List::class, protocols = "PUT")
    fun updateAuthor(@PathVariable id: Long, @RequestBody author: Author): ResponseEntity<Author>

    @ApiOperation(value = "Delete author by id", protocols = "DELETE")
    fun deleteAuthor(@PathVariable id: Long): ResponseEntity<String>
}