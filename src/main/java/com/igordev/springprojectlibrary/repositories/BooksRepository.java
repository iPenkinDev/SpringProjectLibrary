package com.igordev.springprojectlibrary.repositories;

import com.igordev.springprojectlibrary.models.Book;
import com.igordev.springprojectlibrary.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b.owner FROM Book b WHERE b.id = :id")
    Optional<Person> getBookOwner(@Param("id") int id);

    @Modifying
    @Query("UPDATE Book b SET b.owner = null WHERE b.id = :id")
    void returnBook(@Param("id") int id);

    @Modifying
    @Query("UPDATE Book b SET b.owner = :selectedPerson WHERE b.id = :id")
    void assignBook(@Param("id") int id, @Param("selectedPerson") Person selectedPerson);
}
