package stream_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

/**
 * This is a Mutable Reduction operation using collect(Collector)
 *
 */
public class CollectorTest1 {

	// <R,A> R collect(Collector<? super T,A,R> collector)
	
	public static void main(String[] args) {
		
		//Find name of each person working in HR dept.
		List<Employee> employees = ModelFactory.getEmployees();

//=====================================================================================================================//
		usingMethodReferences(employees);
		usingLambdaExpression(employees);
		usingAnonymousInnerClass(employees);
		
	}//End of Main
	
	//Using Method References
	private static void usingMethodReferences(List<Employee> employees) {
		System.out.println("======= Using Method References ====================");
	
		List<String> collect = employees.stream()
				
				.filter(emp -> emp.getDept().getName().equals("HR"))
				
				.map(Employee :: getPerson)
				
				.map(Person :: getName)
				
				.collect(new Collector<String, List<String>, List<String>>(){

					@Override
					public Supplier<List<String>> supplier() {
						return ArrayList :: new;
					}

					@Override
					public BiConsumer<List<String>, String> accumulator() {
						return List :: add;
					}

					@Override
					public BinaryOperator<List<String>> combiner() {
						return (result1, result2) -> {
							result1.addAll(result2);
							return result1;
						};
					}

					@Override
					public Function<List<String>, List<String>> finisher() {
						return ArrayList :: new;
					}

					@Override
					public Set<Characteristics> characteristics() {
						return Collections.emptySet();
					}
					
				});
		
		System.out.println(collect);
		
	}
		
	
	
	
	//Using Lambda Expressions
	private static void usingLambdaExpression(List<Employee> employees) {
		System.out.println("======= Using Lambda Expressions ====================");
	
		List<String> collect = employees.stream()
		
		.filter(emp -> emp.getDept().getName().equals("HR"))
		
		.map(emp -> emp.getPerson().getName())
		
		.collect(new Collector<String, List<String>, List<String>>(){

			@Override
			public Supplier<List<String>> supplier() {
				return () -> new ArrayList<>();
			}

			@Override
			public BiConsumer<List<String>, String> accumulator() {
				return (result, input) -> result.add(input);
			}

			@Override
			public BinaryOperator<List<String>> combiner() {
				return (result1, result2) -> {
					result1.addAll(result2);
					return result1;
				};
			}

			@Override
			public Function<List<String>, List<String>> finisher() {
				return result -> result;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return Collections.emptySet();
			}
			
		});
		
		System.out.println(collect);
		
	}
	
	//Using anonymous Inner class.
	private static void usingAnonymousInnerClass(List<Employee> employees) {
		System.out.println("======= Using anonymous Inner class ====================");
			
			List<String> names = employees.stream()
				
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
			
			.collect(new Collector<String, List<String>, List<String>>() {

				@Override
				public Supplier<List<String>> supplier() {

					return new Supplier<List<String>>() {

						@Override
						public List<String> get() {
							return new ArrayList<>();
						}
					};
				}

				@Override
				public BiConsumer<List<String>, String> accumulator() {
					return new BiConsumer<List<String>, String>() {

						@Override
						public void accept(List<String> t, String u) {
							t.add(u);
						}
					};
				}

				@Override
				public BinaryOperator<List<String>> combiner() {

					return new BinaryOperator<List<String>>() {

						@Override
						public List<String> apply(List<String> t, List<String> u) {
							t.addAll(u);
							return t;
						}
					};
				}

				@Override
				public Function<List<String>, List<String>> finisher() {
					return new Function<List<String>, List<String>>() {

						@Override
						public List<String> apply(List<String> t) {
							return t;
						}
					};
				}

				@Override
				public Set<Characteristics> characteristics() {
					return Collections.emptySet();
				}
			});
			
			System.out.println(names);
	}
	
}
