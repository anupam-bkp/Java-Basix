package lambda_test;

import java.util.List;

import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

/**
 * Start of Lambda Expressions
 * Lambda Expression with User-Defined Functional Interface.
 *
 */
public class LambdaTest1 {

	public static void main(String[] args) {
		
		final List<Person> persons = ModelFactory.getPersons();

//=====================================================================================================================//
		//Passing function code in a local class
		showFilteredPerson(persons, new FilterPersonOnAge());
		showFilteredPerson(persons, new FilterPersonOnGender());
		
//=====================================================================================================================//
		//Passing function code in a anonymous inner class
		showFilteredPerson(persons, new PersonFilter() {
			@Override
			public boolean filter(Person p) {
				return p.getAge() > 30 && p.getAge() < 60;
			}
		});
		
//======================================================================================================================//		
		System.out.println("====================================");
		
		//Passing function code in a Lambda Expression
		showFilteredPerson(persons, p -> (p.getAge() > 30 && p.getAge() < 60));
		
	} //Main Ends Here
	
	private static void showFilteredPerson(final List<Person> persons, PersonFilter personFilter) {
		for(Person person : persons) {
			if(personFilter.filter(person)) {
				System.out.println(person);
			}
		}
	}
	
} //LambdaTest1 class Ends Here

//Functional Interface
interface PersonFilter{
	boolean filter(Person p);
}

class FilterPersonOnAge implements PersonFilter{

	@Override
	public boolean filter(Person p) {
		return p.getAge() > 30 && p.getAge() < 60;
	}
}

class FilterPersonOnGender implements PersonFilter{

	@Override
	public boolean filter(Person p) {
		return "MALE".equals(p.getGender());
	}
}


