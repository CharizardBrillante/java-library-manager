package models;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Book extends AbstractBook{
	
	@Getter @Setter String author;
	@Getter @Setter String genre;
	
	public Book() {}
	
	//Constructor with loan
	public Book(String author, String genre, String title, int releaseYear, int pages, Loan loan) {
		super(title, releaseYear, pages, loan);
		this.author = author;
		this.genre = genre;
	}
	
	//Constructor without loan
	public Book(String author, String genre, String title, int releaseYear, int pages) {
		super(title, releaseYear, pages);
		this.author = author;
		this.genre = genre;
	}
	

	
	public String toString() {
		return "ISBN: " + this.isbn + ", Author: " + this.author +  ", Genre: " + this.genre + ", Title: " + this.title + ", Year: " + this.releaseYear + ", Pages: " + this.pages;
 	}
}
