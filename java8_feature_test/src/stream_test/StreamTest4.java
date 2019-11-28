package stream_test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class StreamTest4 {

	public static void main(String[] args) {
		
		List<Employee> employees = ModelFactory.getEmployees();
		maxTest(employees);
	}
	
	//Optional<T> max(Comparator<? super T> comparator)
	private static void maxTest(List<Employee> employees) {
		Optional<Employee> max = employees.stream().max(StreamTest4 :: compareEmployeeByName);
		if(max.isPresent()) {
			System.out.println(max.get().getEmployeeName());
		}
		
	}

	private static int compareEmployeeByName(Employee e1, Employee e2){
		return e1.getEmployeeName().compareTo(e2.getEmployeeName()); 
	}
	
	private static int compareEmployeeBySalary(Employee e1, Employee e2){
		return e1.getSalary() > e2.getSalary() ? 1 : -1;
	}
}
