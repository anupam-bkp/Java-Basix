package stream_test;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

/**
 * Aggregate Operations that accept Lambda Expressions as Parameters
 *
 */
public class StreamTest1 {

	public static void main(String[] args) {
		
		streamCreationTest1();
		streamCreationTest2();
		
	}  //Main Ends Here
	
	private static void streamCreationTest1() {
		
		Stream<Integer> of = Stream.of(10);
		
		Stream<Integer> of2 = Stream.of(10, 20, 30 , 40);
//		System.out.println("Count : " + of2.count());
		
		Stream<Integer> concat = Stream.concat(of, of2);
//		System.out.println("Count : " + concat.count());
		
		Stream<Integer> distinct = concat.distinct();
		System.out.println("Count : " + distinct.count());
		
		Stream<Integer> generate = Stream.generate(new Supplier<Integer>() {

			@Override
			public Integer get() {
				return 10;
			}
		});
		
		
	}
	
	private static void streamCreationTest2() {
		
		Stream<Integer> iterate = Stream.iterate(10, new UnaryOperator<Integer>() {

			@Override
			public Integer apply(Integer t) {
				return t+10;
			}
		});
		
		iterate.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.println(t);
			}
		});
		
		
	}
	
	//Using Aggregate Operations that accept Functional Interfaces (Using Anonymous Inner class)
	private static void aggregateOperationTest1() {
		
		final List<Person> persons = ModelFactory.getPersons();
		
		persons.stream()
		
		.filter(new Predicate<Person>() {

			@Override
			public boolean test(Person p) {
				return p.getAge() > 30 && p.getAge() < 60;
			}
		})
		
		.map(new Function<Person, String>() {

			@Override
			public String apply(Person p) {
				return p.getName();
			}
		})
		
		.forEach(new Consumer<String>() {

			@Override
			public void accept(String name) {
				System.out.println(name);
			}
		});
		
//========================================================================================================================//
		System.out.println("===========================");
		
		//Using Aggregate Operations that accept Functional Interfaces (Using Lambda Expressions)
		
		persons.stream()
			.filter(p -> p.getAge() > 30 && p.getAge() < 60)
			.map(p -> p.getName())
			.forEach(str -> System.out.println(str));
		
		
	}
}
