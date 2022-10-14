package main;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import models.AbstractBook;
import models.Loan;
import models.User;


public class LibraryDAO {
	
	public static void addElement(EntityManager em, Object b) {
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.persist(b);
		
		t.commit();
	}
	
	
	public static void deleteBook(EntityManager em, long ISBN) {
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.remove(getBookByISBN(em, ISBN));
		
		t.commit();
	}

	
	public static AbstractBook getBookByISBN(EntityManager em, long ISBN) {
		return em.find(AbstractBook.class, ISBN);
	}
	
	public static User getUserByCardNumber(EntityManager em, long cn) {
		return em.find(User.class, cn);
	}
	
	public static List<AbstractBook> getBookByYear(EntityManager em, int year) {
		Query q = em.createQuery("SELECT b FROM AbstractBook b WHERE b.releaseYear = :y");
		q.setParameter("y", year);
		
		List<AbstractBook> r = q.getResultList();
		
		return r;
	}
	
	public static List<AbstractBook> getBookByAuthor(EntityManager em, String author) {
		Query q = em.createQuery("SELECT b FROM AbstractBook b WHERE b.author = :a");
		q.setParameter("a", author);
		
		List<AbstractBook> r = q.getResultList();
		
		return r;
	}
	
	public static List<AbstractBook> getBookByTitle(EntityManager em, String title) {
		Query q = em.createQuery("SELECT b FROM AbstractBook b WHERE b.title LIKE :t");
		q.setParameter("t", "%" + title + "%");
		
		List<AbstractBook> r = q.getResultList();
		
		return r;
	}
	
	public static List<Loan> getExpiredLoans(EntityManager em){
		LocalDate now = LocalDate.now();
		Query q = em.createQuery("SELECT l FROM Loan l WHERE l.expirationDate < :d AND l.returnDate IS NULL");
		q.setParameter("d", now);
		
		List<Loan> r = q.getResultList();
		
		return r;
	}
	
	public static List<AbstractBook> getLoanedBooksByUser(EntityManager em, long cn) {
		Query q = em.createQuery("SELECT b FROM AbstractBook b WHERE EXISTS(SELECT l FROM Loan l WHERE b.isbn = l.book.isbn AND l.user.cardNumber = :cn )");
		q.setParameter("cn", cn );
		
		List<AbstractBook> r = q.getResultList();
		
		return r;
	}
}
