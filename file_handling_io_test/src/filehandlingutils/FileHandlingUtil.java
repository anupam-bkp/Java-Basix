package filehandlingutils;

public class FileHandlingUtil {
	
	final static String FILE_PATH_SEPARATOR;
	final static String USER_DIR;
	final static String FILE_PATH;

	static {
		FILE_PATH_SEPARATOR = System.getProperty("file.separator");
		USER_DIR = System.getProperty("user.dir");
		FILE_PATH = USER_DIR + FILE_PATH_SEPARATOR + "resources" + FILE_PATH_SEPARATOR + "test.txt";
	}
	
	public static String getFilePathToRead() {
		return FILE_PATH;
	}

}
