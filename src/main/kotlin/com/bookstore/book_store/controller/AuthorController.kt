package com.bookstore.book_store.controller

import com.bookstore.book_store.model.Author
import com.bookstore.book_store.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/v1/authors")
class AuthorController(@Autowired var authorService: AuthorService) : DocumentationInterface {

    @GetMapping
    override
    fun getAllAuthor(size: Optional<Int>, sortBy: Optional<String>, name: Optional<String>): ResponseEntity<List<Author>> {
        val finalAuthor = authorService.getAllAuthors(name = name.orElse("_"), sortBy = sortBy.orElse("id"),
                limit = size.orElse(Int.MAX_VALUE))
        return ResponseEntity(finalAuthor, HttpStatus.OK)

    }

    @GetMapping("/{id}")
    override fun getAuthor(@PathVariable id: Long): ResponseEntity<Author> {
        val finalAuthor = authorService.getAuthor(id)
        return if (finalAuthor == null) ResponseEntity(HttpStatus.NOT_FOUND) else ResponseEntity(finalAuthor, HttpStatus.OK)

    }

    @PostMapping
    override fun addAuthor(@RequestBody author: Author): ResponseEntity<Author> {
        val finalAuthor = authorService.addAuthor(author)
        return if (finalAuthor == null) ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR) else ResponseEntity(finalAuthor, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    override fun updateAuthor(@PathVariable id: Long, @RequestBody author: Author): ResponseEntity<Author> {
        val finalAuthor = authorService.updateAuthor(id, author)
        return if (finalAuthor == null) ResponseEntity(HttpStatus.NOT_FOUND) else ResponseEntity(finalAuthor, HttpStatus.OK)
    }


    @DeleteMapping("/{id}")
    override fun deleteAuthor(@PathVariable id: Long): ResponseEntity<String> {
        var author: Author? = null
        if (authorService.getAuthor(id) != null) {
            author = authorService.deleteAuthor(id)
        }
        return if (author == null) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

    }
}