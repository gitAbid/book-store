package com.bookstore.book_store.service

import com.bookstore.book_store.model.Author
import com.bookstore.book_store.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AuthorService(@Autowired var authorRepo: AuthorRepository) {
    fun getAllAuthors(name: String, sortBy: String, limit: Int): List<Author> ? {
        val pageable = PageRequest.of(0, limit, Sort.by(sortBy).ascending());
        return authorRepo.findAuthors(name, pageable)
    }

    fun getAuthor(id: Long): Author ?{
        return authorRepo.getOne(id)
    }

    fun updateAuthor(id: Long, author: Author): Author? {
        authorRepo.getOne(id)?.let {
            authorRepo.save(author)
        }
        return authorRepo.getOne(id) // status code 201
    }

    fun addAuthor(author: Author): Author?{
        authorRepo.save(author)
        return authorRepo.getOne(author.id) // status code 201
    }

    fun deleteAuthor(id: Long): Author? {
        authorRepo.deleteById(id)
        return authorRepo.getOne(id) // status code 200
    }

}