package com.bookstore.book_store.service

import com.bookstore.book_store.model.Author
import com.bookstore.book_store.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthorService(@Autowired var authorRepo: AuthorRepository) {
    fun getAllAuthors(name: String, sortBy: String, limit: Int): ResponseEntity<List<Author>> {
        val pageable = PageRequest.of(0, limit, Sort.by(sortBy).ascending());
        return ResponseEntity(authorRepo.findAuthors(name, pageable), HttpStatus.OK)
    }

    fun getAuthor(id: Long): ResponseEntity<Author> {
        return ResponseEntity(authorRepo.getOne(id), HttpStatus.OK)
    }

    fun updateAuthor(id: Long, author: Author): ResponseEntity<Author> {
        if (authorRepo.getOne(id) != null) {
            authorRepo.save(author)
            return ResponseEntity(authorRepo.getOne(id), HttpStatus.OK) // status code 201
        } else {
            return ResponseEntity(HttpStatus.BAD_REQUEST) // status code 201
        }
    }

}