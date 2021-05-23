package stream_test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import java8_feature_model.Employee;
import java8_feature_model.ModelFactory;

public class CollectorsTest4 {
	
	public static void main(String[] args) {
		
		List<Employee> employees = ModelFactory.getEmployees();
		countingTest(employees);
		maxByTest(employees);
		minByTest(employees);
		summarizingIntTest(employees);
		summingIntTest(employees);
		averagingIntTest(employees);
		
	}
	
	/*
 		public static <T> Collector<T,?,Long> counting()
	 */
	private static void countingTest(List<Employee> employees) {
	
		Long collect = employees.stream().collect(Collectors.counting());
		System.out.println(collect);
	}
	
	/*
	 * public static <T> Collector<T,?,Optional<T>> maxBy(Comparator<? super T> comparator)
	 */
	private static void maxByTest(List<Employee> employees) {
		//Using Anonymous Inner class
		Optional<Employee> collect = employees.stream()
			.collect(Collectors.maxBy(
					new Comparator<Employee>() {
						@Override
						public int compare(Employee o1, Employee o2) {
							return o1.getSalary() > o2.getSalary() ? 1 : -1;
						}
					}
				)
			);
		
		if(collect.isPresent()) {
			System.out.println(collect.get().getId());
		}else {
			System.out.println("No Employee Present");
		}
		
		//Using Lambda Expressions
		
		Comparator<Employee> empComparator = (emp1, emp2) -> emp1.getSalary() > emp2.getSalary() ? 1 : -1;
		
		Optional<Employee> collect2 = employees.stream()
		.collect(Collectors.maxBy(empComparator));
		
		if(collect.isPresent()) {
			System.out.println(collect2.get().getId());
		}else {
			System.out.println("No Employee Present");
		}
	}
	
	private static void minByTest(List<Employee> employees) {
		
		Comparator<Employee> empComparatorMin = (emp1, emp2) -> emp2.getSalary() < emp1.getSalary() ? 1 : -1;
		
		Optional<Employee> collect = employees.stream()
			.collect(Collectors.minBy(empComparatorMin));
		
		if(collect.isPresent()) {
			System.out.println("Min Wage : " + collect.get().getId());
		}else {
			System.out.println("No Employee Present");
		}	
	}
	
	/*
	 public static <T> Collector<T,?,IntSummaryStatistics> summarizingInt(ToIntFunction<? super T> mapper)
	 */
	private static void summarizingIntTest(List<Employee> employees) {
		
		//Using Anonymous Inner class
		IntSummaryStatistics collect = employees.stream()
			.collect(Collectors.summarizingInt(
					new ToIntFunction<Employee>() {

						@Override
						public int applyAsInt(Employee employee) {
							return employee.getSalary();
						}
					}
				)
			);
		
		System.out.println(collect.getCount() + " : " + collect.getSum() + " : " + collect.getMin() + " : " + 
				collect.getMax() + " : " + collect.getAverage());
		
		//Using Lambda Expression
		IntSummaryStatistics collect2 = employees.stream().collect(Collectors.summarizingInt(emp -> emp.getSalary()));
		System.out.println(collect2.getCount() + " : " + collect2.getSum() + " : " + collect2.getMin() + " : " + 
				collect2.getMax() + " : " + collect2.getAverage());
		
		//Using Method References
		IntSummaryStatistics collect3 = employees.stream().collect(Collectors.summarizingInt(Employee :: getSalary));
		System.out.println(collect3.getCount() + " : " + collect3.getSum() + " : " + collect3.getMin() + " : " + 
				collect3.getMax() + " : " + collect3.getAverage());
	}
	
	/*
	 public static <T> Collector<T,?,Integer> summingInt(ToIntFunction<? super T> mapper)
	 */
	private static void summingIntTest(List<Employee> employees) {

		//Using Anonymous Inner Class
		Integer collect = employees.stream()
			.collect(Collectors.summingInt(
					new ToIntFunction<Employee>() {

						@Override
						public int applyAsInt(Employee emp) {
							return emp.getSalary();
						}
					}
				)
			);
		
		System.out.println(collect);
		
		//Using Method References
		Integer collect2 = employees.stream().collect(Collectors.summingInt(Employee :: getSalary));
		System.out.println(collect2);
	}
	
	/*
	 public static <T> Collector<T,?,Double> averagingInt(ToIntFunction<? super T> mapper)
	 */
	private static void averagingIntTest(List<Employee> employees) {
		
		Double collect = employees.stream()
			.collect(Collectors.averagingInt(
					new ToIntFunction<Employee>() {

						@Override
						public int applyAsInt(Employee emp) {
							return emp.getSalary();
						}
					}
				)
			);
		
		System.out.println(collect);
		
		//Using Method References
		Double collect2 = employees.stream().collect(Collectors.averagingInt(Employee :: getSalary));
		System.out.println(collect2);
	}
	
	
}
