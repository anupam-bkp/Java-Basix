package method_refrences_test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;
import java8_feature_model.Person;

public class MethodRefrencesTest2 {
	
	public static void main(String[] args) {
		
		//Show names of employees belong to HR dept
		
		List<Employee> employees = ModelFactory.getEmployees();
		
//==================================================================
		usingMethodReferences(employees);
		usingLambdaExpression(employees);
		usingAnonymousInnerClass(employees);
		
		
		
	}//End of Main

	//Using Lambda Expression
		private static void usingMethodReferences(List<Employee> employees) {
			System.out.println("================Using Method References ================");
			
			employees.stream()
			
			.filter(emp -> emp.getDept().getName().equals("HR"))
			
			.map(Employee :: getPerson)
			
			.map(Person :: getName)
			
			.forEach(System.out :: println);
			
		}
	
	//Using Lambda Expression
	private static void usingLambdaExpression(List<Employee> employees) {
		System.out.println("================Using Lambda Expressions ================");
		
		employees.stream()
		
		.filter(emp -> emp.getDept().getName().equals("HR"))
		
		.map(emp -> emp.getPerson().getName())
		
		.forEach(name -> System.out.println(name));
		
	}
	
	//Using Anonymous Inner Class
	private static void usingAnonymousInnerClass(List<Employee> employees) {
		System.out.println("================Using Anonymous Inner class================");
		
		employees.stream()
		
		.filter(new Predicate<Employee>() {

			@Override
			public boolean test(Employee t) {
				return t.getDept().getName().equals("HR");
			}
		})
		
		.map(new Function<Employee, String>() {

			@Override
			public String apply(Employee t) {
				return t.getPerson().getName();
			}
		})
		
		.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
	}

}
