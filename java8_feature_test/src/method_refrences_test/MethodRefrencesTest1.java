package method_refrences_test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

/**
 * Sometimes a lambda expressions does nothing but call an existing method.
 * Method References in place of Lambda Expression for methods that already have a name.
 *
 */
public class MethodRefrencesTest1 {

	public static void main(String[] args) {
		
		final List<Person> persons = ModelFactory.getPersons();
		
		//Using Lambda Expressions
		showFilteredPerson(persons, 
				p -> p.getAge() > 30 && p.getAge() < 60, 		//Predicate
				p -> p.getName(), 								//Function      (Lambda expression only calling an existing method)
				str -> System.out.println(str));				//Consumer		(Lambda expression only calling an existing method)
		
//====================================================================================================================//
		System.out.println("-------------------------------------");
		
		//Using Method References (Easy to use Lambda Expressions that already have a name 
		
		showFilteredPerson(persons, 
	
				MethodRefrencesTest1 :: filterPersonBasedOnAge,  //Reference to a static method
	
				Person :: getName,         //Reference to an instance method of an arbitrary object of a particular type. 
				
				System.out :: println);   //Reference to an instance method of a particular object.
	}
	
	private static void showFilteredPerson(List<Person> persons, Predicate<Person> personFilter, 
			Function<Person, String> personMapper, Consumer<String> personAction) {
		
		for(Person person : persons) {
			if(personFilter.test(person)) {
				String str = personMapper.apply(person);
				personAction.accept(str);
			}
		}
	}
	
	private static boolean filterPersonBasedOnAge(Person p) {
		return p.getAge() > 30 && p.getAge() < 60;
	}
	
}
