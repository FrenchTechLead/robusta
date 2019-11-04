package org.robusta;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robusta.Main;

public class MainTest {
	
	ByteArrayOutputStream baos;
	PrintStream ps;
	PrintStream old;
	
	String ERROR_MSG = "Output message error.";
	String FILE_PATH =
			"src" + File.separator +
			"test" +  File.separator +
			"resources" +  File.separator +
			"hello-world"
			;
	
    @Before
    public void beforeSetup() {
    	System.setProperty("java.class.path", "target/robusta.jar");
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        old = System.out;
        System.setOut(ps);

    }
    
    @After
    public void afterSetup() {
        File file = new File(FILE_PATH + ".jar");
        
        if(file.delete()) { 
            System.out.println("File deleted successfully"); 
        }
    }
 

	@Test
	public void testMainCompilationSuccess() {
		String JVS_FILE = FILE_PATH + ".jvs";
		Main.main(new String[] {"compile",  JVS_FILE});
		System.out.flush();
		System.setOut(old);
		String output = baos.toString();
		assertThat(output, containsString("Compilation Completed Successfully"));
	}
	
	
	@Test
	public void testMainCompilationFailOnFileName() {
		Main.main(new String[] {"compile",  FILE_PATH + ".toto"});
		System.out.flush();
		System.setOut(old);
		String output = baos.toString();
		assertThat(output, containsString("The file should have .jvs extension"));
	}
	
}
