package com.igordev.springprojectlibrary.services;

import com.igordev.springprojectlibrary.models.Book;
import com.igordev.springprojectlibrary.models.Person;
import com.igordev.springprojectlibrary.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById (int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save (Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update (int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete (int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findByName (String name) {
        return peopleRepository.findByName(name);
    }

    public List<Book> getPersonBooks(int id) {
        return peopleRepository.getPersonBooks(id);
    }
}
