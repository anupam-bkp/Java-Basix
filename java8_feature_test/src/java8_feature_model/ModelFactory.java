package java8_feature_model;

import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
	
	private static final GENDER[] GENDERS = new GENDER[] {GENDER.MALE, GENDER.MALE, GENDER.FEMALE, GENDER.FEMALE, GENDER.MALE};
	private static final String[] NAMES = new String[] {"abc", "yz", "stu", "def", "vwx"};
	private static final int[] AGES = new int[] {10, 50, 30, 20, 40};
	private static final String[] DEPTNAMES = new String[] {"HR", "IT", "HR", "HR", "FINANCE"};
	
	public static List<Person> getPersons(){
		
		List<Person> persons = new ArrayList<>();
		
		for(int i=0; i<NAMES.length; i++) {
			persons.add(new Person(NAMES[i], AGES[i], GENDERS[i].name()));
		}
		
		return persons;
	}	
	
	public static List<Department> getDepartments(){
		
		List<Department> departments = new ArrayList<>();
		
		for(int i=0; i<DEPTNAMES.length; i++) {
			departments.add(new Department(i, DEPTNAMES[i]));
		}
		
		return departments;
	}
	
	public static List<Employee> getEmployees() {
		
		List<Employee> employees = new ArrayList<>();
		
		List<Person> persons = getPersons();
		List<Department> departments = getDepartments();
		
		Employee emp = null;
		for(int i=0; i<persons.size(); i++) {
			emp = new Employee(i, 100 * i);
			emp.setPerson(persons.get(i));
			emp.setDept(departments.get(i));
			employees.add(emp);
		}
		
		return employees;
	}
	
}