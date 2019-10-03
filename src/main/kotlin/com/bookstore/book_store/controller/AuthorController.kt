package com.bookstore.book_store.controller

import com.bookstore.book_store.model.Author
import com.bookstore.book_store.service.AuthorService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*


@RestController
@RequestMapping("/v1/authors")
@Api(value = "Authors", description = "Authors api allows get all authors, get single author, update author, delete authors")
class AuthorController(@Autowired var authorService: AuthorService) {
    @GetMapping
    @ApiOperation(value = "Get all authors. This api also allows sorting[sortBy], list [size] and searching my [name]",
                    response = List::class, protocols = "GET")
    fun getAllAuthor(size: Optional<Int>, sortBy: Optional<String>, name: Optional<String>): ResponseEntity<List<Author>> {
        return authorService.getAllAuthors(name = name.orElse("_"), sortBy = sortBy.orElse("id"),
                limit = size.orElse(Int.MAX_VALUE))
    }

    @ApiOperation(value = "Get single author by id", protocols = "GET")
    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: Long): ResponseEntity<Author> {
        return authorService.getAuthor(id)
    }

    @ApiOperation(value = "Add new author", protocols = "POST")
    @PostMapping
    fun addAuthor(@RequestBody author: Author): ResponseEntity<Author> {
        return authorService.addAuthor(author)
    }

    @ApiOperation(value = "Update author by id",
            response = List::class, protocols = "PUT")
    @PutMapping("/{id}")
    fun updateAuthor(@PathVariable id: Long, @RequestBody author: Author): ResponseEntity<Author> {
        return authorService.updateAuthor(id, author)
    }

    @ApiOperation(value = "Delete author by id",protocols = "DELETE")
    @DeleteMapping("/{id}")
    fun deleteAuthor(@PathVariable id: Long): ResponseEntity<String> {
        return authorService.deleteAuthor(id)
    }
}