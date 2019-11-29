package stream_test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class StreamTest4 {

	public static void main(String[] args) {
		
		List<Employee> employees = ModelFactory.getEmployees();
		maxTest(employees);
		minTest(employees);
		sortedTest1(employees);
		sortedTest2(employees);
		
		toArrayTest(employees);
	}
	
	//Optional<T> max(Comparator<? super T> comparator)
	private static void maxTest(List<Employee> employees) {
		
		//Using Anonymous Inner Class
		Optional<Employee> max3 = employees.stream().max(new Comparator<Employee>(){

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getEmployeeName().compareTo(o2.getEmployeeName());
			}
		});
		
		if(max3.isPresent()) {
			System.out.println(max3.get().getEmployeeName());
		}		
		
		//Using Lambda Expressions
		Optional<Employee> max2 = employees.stream().max((emp1, emp2) -> emp1.getEmployeeName().compareTo(emp2.getEmployeeName()));
		if(max2.isPresent()) {
			System.out.println(max2.get().getEmployeeName());
		}
		
		//Method References
		Optional<Employee> max = employees.stream().max(StreamTest4 :: compareEmployeeByName);
		if(max.isPresent()) {
			System.out.println(max.get().getEmployeeName());
		}
		
		Optional<Employee> max4 = employees.stream().max(StreamTest4 :: compareEmployeeBySalary);
		if(max4.isPresent()) {
			System.out.println(max4.get().getEmployeeName() + " : " + max4.get().getSalary());
		}
	}

	//Optional<T> min(Comparator<? super T> comparator)
	private static void minTest(List<Employee> employees) {

		Optional<Employee> min = employees.stream().min(new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getEmployeeName().compareTo(o2.getEmployeeName());
			}
		});
		
		if(min.isPresent()) {
			System.out.println(min.get().getEmployeeName());
		}
		
		Optional<Employee> min2 = employees.stream().min(StreamTest4 :: compareEmployeeByName);
		if(min2.isPresent()) {
			System.out.println(min2.get().getEmployeeName());
		}
		
		Optional<Employee> min3 = employees.stream().min(StreamTest4 :: compareEmployeeBySalary);
		if(min3.isPresent()) {
			System.out.println(min3.get().getEmployeeName() + " :: " + min3.get().getSalary());
		}
	}

	//	Stream<T> sorted()
	private static void sortedTest1(List<Employee> employees) {
		
//		employees.stream().sorted().forEachOrdered(System.out :: println);
		
		employees.stream().map(Employee :: getEmployeeName).sorted().forEach(System.out :: println);
	}
	
	//Stream<T> sorted(Comparator<? super T> comparator)
	private static void sortedTest2(List<Employee> employees) {
		employees.stream().sorted(StreamTest4 :: compareEmployeeByName).forEachOrdered(System.out :: println);
		System.out.println("-------------------");
		employees.stream().sorted(StreamTest4 :: compareEmployeeBySalary).forEachOrdered(System.out :: println);
	}
	
	private static void toArrayTest(List<Employee> employees) {
	
		//Object[] toArray()
		Object[] array = employees.stream().map(Employee :: getEmployeeName).toArray();
		System.out.println(Arrays.toString(array));
		
		//<A> A[] toArray(IntFunction<A[]> generator)
		
		String[] array2 = employees.stream().map(Employee :: getEmployeeName).toArray(new IntFunction<String[]>() {

			@Override
			public String[] apply(int value) {
				return new String[value];
			}
		});
		
		System.out.println(Arrays.toString(array2));
		
		String[] array3 = employees.stream().map(Employee :: getEmployeeName).toArray(String[] :: new);
		System.out.println(Arrays.toString(array3));
	}
	
	
	//Comparator for Employee Name
	private static int compareEmployeeByName(Employee e1, Employee e2){
		return e1.getEmployeeName().compareTo(e2.getEmployeeName()); 
	}
	
	//Comparator for Salary
	private static int compareEmployeeBySalary(Employee e1, Employee e2){
		return e1.getSalary() > e2.getSalary() ? 1 : -1;
	}
}
