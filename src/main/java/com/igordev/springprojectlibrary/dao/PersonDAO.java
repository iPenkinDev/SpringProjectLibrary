//package com.igordev.springprojectlibrary.dao;
//
//import com.igordev.springprojectlibrary.models.Book;
//import com.igordev.springprojectlibrary.models.Person;
//import org.hibernate.Hibernate;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@SuppressWarnings("SqlResolve")
//@Component
//public class PersonDAO {
//    JDBCTemplate
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//       return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(name, age) VALUES (?, ?)", person.getName(), person.getAge());
//    }
//
//    public void update(int id, Person updatedPerson) {
//       jdbcTemplate.update("UPDATE Person SET name=?, age=? WHERE id=?", updatedPerson.getName(), updatedPerson.getAge(), id);
//    }
//
//    public void delete(int id) {jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public Optional<Person> getPersonByFullName(String name) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public Object getPersonBooks(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    Hibernate
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Person show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//        person.setName(updatedPerson.getName());
//        person.setAge(updatedPerson.getAge());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, id));
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Person> getPersonByName(String name) {
//        Session session = sessionFactory.getCurrentSession();
//        List<Person> people = session.createQuery("select p from Person p where p.name = :name", Person.class)
//                .setParameter("name", name)
//                .getResultList();
//        return people.stream().findAny();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> getPersonBooks(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        List<Book> books = session.createQuery("select b from Book b where b.owner.id = :id", Book.class)
//                .setParameter("id", id)
//                .getResultList();
//        return books;
//    }
//}
