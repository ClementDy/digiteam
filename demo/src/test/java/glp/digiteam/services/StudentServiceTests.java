package glp.digiteam.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import glp.digiteam.entity.Student;
import glp.digiteam.services.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {

	@Autowired
	private StudentService studentService;
	
	@Test
	public void testSaveStudentProfile() {
		Student student = new Student();
		student.setFirstName("test");
		student.setLastName("TEST");
		
		long nip = studentService.saveStudentProfile(student).getNip();
		
		student = studentService.getStudentByNip(nip);
		
		Assert.assertNotNull(student);
		Assert.assertEquals("test", student.getFirstName());
		Assert.assertEquals("TEST", student.getLastName());
	}
}
