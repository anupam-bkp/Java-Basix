package stream_test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class CollectorsTest3 {

	public static void main(String[] args) {
	
		List<Employee> employees = ModelFactory.getEmployees();

//		partitioningByTest1(employees);
//		partitioningByTest2(employees);
//		collectingAndThenTest(employees);
	}
	
	/*
	 	public static <T> Collector<T,?,Optional<T>> reducing(BinaryOperator<T> op)
	 */
	private static void reducingTest1(List<Employee> employees) {
		
		
		
		
	}
	
	private static void reducingTest2() {
		
		
	}
	
	/*
	 	public static <T> Collector<T,?,Map<Boolean,List<T>>> partitioningBy(Predicate<? super T> predicate)
	 */
	private static void partitioningByTest1(List<Employee> employees) {
		
		Map<Boolean, List<Employee>> collect = employees.stream()
			.collect(Collectors.partitioningBy(
					new Predicate<Employee>() {
						@Override
						public boolean test(Employee t) {
							return "HR".equals(t.getDept().getName());
						}
					}
				)
			);
		
		System.out.println(collect);
		
		//Method References
		Map<Boolean, List<Employee>> collect2 = employees.stream()
			.collect(Collectors.partitioningBy(emp -> "HR".equals(emp.getDept().getName())));
		System.out.println(collect2);
		
	}
	
	/*
	 * public static <T,D,A> Collector<T,?,Map<Boolean,D>> partitioningBy(Predicate<? super T> predicate, Collector<? super T,A,D> downstream)
	 */
	private static void partitioningByTest2(List<Employee> employees) {
		
		Map<Boolean, List<String>> collect = employees.stream()
			.collect(Collectors.partitioningBy(
					emp -> "HR".equals(emp.getDept().getName()),   //Predicate
					
					Collectors.mapping(						      //Downstream
							
							emp -> emp.getEmployeeName(),       //Mapper	
							Collectors.toList()					//Downstream
						)
					)
				);
		
		System.out.println(collect);
		
		Map<Boolean, List<String>> collect2 = employees.stream()
			.collect(Collectors.partitioningBy(
					
					emp -> "HR".equals(emp.getDept().getName()), //predicate
					
					Collectors.mapping(            //Downstream
							
							Employee :: getEmployeeName, 	//Mapper 
							
							Collectors.toList())));			//Downstream
		
		System.out.println(collect2);
		
	}
	
	
	/*
		<T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher)
    */
	private static void collectingAndThenTest(List<Employee> employees) {
		
		List<String> collect = employees.stream()
			.map(emp -> emp.getEmployeeName())
			.collect(Collectors.collectingAndThen(
					
					Collectors.toList(),           //downstream
					
					new Function<List<String>, List<String>>() {		//Finisher

						@Override
						public List<String> apply(List<String> t) {
							return Collections.unmodifiableList(t);
						}
					}
				)
			);
		
		System.out.println(collect);
		
		//Using Method References
		List<String> collect1 = employees.stream()
				.map(Employee :: getEmployeeName)
				.collect(Collectors.collectingAndThen(				
						Collectors.toList(),              //downstream 
						Collections :: unmodifiableList   //Finisher
						)
					);
		
		System.out.println(collect1);
	}
}
