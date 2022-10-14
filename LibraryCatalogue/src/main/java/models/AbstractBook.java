package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity  
@Table(name="catalogue")  
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  
public class AbstractBook {
	
	@Id
	@SequenceGenerator(
        name="id_seq",
        sequenceName="id_seq",
        initialValue = 100000,
        allocationSize=1    // cache in postgre field
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator="id_seq"
    )
	
	@Getter protected long isbn;
	@Getter @Setter protected String title;
	@Getter @Setter protected int releaseYear;
	@Getter @Setter protected int pages;
	
	@OneToOne(mappedBy = "book")
	@Getter @Setter Loan loan;
	
	public AbstractBook() {}
	
	//Constructor with loan
	public AbstractBook(String title, int releaseYear, int pages, Loan loan) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.pages = pages;
		this.loan = loan;
	}

	//Constructor without loan
	public AbstractBook(String title, int releaseYear, int pages) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.pages = pages;
	}
	
	
	
	
}
