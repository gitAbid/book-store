package com.bookstore.book_store.controller

import com.bookstore.book_store.model.Book
import com.bookstore.book_store.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/books")
class BooksController {

    @Autowired
    lateinit var bookRepo: BookRepository

    @GetMapping
    fun getAllBooks(@RequestParam(value = "size", defaultValue = "0", required = false) size: Int,
                    @RequestParam(value = "sortBy", required = false, defaultValue = "id") sortBy: String,
                    @RequestParam(value = "search", required = false, defaultValue = "") search: String
    ): List<Book> {
        var filtered_books = bookRepo.findAll()
        if (!search.isNullOrEmpty()) {
            filtered_books = filtered_books.filter { it.title.contains(search, ignoreCase = true) } as ArrayList<Book>
        }
        when (sortBy) {
            "id" -> filtered_books.sortBy { it.id }
            "title" -> filtered_books.sortBy { it.title }
            else -> filtered_books.sortBy { it.id }
        }

        return (if (size == 0) {
            filtered_books
        } else {
            if (filtered_books.size > size) filtered_books.subList(0, size) else filtered_books
        })


    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Long): Book {
        return bookRepo.getOne(id)
    }

    @PostMapping
    fun addBook(@RequestBody book: Book): Book {
        bookRepo.save(book)
        return bookRepo.getOne(book.id)
    }

    @PutMapping("/{id}")
    fun updateBook(@RequestBody book: Book, @PathVariable id: Long): Book {
        bookRepo.save(book)
        return bookRepo.getOne(id)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long) {
        bookRepo.deleteById(id)
    }
}