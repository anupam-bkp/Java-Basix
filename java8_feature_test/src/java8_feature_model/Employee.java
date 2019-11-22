package java8_feature_model;

public class Employee {

	int id;

	Person person;
	
	Department dept;

	int salary;

	public Employee(int id, int salary) {
		super();
		this.id = id;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getEmployeeDeptName() {
		return this.dept.getName();
	}
	
	public String getEmployeeName() {
		return this.person.getName();
	}
	
	@Override
	public String toString() {
		return this.getPerson().getName();
	}
	
	
}
