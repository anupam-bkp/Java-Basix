package java8_feature_model;

/**
 *Model representing a Person
 */
public class Person {

	String name;
	
	int age;
	
	String gender;

	public Person(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
}

enum GENDER{
		MALE, FEMALE;
}
