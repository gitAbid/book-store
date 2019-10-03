package com.bookstore.book_store.repository

import com.bookstore.book_store.model.Author
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*


interface AuthorRepository : JpaRepository<Author, Long> {

    @Query("select a from Author a where a.name like %?1%")
    fun findAuthors(name: String, pageable: Pageable): List<Author>
}