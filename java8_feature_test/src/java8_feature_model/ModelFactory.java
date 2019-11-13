package java8_feature_model;

import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
	
	private static final GENDER[] GENDERS = new GENDER[] {GENDER.MALE, GENDER.MALE, GENDER.FEMALE, GENDER.FEMALE, GENDER.MALE};
	private static final String[] NAMES = new String[] {"abc", "yz", "stu", "def", "vwx"};
	private static final int[] AGES = new int[] {10, 50, 30, 20, 40};
	
	public static List<Person> getPersons(){
		
		List<Person> persons = new ArrayList<>();
		
		for(int i=0; i<NAMES.length; i++) {
			persons.add(new Person(NAMES[i], AGES[i], GENDERS[i].name()));
		}
		
		return persons;
	}	
	
}