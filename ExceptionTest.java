package MyClass;

import java.io.*;
import java.util.*;

public class ExceptionTest {

	public static void main(String[] args) {
		

		
		try {
			ExceptionTest.method();
			System.out.println("success");
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}

	static void method() throws Throwable {
		File f = new File("D:/text.txt");
		
		new FileInputStream(f);
	}
}
