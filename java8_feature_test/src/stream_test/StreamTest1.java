package stream_test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

/**
 * Aggregate Operations that accept Lambda Expressions as Parameters
 *
 */
public class StreamTest1 {

	public static void main(String[] args) {
		
		final List<Person> persons = ModelFactory.getPersons();
		
//=====================================================================================================
		//Using Aggregate Operations that accept Functional Interfaces (Using Anonymous Inner class)
		
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
		
		
	}  //Main Ends Here
	
}
