package filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamTest {

	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static void main(String[] args) {
		
		String path1 = "D:\\JAVA_RELATED\\Test_Files\\original";
		String path2 = "D:\\JAVA_RELATED\\Test_Files\\copy";
		
		fileCopy(path1, path2);
		
		System.out.println("File copied successfully");
	}
	
	private static void fileCopy(String source, String destination) {
		
		File srcFolder = new File(source);
		File destFolder = new File(destination);
		
		if(!srcFolder.exists()) {
			System.out.println("Source File not available");
			return;
		}
		
		if(!destFolder.exists()) {
			if(destFolder.mkdirs()) {
				System.out.println(destination + " : Folder created successfully");
			}
		}
		
		
		if(srcFolder.isDirectory()) {
			
			for (String fileName : srcFolder.list()) {
				System.out.println("FileName : " + fileName);
				copy(new File(source + FILE_SEPARATOR + fileName), new File(destination + FILE_SEPARATOR + fileName));
			}	
		}else {
			System.out.println("Source is not a directory");
		}
	}
	
	private static void copy(File srcFile, File destFile){
		
		System.out.println("Copying File : ");
		System.out.println("Source File : " + srcFile.getAbsolutePath());
		System.out.println("Destination File : " + destFile.getAbsolutePath());
		
		byte[] buffer = new byte[256];
		
		try (FileInputStream fis = new FileInputStream(srcFile); 
				FileOutputStream fos = new FileOutputStream(destFile)) {
			
			while(fis.read(buffer) != -1) {
				fos.write(buffer);
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}








