package models;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Magazine extends AbstractBook{
	@Getter @Setter String periodicity;
	
	public Magazine() {}
	
	//Constructor with loan
	public Magazine(String title, int releaseYear, int pages, String periodicity, Loan loan) {
		super(title, releaseYear, pages, loan);
		this.periodicity = periodicity;
	}
	
	//Constructor without loan
	public Magazine(String title, int releaseYear, int pages, String periodicity) {
		super(title, releaseYear, pages);
		this.periodicity = periodicity;
	}
	
	public String toString() {
		return "ISBN: " + this.isbn + ", Title: " + this.title + ", Year: " + this.releaseYear + ", Pages: " + this.pages + ", Periodicity: " + this.periodicity;
 	}
}

	