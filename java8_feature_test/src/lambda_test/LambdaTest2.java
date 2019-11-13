package lambda_test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

/**
 * Lambda Expression with JDK Standard Functional Interfaces (Predicate and Consumer)
 * java.util.function package
 *
 */
public class LambdaTest2 {

	public static void main(String[] args) {
		
		final List<Person> persons = ModelFactory.getPersons();
		
//=================================================================================================================//		
		//Using function code in anonymous inner class with java.util.Predicate functional interface
		showFilteredPerson(persons, new Predicate<Person>() {

			@Override
			public boolean test(Person p) {
				return p.getAge() > 30 && p.getAge() < 60;
			}
		});

//==================================================================================================================//
		
		System.out.println("============================");
		//Using function code in Lambda Expression with java.util.Predicate functional interface
		showFilteredPerson(persons, p -> (p.getAge() > 30 && p.getAge() < 60));
		
//==================================================================================================================//		
		
		System.out.println("======================================");
		//Using function code in Lambda Expression with java.util.Predicate and java.util.Consumer functional interface in anonymous inner class
		showFilteredPerson(
				
				persons, 									//models
				
				p -> (p.getAge() > 30 && p.getAge() < 60),  //Predicate
				
				new Consumer<Person>() {					//Consumer
					@Override
					public void accept(Person t) {
						System.out.println(t);
					}
				});
		
//====================================================================================================================//		
		
		System.out.println("====================================");
		//Using function code in Lambda Expression with java.util.Predicate and java.util.Consumer functional interface
		showFilteredPerson(
				persons, 										//models
				p -> (p.getAge() > 30 && p.getAge() < 60),		//Predicate
				p -> System.out.println(p));					//Consumer
		
	} //Main Ends Here
	
	
	
	private static void showFilteredPerson(final List<Person> persons, Predicate<Person> personFilter){
		for(Person person : persons) {
			if(personFilter.test(person)) {
				System.out.println(person);
			}
		}
	}
	
	private static void showFilteredPerson(final List<Person> persons, Predicate<Person> personFilter, Consumer<Person> personAction) {
		
		for(Person person : persons) {
			if(personFilter.test(person)) {
				personAction.accept(person);
			}
		}
		
	}
}
