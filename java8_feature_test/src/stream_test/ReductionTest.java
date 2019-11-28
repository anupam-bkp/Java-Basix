package stream_test;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class ReductionTest {

	public static void main(String[] args) {
		
		List<Employee> employees = ModelFactory.getEmployees();
		
		reductionTest1(employees);
//		reductionTest2(employees);
//		reductionTest3(employees);
	}
	
	/*
	 Optional<T> reduce(BinaryOperator<T> accumulator)
	 */
	private static void reductionTest1(List<Employee> employees) {
		
		//Using Anonymous Inner Class
		Optional<String> reduce = employees.stream()
			.filter(new Predicate<Employee>() {

				@Override
				public boolean test(Employee t) {
					return "HR".equals(t.getEmployeeDeptName());
				}
		})
			
		.map(new Function<Employee, String>() {

			@Override
			public String apply(Employee t) {
				return t.getEmployeeName();
			}
		})
		
		.reduce(new BinaryOperator<String>() {

			@Override
			public String apply(String t, String u) {
				return t+"::"+u;
			}
		});
		
		if(reduce.isPresent()) {
			System.out.println(reduce.get());
		}else {
			System.out.println("No value");
		}	
		
		//Using Lambda Expressions
		Optional<String> reduce2 = employees.stream()
			.filter(emp -> "HR".equals(emp.getEmployeeDeptName()))
			.map(emp -> emp.getEmployeeName())
			.reduce((emp1, emp2) -> emp1 + "::" + emp2);
		
		if(reduce2.isPresent()) {
			System.out.println(reduce2.get());
		}else {
			System.out.println("No value");
		}	
		
		//Using Method References
		Optional<String> reduce3 = employees.stream()
			.filter(emp -> "HR".equals(emp.getEmployeeDeptName()))
			.map(Employee :: getEmployeeName)
			.reduce(String :: concat);
		
		if(reduce3.isPresent()) {
			System.out.println(reduce3.get());
		}else {
			System.out.println("No value");
		}	
	}
	
	/*
	 * T reduce(T identity, BinaryOperator<T> accumulator)
	 */
	private static void reductionTest2(List<Employee> employees) {
		
		//Using Anonymous Inner Class
		String reduce = employees.stream()
			.filter(emp -> "HR".equals(emp.getDept().getName()))
			.map(emp -> emp.getEmployeeName())
			.reduce(
					"Hello", 
					
					new BinaryOperator<String>() {

						@Override
						public String apply(String t, String u) {
							return t + "::" + u;
						}
					});
		
		System.out.println(reduce);
			
		//Using Lambda Expressions
		
		String reduce2 = employees.stream()
			.filter(emp -> "HR".equals(emp.getDept().getName()))
			.map(Employee :: getEmployeeName)
			.reduce( 
					"Hello",
					 (st1, st2) -> st1 + " :: " + st2);
		
		System.out.println(reduce2);
	}
	
	/*
	 <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
	 */
	private static void reductionTest3(List<Employee> employees) {
		
		//Using Anonymous Inner class
		StringBuilder reduce = employees.stream()
			
		.filter(emp -> "HR".equals(emp.getDept().getName()))
			
			.reduce(new StringBuilder(), 
					
					new BiFunction<StringBuilder, Employee, StringBuilder>() {

						@Override
						public StringBuilder apply(StringBuilder t, Employee u) {
							return t.append(u.getEmployeeName());
						}
					}, 
					
					new BinaryOperator<StringBuilder>() {

						@Override
						public StringBuilder apply(StringBuilder t, StringBuilder u) {
							return t.append(u.toString());
						}
					});
		
		System.out.println(reduce);
		
		//Using Lambda Expressions
		
		StringBuilder reduce2 = employees.stream()
			
			.filter(emp -> "HR".equals(emp.getDept().getName()))
			
			.reduce(
					new StringBuilder(), 
					
					(t, u) -> t.append(u.getEmployeeName()), 
					
					(t, u) -> t.append(u.toString()));
		
		System.out.println(reduce2);
	}
	
}
