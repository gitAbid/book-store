package com.bookstore.book_store.service

import com.bookstore.book_store.model.Author
import org.springframework.http.ResponseEntity


interface AuthorService {
    fun getAllAuthors(name: String, sortBy: String, limit: Int): ResponseEntity<List<Author>>
    fun getAuthor(id: Long): ResponseEntity<Author>
    fun updateAuthor(id: Long, author: Author): ResponseEntity<Author>
    fun addAuthor(author: Author): ResponseEntity<Author>
    fun deleteAuthor(id: Long): ResponseEntity<String>
}