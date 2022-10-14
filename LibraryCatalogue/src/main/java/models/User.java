package models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter long cardNumber;
	
	@Getter @Setter String name;
	@Getter @Setter String surname;
	@Getter @Setter LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")
	@Getter @Setter Set<Loan> loans;
	
	public void addLoan(Loan l) {
		this.loans.add(l);
	}
	
	public String toString() {
		return String.format("NAME: %s, SURNAME: %s", name, surname);
	}
	
}
