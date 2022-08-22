package hibernatePackage;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
	static Logger logger = LoggerFactory.getLogger(Demo.class);

	public static void main(String[] args) {
		addDepartment("Finance");
		addEmployee("Sameer", "Omar", "0791124447", "basel@gmail.com",1650, 6);
		employee10thMaxSalary();
		employeePerDepartments();
	}
	//------------------------------------ADD EMPLOYEE----------------------------------------
	private static void addEmployee(String first_name, String last_name, String phone, String email, int salary,int deptid) {
		SessionFactory sessionFactory =  new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		  Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Employee employee= new Employee();
		    employee.setFirstName(first_name);
			employee.setLastName(last_name);
			employee.setPhone(phone);
			employee.setEmail(email);
			employee.setSalary(salary);
			employee.setDepartmentId(deptid);
			session.persist(employee);
			session.getTransaction().commit();
	
		}catch (Exception e) {
			logger.error("Oppes, could not add infromation, please enter correct info!");
			session.close();
		}
	}
	//--------------------------------ADD DEPARTMENT USING @Builder--------------------------
	/*private static void addDepratment() {
		SessionFactory sessionFactory =  new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		  Session session = sessionFactory.openSession();
		  try {
			  session.beginTransaction();
			 Department depatrment = Department.builder().departmentName("ggg").build();
			  session.persist(depatrment);
			  session.getTransaction().commit();
			  
		} catch (Exception e) {
			// TODO: handle exception
			session.close(); 
		}
	}*/
	//--------------------------------ADD DEPARTMENT------------------------------------
	private static void addDepartment(String departmentName) {
		SessionFactory sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		  Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Department department = new Department();
			department.setDepartmentName(departmentName);
			session.persist(department);
			session.getTransaction().commit();	
		}finally {
			session.close();
		}
	}
	//------------------------------------EMPLOYEE 10TH MAX SALARY----------------------------------------
	public static void employee10thMaxSalary() {
		SessionFactory sessionFactory =  new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		  Session session = sessionFactory.openSession();
		  try {
			  List<Employee> empList ;
			  empList = session.createCriteria(Employee.class)
						.addOrder(Order.desc("salry"))
						.setFirstResult(9)
						.setMaxResults(1)
						.list();
			for(Employee employee : empList) {
			    logger.info(employee.getFirstName()+" "+employee.getSalary());
			}	 	
		} catch (Exception e) {
			logger.error("Could not get the result!");
		}finally {	
		}
	}
	//--------------------------------NUMBER OF EMPLOYEE IN EACH DEPARTMENT--------------------------------------------
	public static void employeePerDepartments() {
		
	}
}

