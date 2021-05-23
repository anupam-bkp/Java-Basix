package stream_test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class CollectorsTest1 {

	public static void main(String[] args) {
		
		//Find name of each person working in HR dept.
		List<Employee> employees = ModelFactory.getEmployees();
		
		collectInList(employees);
		collectInSet(employees);
		collectInMap(employees);
		collectInMap1(employees);
		collectInMap2(employees);
		collectInCollection(employees);
	}
	
	/*Collecting name of each employee working in HR dept into a List.
		Collectors.toList()
	*/
	private static void collectInList(List<Employee> employees) {
		System.out.println("================Collected in List ==================");
		final List<String> collect = employees.stream()
				.filter(emp -> emp.getDept().getName().equals("HR"))
				.map(emp -> emp.getPerson().getName())
				.collect(Collectors.toList());

		System.out.println(collect);
	}
	
	/*Collecting name of each employee working in HR dept into a List.
		Collectors.toSet()
	*/
	private static void collectInSet(List<Employee> employees) {
		System.out.println("================Collected in Set ==================");
		final Set<String> collect = employees.stream()
				.filter(emp -> emp.getDept().getName().equals("HR"))
				.map(emp -> emp.getPerson().getName())
				.collect(Collectors.toSet());
				
		System.out.println(collect);
	}
	
	/*Collecting Name of Employee and Department into a map
		Collectors.toMap(Function, Function)
	*/
	private static void collectInMap(List<Employee> employees) {
		System.out.println("===========Collected in Map ==========");
		
		final Map<String, String> collect = employees.stream().collect(
				Collectors.toMap(
						
						new Function<Employee, String>() {         //KeyMapper

							@Override
							public String apply(Employee t) {
								return t.getPerson().getName();
							}
						}, 
						
						new Function<Employee, String>() {

							@Override
							public String apply(Employee t) {
								return t.getDept().getName();
							}
						}
				)
			);
		
		System.out.println(collect);
		
		System.out.println("              ------------Lambda-------------       ");
		
		final Map<String, String> collect2 = employees.stream().collect(
				Collectors.toMap(
						
						emp -> emp.getPerson().getName(), //keyMapper 
						emp -> emp.getDept().getName()   //ValueMapper
						
						)
				);
		
		System.out.println(collect2);
	}
	
	
	/* Collecting Name of Employee and Department into a map
		
		Collectors.toMap(Function, Function, BinaryOperator)
	*/
	private static void collectInMap1(List<Employee> employees) {
		System.out.println("===========Collected in Map With Merge Function==========");
	
		final Map<String, String> collect = employees.stream().collect(
				Collectors.toMap(
						
						emp -> emp.getDept().getName(),  //KeyMapper
						emp -> emp.getPerson().getName(),  //KeyMapper
						
						new BinaryOperator<String>() {

							@Override
							public String apply(String t, String u) {
								System.out.println(t + " === " + u);
								return t+","+u;
							}
							
						}
					)
				);
		
		System.out.println(collect);
		
		System.out.println("       --------- Lambda -----------         ");
		
		final Map<String, String> collect1 = employees.stream().collect(
				Collectors.toMap(
						
						emp -> emp.getDept().getName(),  //KeyMapper
						emp -> emp.getPerson().getName(),  //KeyMapper
						(name1, name2) -> name1 + "," + name2   //MergeFunction
					)
			);
		
		System.out.println(collect1);
		
		
		System.out.println("     ---------Method Refrences -----------  ");
		
		
		final Map<String, String> collect2 = employees.stream().collect(
				Collectors.toMap(
						emp -> emp.getDept().getName(),  //KeyMapper
						emp -> emp.getPerson().getName(),  //KeyMapper
						String :: concat			      //MergeFunction
					)
			);
		
		System.out.println(collect2);
		
	}
	
	/*Collecting Name of Employee and Department into a map
	
		Collectors.toMap(Function, Function, BinaryOperator, Supplier)
	*/
	private static void collectInMap2(List<Employee> employees) {
		System.out.println("===========Collected in Map With Merge Function and Map Supplier==========");
	
		TreeMap<String,String> collect = employees.stream().collect(
				Collectors.toMap(
						
						emp -> emp.getDept().getName(),  //KeyMapper
						emp -> emp.getPerson().getName(),  //KeyMapper
						(name1, name2) -> name1 + "," + name2, //MergeFunction
						TreeMap :: new
					)
				);
			
			System.out.println(collect);
			
	}
	
	/*
	 	public static <T,C extends Collection<T>> Collector<T,?,C> toCollection(Supplier<C> collectionFactory)
	 */
	private static void collectInCollection(List<Employee> employees) {

		//Using Anonymous Inner Class
		List<String> collect = employees.stream().map(new Function<Employee, String>() {

			@Override
			public String apply(Employee t) {
				return t.getEmployeeName();
			}
		})
		.collect(Collectors.toCollection(new Supplier<List<String>>() {

			@Override
			public List<String> get() {
				return new ArrayList<>();
			}
		}));
		
		System.out.println(collect);
		
		//Using Lambda Expression
		List<String> collect2 = employees.stream().map(emp -> emp.getEmployeeName())
		.collect(Collectors.toCollection(() -> new LinkedList<String>()));
		
		System.out.println(collect2);

		//Using Method References
		Set<String> collect3 = employees.stream().map(Employee :: getEmployeeName)
		.collect(Collectors.toCollection(HashSet :: new));
		System.out.println(collect3);
		
	}
	
}
