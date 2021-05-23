package lambda_test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

/**
 * Lambda Expression with JDK standard function interface java.util.function.Function
 *
 */
public class LambdaTest3 {
	
	public static void main(String[] args) {
		
		final List<Person> persons = ModelFactory.getPersons();
	
		//Using functional code in Lambda Expression with Predicate, Consumer and  java.util.Function interface as anonymous inner class
		showFilteredPerson(persons, 
				
				p -> p.getAge() > 30 && p.getAge() < 60,  	//Predicate
				
				new Function<Person, String>() {			//Function
					@Override
					public String apply(Person p) {
						return p.getName();
					}
				}, 
				
				name -> System.out.println(name));			//Consumer
		
//=================================================================================================================================//
		System.out.println("---------------------------------------");
		
		//Using functional code in Lambda Expression with Predicate, Consumer, Function
		showFilteredPerson(persons, 
				
				p -> p.getAge() > 30 && p.getAge() < 60,      //Predicate
				
				p -> p.getName(),							  //Function
				
				name -> System.out.println(name));			  //Consumer
		
	}//Main Ends Here

	private static void showFilteredPerson(final List<Person> persons, Predicate<Person> personFilter,  
			Function<Person, String> personMapper, Consumer<String> personAction) {
	
		for(Person person : persons) {
			
			if(personFilter.test(person)) {
				String result = personMapper.apply(person);
				personAction.accept(result);
			}
		}
	}
	
}
