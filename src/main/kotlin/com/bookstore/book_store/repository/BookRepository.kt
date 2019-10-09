package com.bookstore.book_store.repository

import com.bookstore.book_store.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {}