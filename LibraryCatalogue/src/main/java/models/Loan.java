package models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Builder
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@Getter @Setter User user;
	
	@OneToOne
	@JoinColumn(name="book_id")
	@Getter @Setter AbstractBook book;
	@Getter @Setter LocalDate startingDate;
	@Getter @Setter LocalDate expirationDate;
	@Getter @Setter LocalDate returnDate;
	
	public Loan(long id, User user, AbstractBook book, LocalDate startingDate, LocalDate returnDate, LocalDate expirationDate) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
		this.startingDate = startingDate;
		this.returnDate = returnDate;
		this.expirationDate = expirationDate;
	}
	
	public void calcExpiration() {
		this.expirationDate = this.startingDate.plusDays(30);
	}
	
	public String toString() {
		return String.format("USER: [ %s ],\n BOOK: [ %s ],\n STARTINGDATE: %s,\n EXPIRATIONDATE: %s,\n RETURNDATE: %s \n", user, book, startingDate, expirationDate, returnDate );
	}
	
}
