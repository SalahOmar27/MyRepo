package hibernatePackage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.*;

@Data
@Entity 
@Table(name="Employee")
public  class Employee {
	 @Id @GeneratedValue (strategy = GenerationType.AUTO)
	   @Column(name = "employee_id")
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
	   @Column(name = "Dept_Id")
   	   private int departmentId;
	   
	   
}
