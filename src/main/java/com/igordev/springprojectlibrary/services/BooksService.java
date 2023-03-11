package com.igordev.springprojectlibrary.services;

import com.igordev.springprojectlibrary.models.Book;
import com.igordev.springprojectlibrary.models.Person;
import com.igordev.springprojectlibrary.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById (int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save (Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update (int id, Book updateBook) {
        updateBook.setId(id);
        booksRepository.save(updateBook);
    }

    @Transactional
    public void delete (int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return booksRepository.getBookOwner(id);
    }

    @Transactional
    public void returnBook(int id) {
        booksRepository.returnBook(id);
    }

    @Transactional
    public void assignBook(int id, Person selectedPerson) {
        booksRepository.assignBook(id, selectedPerson);
    }
}
