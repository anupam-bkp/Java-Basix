package stream_test;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class StreamTest2 {
	
	public static void main(String[] args) {
		
		List<Employee> employees = ModelFactory.getEmployees();
		
		mapTest(employees);
//		mapToIntTest(employees);
//		flatMapTest(employees);
//		flatMapToIntTest(employees);
	}

	//<R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
	private static void flatMapTest(List<Employee> employees) {
		//Input
		Map<String, List<String>> collect = employees.stream()
			.collect(Collectors.groupingBy(
					Employee :: getEmployeeDeptName, 
					Collectors.mapping(
							Employee :: getEmployeeName, 
							Collectors.toList())));
				
		System.out.println(collect);
			
		
		//Using Anonymous Inner Class
		collect.values().stream().flatMap(new Function<List<String>, Stream<String>>() {

			@Override
			public Stream<String> apply(List<String> t) {
				return t.stream();
			}
		}).forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		System.out.println("============================");
		
		//Using Lambda Expressions
		collect.values().stream()
			.flatMap(nameList -> nameList.stream())
			.forEach(name -> System.out.println(name));
		
		System.out.println("============================");
		
		//Using Method References
		collect.values().stream()
			.flatMap(List :: stream)
			.forEach(System.out :: println);
	}
	
	//IntStream flatMapToInt(Function<? super T,? extends IntStream> mapper)
	private static void flatMapToIntTest(List<Employee> employees) {
		
		//Input
		Map<String, List<Employee>> collect = employees.stream()
			.collect(Collectors.groupingBy(Employee :: getEmployeeDeptName));
		
		//Using Anonymous Inner Class
		collect.values().stream()
			.flatMapToInt(new Function<List<Employee>, IntStream>() {

				@Override
				public IntStream apply(List<Employee> empList) {
					return empList.stream().mapToInt(new ToIntFunction<Employee>() {

						@Override
						public int applyAsInt(Employee emp) {
							return emp.getSalary();
						}
					});
				}
			})
			
			.forEach(new IntConsumer() {
				
				@Override
				public void accept(int value) {
					System.out.println(value);
				}
			});
		
		System.out.println("======================");
		
		//Using Lambda Expressions
		collect.values().stream()
			.flatMapToInt(empList -> empList.stream().mapToInt(emp1 -> emp1.getSalary()))
			.forEach(sal -> System.out.println(sal));
	}
	
	//<R> Stream<R> map(Function<? super T,? extends R> mapper)
	private static void mapTest(List<Employee> employees) {
		
		//Using Anonymous Inner Class
		employees.stream().map(new Function<Employee, String>(){

			@Override
			public String apply(Employee t) {
				return t.getPerson().getName();
			}
		}).forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		
		System.out.println("----------------------");
		//Using Lambda Expressions
		employees.stream()
			.map(emp -> emp.getPerson().getName())
			.forEach(name -> System.out.println(name));
	
		System.out.println("------------------------");
		//Using Method References
		employees.stream()
			.map(Employee :: getEmployeeName)
			.forEach(System.out :: println);
		
	}
	
	//IntStream mapToInt(ToIntFunction<? super T> mapper)
	private static void mapToIntTest(List<Employee> employees) {
		
		//Using Anonymous Inner Class
		employees.stream().mapToInt(new ToIntFunction<Employee>() {

			@Override
			public int applyAsInt(Employee emp) {
				return emp.getSalary();
			}
		}).forEach(new IntConsumer() {
			
			@Override
			public void accept(int value) {
				System.out.println(value);
			}
		});
		
		System.out.println("==============================");
		//Using Method Reference
		OptionalDouble average = employees.stream().mapToInt(Employee :: getSalary).average();
		if(average.isPresent()) {
			System.out.println(average.getAsDouble());
		}
		
	}
	
}
