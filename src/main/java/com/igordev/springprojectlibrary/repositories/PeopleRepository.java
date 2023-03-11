package com.igordev.springprojectlibrary.repositories;

import com.igordev.springprojectlibrary.models.Book;
import com.igordev.springprojectlibrary.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByName(String name);

    @Query("SELECT b FROM Book b WHERE b.owner.id = :id")
    List<Book> getPersonBooks(@Param("id") int id);
}
