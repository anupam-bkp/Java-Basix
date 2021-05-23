package stream_test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

/**
 * Test of Mutable Reduction operation using collect(supplier, accumulator, combiner).
 */
public class MutableReductionTest {

	/*	<R> R collect(Supplier<R> supplier,
            BiConsumer<R,? super T> accumulator,
            BiConsumer<R,R> combiner)*/

	public static void main(String[] args) {

		//Find name of each person working in HR dept.
		List<Employee> employees = ModelFactory.getEmployees();
//================================================================
		
		usingMethodReferences(employees);	
		usingLambdaExpressions(employees);
		usingAnonymousInnerClass(employees);
	}//End of Main

	//Using Method References
	private static void usingMethodReferences(List<Employee> employees) {
		System.out.println("=============== Using Method Refrences ==================================");

		List<Object> collect2 = employees.stream()

				.filter(emp -> emp.getDept().getName().equals("HR"))
				.map(emp -> emp.getPerson().getName())

				.collect(ArrayList :: new, ArrayList :: add, ArrayList :: addAll);

		System.out.println(collect2);
	}

	//Using Lambda Expressions
	private static void usingLambdaExpressions(List<Employee> employees) {
		System.out.println("=========== Using Lambda Expressions ========================================");

		List<Object> collect = employees.stream()

				.filter(emp -> emp.getDept().getName().equals("HR"))
				.map(emp -> emp.getPerson().getName())

				.collect(
						() -> new ArrayList<>(), 

						(result, input) -> result.add(input), 

						(result, result1) -> result.addAll(result1));

		System.out.println(collect);
	}

	//Using anonymous Inner class.
	private static void usingAnonymousInnerClass(List<Employee> employees) {
		System.out.println("======= Using anonymous Inner class ============");

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

				.collect(
						new Supplier<List<String>>() {
							@Override
							public List<String> get() {
								return new ArrayList<>();
							}				
						}, 

						new BiConsumer<List<String>, String>() {

							@Override
							public void accept(List<String> t, String u) {
								t.add(u);
							}
						},

						new BiConsumer<List<String>, List<String>>() {

							@Override
							public void accept(List<String> t, List<String> u) {
								t.addAll(u);
							}
						}
					);


		System.out.println(names);
	}

}
