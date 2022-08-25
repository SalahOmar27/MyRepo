package hibernatePackage;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Demo {
	static Logger logger = LoggerFactory.getLogger(Demo.class);

	public static void main(String[] args) {
		addDepratment();             //using @builder
		addEmployee();               //using @builder
		employee10thMaxSalary();
		employeePerDepartments();
		//addEmployee("Sameer", "Omar", "0791124447", "basel@gmail.com",1650, 6);
		//addDepartment("Finance");
	}
	//--------------------------------ADD DEPARTMENT USING @Builder--------------------------
   private static void addDepratment() {
		SessionFactory sessionFactory =  new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		  try {
		     session.beginTransaction();
			 Department depatrment = Department.builder().departmentName("Security").build();
			 session.persist(depatrment);
			 session.getTransaction().commit();
				  
			 }catch (Exception e) {
				// TODO: handle exception
				session.close(); 
			}
		}
		//--------------------------------ADD DEPARTMENT------------------------------------
	/*private static void addDepartment(String departmentName) {
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
		}*/
   //-------------------------------------ADD EMPLOYEE USING @Builder-------------------------
   private static void addEmployee() {
		SessionFactory sessionFactory =  new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Employee employee= Employee.builder().firstName("Leen")
					.lastName("Jehad").phone("079570001")
					.email("leen@gmail.com").salary(1750)
					.departmentId(4).build();
			session.persist(employee);
			session.getTransaction().commit();
	
		}catch (Exception e) {
			logger.error("Oppes, could not add infromation, please enter correct info!");
			session.close();
		}
	}
	//------------------------------------ADD EMPLOYEE----------------------------------------
	/*private static void addEmployee(String first_name, String last_name, String phone, String email, int salary,int deptid) {
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
	*/
	//------------------------------------EMPLOYEE 10TH MAX SALARY----------------------------------------
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void employee10thMaxSalary() {
		SessionFactory sessionFactory =  new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			
		  List<Employee> empList;
		  empList = session.createCriteria(Employee.class)
					.addOrder(Order.desc("salary"))
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
		SessionFactory sessionFactory =  new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

