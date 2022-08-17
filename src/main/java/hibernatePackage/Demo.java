package hibernatePackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddDepartment("IT");
		
		AddEmployee("Mazen", "ali", "0795284937", "mazen@gmail.com",2250, 5);
		
		Employee10thMaxSalary();
		
		EmployeePerDepartments();

	}
	
	//----------------------------------------------------------------------------
	
	private static void AddEmployee(String fn, String ln, String tel, String email, int sal,int deptid) {
		SessionFactory sessionFactory = new  Configuration().configure().buildSessionFactory();
		  Session session = sessionFactory.openSession();
		
		try {
			Connection con =dataSource.getConnection();
			session.beginTransaction();
			Employee emp= new Employee();
			emp.setFirst_name(fn);
			emp.setLast_name(ln);
			emp.setPhone(tel);
			emp.setEmail(email);
			emp.setSalary(sal);
			emp.setDeptid(deptid);
			
			session.persist(emp);
			session.getTransaction().commit();
	
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
			dataSource.close();
		}

	}
	//--------------------------------------------------------------------
	private static void AddDepartment(String name) {
		SessionFactory sessionFactory = new  Configuration().configure().buildSessionFactory();
		  Session session = sessionFactory.openSession();
		
		try {
			Connection con =dataSource.getConnection();
			session.beginTransaction();
			Department dept = new Department();
			dept.setDept_Name(name);
			
			session.persist(dept);
			session.getTransaction().commit();	
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
			dataSource.close();
		}
		
	}
	//----------------------------------------------------------------------------
	public static void Employee10thMaxSalary() {
		SessionFactory sessionFactory = new  Configuration().configure().buildSessionFactory();
		  Session session = sessionFactory.openSession();
		  try {
			  Connection con =dataSource.getConnection();
	          Criteria cr = session.createCriteria(Employee.class);
			  List<Employee> empList = cr.list();
			  empList = session.createCriteria(Employee.class)
						.addOrder(Order.desc("salary"))
						.setFirstResult(9)
						.setMaxResults(1)
						.list();
			for(Employee emp : empList) {
				
				System.out.println(emp.getFirst_name()+" "+emp.getSalary());
				
			}
			 
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			dataSource.close();
		}
	}
	//----------------------------------------------------------------------------
	public static void EmployeePerDepartments() {
		SessionFactory sessionFactory = new  Configuration().configure().buildSessionFactory();
		  Session session = sessionFactory.openSession();
		  try {
		
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			dataSource.close();
		}
	}
	//----------------------------------------------------------------------------
	protected static HikariDataSource dataSource ;

	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/salahdb");
		config.setUsername("root");
		config.setPassword("1234");
	//	config.addDataSourceProperty("minimumIdle", "5");
	//	config.addDataSourceProperty("maximumPoolSize", "25");
		dataSource = new HikariDataSource(config);
	}
}
