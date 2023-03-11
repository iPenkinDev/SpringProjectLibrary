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
//public class BookDAO {
//    JDBCTemplate
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public Book save(Book book) {
//        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
//        return book;
//    }
//
//    public Book update(int id, Book updatedBook) {
//        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
//        return updatedBook;
//    }
//
//    public Book delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//        return null;
//    }
//
//    public Optional<Person> getBookOwner(int id) {
//        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
//                "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public void returnBook(int id) {
//        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE id = ?", id);
//    }
//
//    public void assignBook(int id, Person selectedPerson) {
//        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", selectedPerson.getId(), id);
//    }
//
//    Hibernate
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("from Book").getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Book show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Book.class, id);
//    }
//
//    @Transactional
//    public void save(Book book) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(book);
//    }
//
//    @Transactional
//    public void update(int id, Book updatedBook) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        book.setTitle(updatedBook.getTitle());
//        book.setAuthor(updatedBook.getAuthor());
//        book.setYear(updatedBook.getYear());
//        session.update(book);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        session.delete(book);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Person> getBookOwner(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        Person person = book.getOwner();
//        return person == null ? Optional.empty() : Optional.of(person);
//    }
//
//    @Transactional
//    public void returnBook(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        book.setOwner(null);
//        session.update(book);
//    }
//
//    @Transactional
//    public void assignBook(int id, Person selectedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        book.setOwner(selectedPerson);
//        session.update(book);
//    }
//}
