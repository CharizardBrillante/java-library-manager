package main;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Book;
import models.Loan;
import models.Magazine;
import models.User;
import utils.Randomizer;

public class Main {

	public static void main(String[] args) {
		
		//						------------------------------- !!! PER FLAVIO !!! ----------------------------------
		// 						Se vuoi testare con un numero diverso di libri, utenti o prestiti, vai prima nella classe Randomizer
		// 						a modificare il range di user_id e book_id nel metodo randomLoan(), altrimenti assegnerà ai prestiti
		// 						solo users e book che ho instanziato io
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryCatalogue");
		EntityManager em = emf.createEntityManager();
		
//		for(int i = 0; i < 500; i++) {
//			Book b = Randomizer.randomBook();
//			Magazine m = Randomizer.randomMagazine();
//			
//			LibraryDAO.addElement(em, b);
//			LibraryDAO.addElement(em, m);
//		}
		
//		for(int i = 1; i < 51; i++) {
//			User u = Randomizer.randomUser();
//			LibraryDAO.addElement(em, u);
//		}
		
//		for (int i = 0; i<10; i++) {
//			Loan l = Randomizer.randomLoan(em);
//			l.setExpirationDate(l.getStartingDate().plusDays(30));
//			if(ThreadLocalRandom.current().nextInt(0, 2) > 0) {
//				l.setReturnDate(Randomizer.randomDateInRange(l.getStartingDate(), l.getExpirationDate()));
//			}
//			LibraryDAO.addElement(em, l);
//		}
		
		System.out.println("------------- BOOK BY IBSN ---------------");
		System.out.println(LibraryDAO.getBookByISBN(em, 1));
		System.out.println();
		
		System.out.println("------------- BOOKS BY AUTHOR ---------------");
		System.out.println(LibraryDAO.getBookByAuthor(em, "pdhec"));
		System.out.println();
		
		System.out.println("------------- BOOKS BY YEAR ---------------");
		System.out.println(LibraryDAO.getBookByYear(em, 2006));
		System.out.println();
		
		System.out.println("------------- BOOKS BY PORTION OF TITLE ---------------");
		System.out.println(LibraryDAO.getBookByTitle(em, "ci"));
		System.out.println();
		
		System.out.println("------------- EXPIRED AND NOT RETURNED LOANS ---------------");
		System.out.println(LibraryDAO.getExpiredLoans(em));
		System.out.println();
		
		System.out.println("------------- LOANED BOOKS BY USER ---------------");
		System.out.println(LibraryDAO.getLoanedBooksByUser(em, 80));
		System.out.println();
		
		em.close();
		emf.close();

	}

}
