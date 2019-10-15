package org.javascool;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
	
	ByteArrayOutputStream baos;
	PrintStream ps;
	PrintStream old;
	
	ByteArrayOutputStream baosErr;
	PrintStream psErr;
	PrintStream oldErr;
	
	String ERROR_MSG = "Output message error.";
	String FILE_PATH = "vscode-workspace" + File.separator + "hello-world";
	
    @Before
    public void beforeSetup() {
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        old = System.out;
        System.setOut(ps);
        
        baosErr = new ByteArrayOutputStream();
        psErr = new PrintStream(baosErr);
        oldErr = System.err;
        System.setErr(psErr);

    }
    
    @After
    public void afterSetup() {
        File file = new File(FILE_PATH + ".jar");
        
        if(file.delete()) { 
            System.out.println("File deleted successfully"); 
        } 
        else { 
            System.out.println("Failed to delete the file"); 
        } 
    }
 

	@Test
	public void testMainCompilationSuccess() {
		Main.main(new String[] {"compile",  FILE_PATH + ".jvs"});
		System.out.flush();
		System.setOut(old);
		String output = baos.toString();
		assertThat(output, equalTo("Compilation Completed Successfully"));
	}
	
	@Test
	public void testMainCompilationFailOnFileName() {
		Main.main(new String[] {"compile",  FILE_PATH + ".toto"});
		System.err.flush();
		System.setErr(oldErr);
		String output = baosErr.toString();
		assertThat(output, equalTo("The file should have .jvs extension\n"));
	}
}
