package com.bookstore.book_store.controller

import com.bookstore.book_store.model.Author
import com.bookstore.book_store.repository.AuthorRepository
import com.bookstore.book_store.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.awt.print.Pageable
import java.util.*


@RestController
@RequestMapping("/authors")
class AuthorController(@Autowired var authorRepo: AuthorRepository,
                       @Autowired var authorService: AuthorService) {


    @GetMapping
    fun getAllAuthor(size: Optional<Int>,
                     sortBy: Optional<String>,
                     name: Optional<String>
    ): ResponseEntity<List<Author>> {
        return authorService.getAllAuthors(name = name.orElse("_"), sortBy = sortBy.orElse("id"),
                limit = size.orElse(Int.MAX_VALUE))
    }

    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: Long): ResponseEntity<Author> {
        return authorService.getAuthor(id = id)
    }

    @PostMapping
    fun addAuthor(@RequestBody author: Author): ResponseEntity<Author> {
        authorRepo.save(author)
        return ResponseEntity(authorRepo.getOne(author.id), HttpStatus.CREATED) // status code 201
    }

    @PutMapping("/{id}")
    fun updateAuthor(@PathVariable id: Long, @RequestBody author: Author): ResponseEntity<Author> {
        return authorService.updateAuthor(id,author)
        // partial update
    }

    @DeleteMapping("/{id}")
    fun deleteAuthor(@PathVariable id: Long): ResponseEntity<String> {
        authorRepo.deleteById(id)
        return ResponseEntity.ok("Delete Successful") // status code 200
    }
}