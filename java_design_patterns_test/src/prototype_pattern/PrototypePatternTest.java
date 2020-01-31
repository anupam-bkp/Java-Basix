package prototype_pattern;

import java.util.List;

public class PrototypePatternTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Employees emp = new Employees();
		emp.loadData();
		
		Employees emp1 = (Employees)emp.clone();
		List<String> empList = emp1.getEmpList();
		empList.add("MyName");
		
		System.out.println(empList);
	}
	
}
