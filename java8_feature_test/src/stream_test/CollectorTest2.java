package stream_test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

public class CollectorTest2 {
	
	// <R,A> R collect(Collector<? super T,A,R> collector)
	
	
	/*
	 //Returns a new Collector described by the given supplier, accumulator, and combiner functions.
	 static <T,R> Collector<T,R,R> of(Supplier<R> supplier, BiConsumer<R,T> accumulator,
                                 BinaryOperator<R> combiner, Collector.Characteristics... characteristics)
		
	//Returns a new Collector described by the given supplier, accumulator, combiner, and finisher functions.	
	 static <T,A,R> Collector<T,A,R> of(Supplier<A> supplier, BiConsumer<A,T> accumulator,
                                   BinaryOperator<A> combiner,Function<A,R> finisher,
                                   Collector.Characteristics... characteristics) 
	*/
	
	public static void main(String[] args) {

		//Find name of each person working in HR dept.
		List<Employee> employees = ModelFactory.getEmployees();
		
		usingMethodReferences(employees);
		usingLambdaExpression(employees);
		usingAnonymousInnerClass(employees);
		
	}  //Main Ends Here
	
	//Using Method References
	private static void usingMethodReferences(List<Employee> employees) {
		System.out.println("======= Using Method References ====================");
	
		List<String> collect = employees.stream()
				
				.filter(emp -> emp.getDept().getName().equals("HR"))
				
				.map(Employee :: getPerson)
				
				.map(Person :: getName)
				
				/*.collect(Collector.of(
						
						ArrayList :: new, 
						
						List :: add, 
						
						(accumulator1, accumulator2) -> null, 
						
						Characteristics.IDENTITY_FINISH));*/
		
				.collect(Collector.of(
						
						ArrayList :: new, 
						
						List :: add, 
						
						(acc1, acc2) -> null, 
						
						accumulator -> null,
						
						Characteristics.IDENTITY_FINISH));
		
			
		System.out.println(collect);
		
	}						
						
	//Using Lambda Expressions
		private static void usingLambdaExpression(List<Employee> employees) {
			System.out.println("======= Using Lambda Expressions ====================");
		
			List<String> collect = employees.stream()
			
			.filter(emp -> emp.getDept().getName().equals("HR"))
			
			.map(emp -> emp.getPerson().getName())
			
			/*.collect(Collector.of(
					
					() -> new ArrayList<>(), 
					
					(accumulator, input) -> accumulator.add(input), 
					
					(accumulator1, accumulator2) -> {
//						accumulator1.addAll(accumulator2);
						return null;
					},
					
					Characteristics.IDENTITY_FINISH));*/
			
			.collect(Collector.of(
					
					() -> new ArrayList<String>(), 
					
					(accumulator, input) -> accumulator.add(input), 
					
					(accumulator1, accumulator2) -> {
//						accumulator1.addAll(accumulator2);
						return null;
					},
					
					accumulator -> null,
					
					Characteristics.IDENTITY_FINISH));
			
			System.out.println(collect);
			
		}
	
	//Using anonymous Inner class.
		private static void usingAnonymousInnerClass(List<Employee> employees) {
			System.out.println("======= Using anonymous Inner class ====================");
				
				List<String> collect = employees.stream()
					
				.filter(new Predicate<Employee>() {

						@Override
						public boolean test(Employee emp) {
							return emp.getDept().getName().equals("HR");
						}
					})
				
				.map(new Function<Employee, String>() {

					@Override
					public String apply(Employee emp) {
						return emp.getPerson().getName();
					}
				})
				
				/*.collect(Collector.of(
						
						new Supplier<List<String>>() {

							@Override
							public List<String> get() {
								return new ArrayList<>();
							}							
						}, 
						
						new BiConsumer<List<String>, String>() {

							@Override
							public void accept(List<String> t, String u) {
								t.add(u);
							}
						}, 
						
						new BinaryOperator<List<String>>() {

							@Override
							public List<String> apply(List<String> t, List<String> u) {
								return null;
							}
						}, 
						
						Characteristics.IDENTITY_FINISH));*/
				
				.collect(Collector.of(
						
						new Supplier<List<String>>() {

							@Override
							public List<String> get() {
								return new ArrayList<>();
							}
						}, 
						
						new BiConsumer<List<String>, String>() {

							@Override
							public void accept(List<String> t, String u) {
								t.add(u);
							}
						}, 
						
						new BinaryOperator<List<String>>() {

							@Override
							public List<String> apply(List<String> t, List<String> u) {
								return null;
							}
						}, 
						
						new Function<List<String>, List<String>>() {

							@Override
							public List<String> apply(List<String> t) {
								return null;
							}
						}, 
						
						Characteristics.IDENTITY_FINISH));
				
				System.out.println(collect);
	
		}
}
