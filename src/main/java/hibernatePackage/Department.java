package hibernatePackage;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.*;
@Setter
@Getter
@Builder
@Entity 
@Table (name ="Department")
public  class Department {
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="department_id")
	private int departmentId;
	
	@Column(name="department_name")
	 private String departmentName;

}
