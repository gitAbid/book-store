package com.bookstore.book_store.model

import javax.persistence.*

@Entity
class Author(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        @ManyToMany
        val books: Set<Book>)
