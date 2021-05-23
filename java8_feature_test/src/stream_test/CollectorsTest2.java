package stream_test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class CollectorsTest2 {
	
	public static void main(String[] args) {
		
		//Find name of each person working in HR dept.
		List<Employee> employees = ModelFactory.getEmployees();
		System.out.println("===========Joining====================================");
		joiningTest(employees);
		System.out.println("===========GroupingBy=================================");
		groupingByTest1(employees);
		groupingByTest2(employees);
		groupingByTest3(employees);
		System.out.println("==============Mapping==================================");
		mappingTest(employees);
		
	}
	
	//public static <T,K> Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier)
	private static void groupingByTest1(List<Employee> employees) {
		
		//Using Anonymous Inner Class
		Map<String, List<Employee>> collect = employees.stream()
			.collect(Collectors.groupingBy(new Function<Employee, String>() {
				@Override
				public String apply(Employee t) {
					return t.getDept().getName();
				}
			}));
		
		System.out.println(collect);
		
		//Using Lambda Expression
		Map<String, List<Employee>> collect2 = employees.stream()
			.collect(Collectors.groupingBy(emp -> emp.getEmployeeDeptName()));
		
		System.out.println(collect2);
		
		//Using Method References
		Map<String, List<Employee>> collect3 = employees.stream()
			.collect(Collectors.groupingBy(Employee :: getEmployeeDeptName));
		
		System.out.println(collect3);
	}
	
	//public static <T,K,A,D> Collector<T,?,Map<K,D>> groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream)
	private static void groupingByTest2(List<Employee> employees) {

		//Using Anonymous Inner Class
		Map<String, List<String>> collect = employees.stream()
				.collect(Collectors.groupingBy(

						new Function<Employee, String>() {
							@Override
							public String apply(Employee t) {
								return t.getDept().getName();
							}
						},

						Collectors.mapping(

								new Function<Employee, String>() {
									@Override
									public String apply(Employee t) {
										return t.getPerson().getName();
									}
								},

								Collectors.toList())

						)
					);

		System.out.println(collect);

		//Using Lambda Expressions
		Map<String, List<String>> collect1 = employees.stream()
				.collect(Collectors.groupingBy(
						
						emp -> emp.getDept().getName(),     //Classifier
						
						Collectors.mapping(
								emp -> emp.getPerson().getName(), //Mapper 
								Collectors.toList()         //Downstream
							)         
						));
		
		System.out.println(collect1);

		//Using Method References
		Map<String, List<String>> collect2 = employees.stream()
				.collect(Collectors.groupingBy(
						Employee :: getEmployeeDeptName, 			//classifier
						Collectors.mapping(                      //Downstream
								Employee :: getEmployeeName,    //Mapper
								Collectors.toList())));   //downstream

		System.out.println(collect2);
	}
	
	/*	public static <T,K,D,A,M extends Map<K,D>> Collector<T,?,M> groupingBy(Function<? super T,? extends K> classifier,
    	Supplier<M> mapFactory,	Collector<? super T,A,D> downstream) 
    */
	private static void groupingByTest3(List<Employee> employees) {
	
		//Using Anonymous Inner Class
		Map<String, List<String>> collect = employees.stream().collect(Collectors.groupingBy(
	
				new Function<Employee, String>() {

					@Override
					public String apply(Employee t) {
						return t.getDept().getName();
					}
				}, 
				
				
				new Supplier<Map<String, List<String>>>() {

					@Override
					public Map<String, List<String>> get() {
						return new TreeMap<>();
					}
				}, 
				
				Collectors.mapping(
						
						new Function<Employee, String>() {

							@Override
							public String apply(Employee t) {
								return t.getPerson().getName();
							}
						}, 
						
						Collectors.toList())));
		
		System.out.println(collect);
	
		//Using Lambda Expressions
		final TreeMap<String,List<String>> collect2 = employees.stream()
			.collect(Collectors.groupingBy(
					
					emp -> emp.getDept().getName(), 
		
					() -> new TreeMap<String, List<String>>(), 
					
					Collectors.mapping(
							emp -> emp.getPerson().getName(), 
							Collectors.toList())));
		
		System.out.println(collect2);
		
		//Using Anonymous Inner Class
		Map<String,List<String>> collect3 = employees.stream()
			.collect(
					Collectors.groupingBy(
							
							Employee :: getEmployeeDeptName,        //Classifier
							
							TreeMap :: new, 					//MapFactory
							
							Collectors.mapping(							//Downstream
									Employee :: getEmployeeName, 		//Mapper
									Collectors.toList())));				//Downstream
		
		System.out.println(collect3);
		
	}
	
	
	private static void joiningTest(List<Employee> employees) {

		//public static Collector<CharSequence,?,String> joining()
		String collect1 = employees.stream()
				.filter(emp -> "HR".equals(emp.getDept().getName()))
				.map(emp -> emp.getPerson().getName())
				.collect(Collectors.joining());

		System.out.println(collect1);

		//public static Collector<CharSequence,?,String> joining(CharSequence delimiter)
		String collect2 = employees.stream()
				.filter(emp -> "HR".equals(emp.getDept().getName()))
				.map(emp -> emp.getPerson().getName())
				.collect(Collectors.joining("##"));

		System.out.println(collect2);
		
		String collect = employees.stream()
				.filter(emp -> "HR".equals(emp.getEmployeeDeptName()))
				.map(Employee :: getEmployeeName)
				.collect(Collectors.joining("**"));

		System.out.println(collect);

		//public static Collector<CharSequence,?,String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
		String collect3 = employees.stream()
				.filter(emp -> "HR".equals(emp.getDept().getName()))
				.map(emp -> emp.getPerson().getName())
				.collect(Collectors.joining("##", "StartOfName", "EndOfName"));

		System.out.println(collect3);
	}
	
	//public static <T,U,A,R> Collector<T,?,R> mapping(Function<? super T,? extends U> mapper, Collector<? super U,A,R> downstream)
	private static void mappingTest(List<Employee> employees) {
		//Without Collectors.mapping()
		List<String> collect2 = employees.stream()
			.map(Employee :: getEmployeeName).collect(Collectors.toList());
		System.out.println(collect2);
		
		//Anonymous Inner Class
		Set<String> collect = employees.stream()
			.collect(Collectors.mapping(
					
					new Function<Employee, String>() {

						@Override
						public String apply(Employee t) {
							return t.getPerson().getName();
						}
					}, 
					
					Collectors.toSet()));
		
		System.out.println(collect);
		
		
		//Lambda Expression
		List<String> collect3 = employees.stream()
			.collect(Collectors.mapping(emp -> emp.getEmployeeName(), Collectors.toList()));
		
		System.out.println(collect3);
		
		//Method References
		List<String> collect4 = employees.stream()
				.collect(Collectors.mapping(Employee :: getEmployeeName, Collectors.toList()));
		System.out.println(collect4);
		
	}
	
}
