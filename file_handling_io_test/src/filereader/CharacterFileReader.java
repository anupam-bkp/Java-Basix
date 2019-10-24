package filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import filehandlingutils.FileHandlingUtil;

public class CharacterFileReader {

private static final String FILE_PATH = FileHandlingUtil.getFilePathToRead();
	
	public static void main(String[] args) {
		System.out.println(FILE_PATH);
		
		readUsingFileReader();
		readUsingBufferedReader();
		readusingBufferedReaderJava7Way();
		readUsingInputStreamReader();
	}
	
	/**
	 * FileReader
	 */
	private static void readUsingFileReader() {
		
		try (FileReader fr = new FileReader(FILE_PATH)){
			
			/*int i;
			while((i = fr.read()) != -1)
				System.out.print((char)i);*/
			
			char[] cbuf = new char[10];
			int numOfChar = fr.read(cbuf);
			while(numOfChar != -1) {
				for(int i=0; i<numOfChar; i++) {
					System.out.print(cbuf[i]);
				}
				numOfChar = fr.read(cbuf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * BufferedReader
	 */
	private static void readUsingBufferedReader() {
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(new File(FILE_PATH)));
			String str = null;
			
			while((str = br.readLine()) != null) {
				System.out.println(str);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * Using Java7 feature to close the reader. 
	 */
	private static void readusingBufferedReaderJava7Way() {

		try(BufferedReader br = new BufferedReader(new FileReader(new File(FILE_PATH)))){
			String str = null;
			while((str = br.readLine()) != null) {
				System.out.println(str);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Using Generic InputStreamReader
	 */
	private static void readUsingInputStreamReader() {
	
		try (BufferedReader br 
				= new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH)))){
			String str = null;
			while((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
