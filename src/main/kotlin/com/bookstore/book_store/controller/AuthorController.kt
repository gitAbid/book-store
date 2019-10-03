package com.bookstore.book_store.controller

import com.bookstore.book_store.model.Author
import com.bookstore.book_store.repository.AuthorRepository
import com.bookstore.book_store.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/v1/authors")
class AuthorController(@Autowired var authorService: AuthorService) {
    @GetMapping
    fun getAllAuthor(size: Optional<Int>, sortBy: Optional<String>, name: Optional<String>): ResponseEntity<List<Author>> {
        return authorService.getAllAuthors(name = name.orElse("_"), sortBy = sortBy.orElse("id"),
                limit = size.orElse(Int.MAX_VALUE))
    }

    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: Long): ResponseEntity<Author> {
        return authorService.getAuthor(id)
    }

    @PostMapping
    fun addAuthor(@RequestBody author: Author): ResponseEntity<Author> {
        return authorService.addAuthor(author)
    }

    @PutMapping("/{id}")
    fun updateAuthor(@PathVariable id: Long, @RequestBody author: Author): ResponseEntity<Author> {
        return authorService.updateAuthor(id, author)
    }

    @DeleteMapping("/{id}")
    fun deleteAuthor(@PathVariable id: Long): ResponseEntity<String> {
        return authorService.deleteAuthor(id)
    }
}