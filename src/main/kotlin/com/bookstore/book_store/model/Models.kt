package com.bookstore.book_store.model

import javax.persistence.*


@Entity
class Book(@Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           val id: Long,
           val title: String,
           val description: String = "",
           @ManyToMany
           val authors: Set<Author>)

@Entity
class Author(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable
        val books: Set<Book>)

