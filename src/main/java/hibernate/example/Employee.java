package hibernate.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
//@Builder
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "department_id", updatable = false)
	private int employeeId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	@Column(name = "salary")
	private int salary;
	@Column(name = "department_id")
	private int departmentId;

}
