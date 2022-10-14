package utils;

import java.time.LocalDate;
import java.util.Random;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import main.LibraryDAO;
import models.Book;
import models.Loan;
import models.Magazine;
import models.User;

public class Randomizer {

	private static String randomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = ThreadLocalRandom.current().nextInt(4, 9);
		Random random = new Random();
		
		String randomString = random.ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
		return randomString.substring(0,1).toUpperCase() + randomString.substring(1);
	}
	
	private static String randomGenre() {
		String[] genres = new String[] {"Fantasy", "Horror", "Historical", "Romance", "Science Fiction"};
		return genres[ThreadLocalRandom.current().nextInt(0, 4)];
	}
	
	private static String randomPeriodicity() {
		String[] periodicities = new String[] {"Weekly", "Monthly", "Half-yearly"};
		return periodicities[ThreadLocalRandom.current().nextInt(0, 3)];
	}
	
	private static int randomYear() {
		return ThreadLocalRandom.current().nextInt(1970, 2023);
	}
	
	private static int randomPages() {
		return ThreadLocalRandom.current().nextInt(100, 501);
	}
	
	private static LocalDate randomDate() {
		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
	    long maxDay = LocalDate.of(2021, 12, 31).toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    return LocalDate.ofEpochDay(randomDay);
	}
	
	
	//------------------------- PUBLIC METHODS ---------------------------------
	
	public static LocalDate randomDateInRange(LocalDate min, LocalDate max) {
		long minDay = min.toEpochDay();
	    long maxDay = max.toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    return LocalDate.ofEpochDay(randomDay);
	}
	public static Book randomBook() {
		return new Book(randomString(), randomGenre(), randomString(), randomYear(), randomPages());
	}
	
	public static Magazine randomMagazine() {
		return new Magazine(randomString(), randomYear(), randomPages(), randomPeriodicity());
	}
	
	public static User randomUser() {
		return User.builder()
				.name(randomString())
				.surname(randomString())
				.birthDate(randomDate())
				.build();
	}
	
	public static Loan randomLoan(EntityManager em) {
		return Loan.builder()
				.book(LibraryDAO.getBookByISBN(em, ThreadLocalRandom.current().nextInt(1,1191)))
				.user(LibraryDAO.getUserByCardNumber(em, ThreadLocalRandom.current().nextInt(51,101)))
				.startingDate(randomDate())
				.returnDate(randomDate())
				.build();
	}
}
