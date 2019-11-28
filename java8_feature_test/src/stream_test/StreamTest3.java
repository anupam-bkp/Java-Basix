package stream_test;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class StreamTest3 {

	public static void main(String[] args) {
		
		List<Employee> employees = ModelFactory.getEmployees();
		
//		peekTest(employees);
//		skipTest(employees);
		otherFunctions(employees);
	}
	
	//Stream<T> skip(long n)
	private static void skipTest(List<Employee> employees) {
		
		long count = employees.stream().skip(3).count();
		System.out.println(count);
		
		employees.stream().peek(emp -> System.out.println("Peek : " + emp.getEmployeeDeptName()))
		.map(new Function<Employee, String>() {

			@Override
			public String apply(Employee t) {
				System.out.println("Mapped");
				return t.getEmployeeName();
			}
		})
		.skip(3).forEach(emp -> System.out.println("ForEach : " + emp));
		
	}
	
	//Stream<T> peek(Consumer<? super T> action)
	private static void peekTest(List<Employee> employees) {
		
		//Using Anonymous Inner Class
		employees.stream().peek(new Consumer<Employee>() {

			@Override
			public void accept(Employee t) {
				System.out.println("Peeked : " + t.getDept().getName());
			}
		}).map(new Function<Employee, String>() {

			@Override
			public String apply(Employee t) {
				System.out.println("Mapped");
				return t.getPerson().getName();
			}
		}).forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println("ForEach : " + t);
			}
		});
		
		System.out.println("======================");
		
		//Using Lambda Expressions
		employees.stream()
			.peek(emp -> System.out.println("Peeked : " + emp.getEmployeeDeptName()))
			.map(emp -> emp.getEmployeeName())
			.forEach(emp -> System.out.println("ForEach : " + emp));
		
		System.out.println("======================");
		//Using Method References
		employees.stream()
			.peek(emp -> System.out.println(emp.getEmployeeDeptName()))
			.map(Employee :: getEmployeeName)
			.forEach(System.out :: println);
	}
	
	//allMatch, anyMatch, noneMatch, findFirst, findAny
	private static void otherFunctions(List<Employee> employees) {
		
		//boolean allMatch(Predicate<? super T> predicate)
		boolean allMatch = employees.stream().map(Employee :: getEmployeeDeptName).allMatch(deptName -> "HR".equals(deptName));
		System.out.println(allMatch);
		
		//boolean anyMatch(Predicate<? super T> predicate)
		boolean anyMatch = employees.stream().map(Employee :: getEmployeeDeptName).anyMatch(deptName -> "HR".equals(deptName));
		System.out.println(anyMatch);
		
		//boolean noneMatch(Predicate<? super T> predicate)
		boolean noneMatch = employees.stream().map(Employee :: getEmployeeDeptName).noneMatch(deptName -> "HR".equals(deptName));
		System.out.println(noneMatch);
		
		//Optional<T> findFirst()
		Optional<String> findFirst = employees.stream().map(Employee :: getEmployeeName).findFirst();
		if(findFirst.isPresent()) {
			System.out.println(findFirst.get());
		}
		
		//Optional<T> findAny()
		Optional<String> findAny = employees.stream().map(Employee :: getEmployeeName).findAny();
		if(findFirst.isPresent()) {
			System.out.println(findAny.get());
		}
	}
	
}
