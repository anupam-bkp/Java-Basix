package filereader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import filehandlingutils.FileHandlingUtil;

public class ByteFileReader {

	private static final String FILE_PATH = FileHandlingUtil.getFilePathToRead();
	
	public static void main(String[] args) {
		readUsingFileInputStream();
	}
	
	private static void readUsingFileInputStream() {
		
		try(FileInputStream fis = new FileInputStream(FILE_PATH)) {
			
			/*int i;
			while((i = fis.read()) != -1) {
				System.out.print((char)i);
			}*/
//			System.out.println(Character.BYTES);
			
			byte[] b = new byte[10];
			while((fis.read(b)) != -1) {
				System.out.println(Arrays.toString(b));
//				System.out.println(Stream.of(b).count());

				
				
			}
				
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private static void readUsingBufferedInputStream() {
		
	}
	
}
