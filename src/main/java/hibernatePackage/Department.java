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
@Table (name ="Department")
public  class Department {
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="Dept_Id")
	private int DeptId;
	
	@Column(name="Dept_Name")
	 private String Dept_Name;

}
