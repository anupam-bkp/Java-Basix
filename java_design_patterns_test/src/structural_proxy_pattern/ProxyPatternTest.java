package structural_proxy_pattern;
public class ProxyPatternTest {

	public static void main(String[] args){
		
		CommandExecutor executor = new CommandExecutorProxy("User", "pwd");
		try {
			executor.runCommand("ls -ltr");
			executor.runCommand(" rm -rf abc.pdf");
		} catch (Exception e) {
			System.out.println("Exception Message::"+e.getMessage());
		}
		
	}

}