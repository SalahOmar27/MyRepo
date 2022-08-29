package hibernate.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {

	public static void main(String[] args) {
		addDepratment(); // using @builder
		// addEmployee(); // using @builder
		employee10thMaxSalary();
		employeePerDepartments();
		addEmployee("Salah", "Omar", "0788567568", "salah@gmail.com", 1450, 6);
		// addDepartment("Finance");
	}

	// ADD DEPARTMENT USING @Builder
	private static void addDepratment() {
		SessionFactory sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Department depatrment = Department.builder().departmentName("IT").build();
			session.persist(depatrment);
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ADD DEPARTMENT
	/*
	 * private static void addDepartment(String departmentName) { SessionFactory
	 * sessionFactory = new
	 * Configuration().configure("Hibernate.cfg.xml").buildSessionFactory(); Session
	 * session = sessionFactory.openSession(); try { session.beginTransaction();
	 * Department department = new Department();
	 * department.setDepartmentName(departmentName); session.persist(department);
	 * session.getTransaction().commit();
	 * 
	 * } catch (Exception e) { // TODO: handle exception } }
	 */
	// ADD EMPLOYEE
	private static void addEmployee(String first_name, String last_name, String phone, String email, int salary,
			int deptid) {
		SessionFactory sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Employee employee = new Employee();
			employee.setFirstName(first_name);
			employee.setLastName(last_name);
			employee.setPhone(phone);
			employee.setEmail(email);
			employee.setSalary(salary);
			employee.setDepartmentId(deptid);
			session.persist(employee);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			log.error("Oppes, could not add infromation, please enter correct info!");
		}
	}

	// ADD EMPLOYEE USING @Builder
	/*
	 * private static void addEmployee() { SessionFactory sessionFactory = new
	 * Configuration().configure("Hibernate.cfg.xml").buildSessionFactory(); Session
	 * session = sessionFactory.openSession(); try { session.beginTransaction();
	 * Employee employee =
	 * Employee.builder().firstName("Leen").lastName("Jehad").phone("0785794401")
	 * .email("leen@gmail.com").salary(1400).departmentId(1).build();
	 * session.persist(employee); session.getTransaction().commit();
	 * 
	 * } catch (Exception e) {
	 * log.error("Oppes, could not add infromation, please enter correct info!"); }
	 * }
	 */
	// EMPLOYEE 10TH MAX SALARY
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void employee10thMaxSalary() {
		SessionFactory sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {

			List<Employee> empList;
			empList = session.createCriteria(Employee.class).addOrder(Order.desc("salary")).setFirstResult(9)
					.setMaxResults(1).list();

			for (Employee employee : empList) {
				log.info(employee.getFirstName() + " " + employee.getSalary());

			}
		} catch (Exception e) {
			log.error("Could not get the result!");
		}
	}

	// NUMBER OF EMPLOYEE IN EACH DEPARTMENT
	public static void employeePerDepartments() {
		SessionFactory sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
			Root<Employee> employee = criteriaQuery.from(Employee.class);
			Join<Employee, Department> department = employee.join("departmentId", JoinType.LEFT);
			criteriaQuery.multiselect(department.<String>get("departmentName").alias("name"),
					builder.count(employee).alias("empInDept"));
			criteriaQuery.groupBy(employee.<Long>get("name"));

			Query query = session.createQuery(criteriaQuery);
			List<Tuple> results = query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			log.error("Oppes!");
		}
	}
}
