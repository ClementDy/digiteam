package glp.digiteam.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import glp.digiteam.entity.student.Student;

public class StudentValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
	      return Student.class.equals(clazz);
	    }

	    @Override
	    public void validate(Object target, Errors errors) {
	    	Student student = (Student) target;

	      if(student.getEmail().equals("")) {
	          errors.rejectValue("email", "");
	      }
	      if(student.getPhone().length() < 10 && !isNumeric(student.getPhone())) {
	          errors.rejectValue("phone", "");
	      }
	      
	      if(student.getAddress().getCity().equals("")) {
	          errors.rejectValue("address.city", "");
	      }
	      if(student.getAddress().getStreet().equals("")) {
	          errors.rejectValue("address.street", "");
	      }
	      
	      if(student.getAddress().getPostalCode().length()!=6 && !isNumeric(student.getAddress().getPostalCode())) {
	          errors.rejectValue("address.postalCode", "");
	      }
	      
	      if(student.getAddress().getComplement().equals("")) {
	          errors.rejectValue("address.complement", "");
	      }
	      
	      if(student.getAvailability().getStartDate().after(student.getAvailability().getEndDate())){
	          errors.rejectValue("availability.startDate", "");
	          errors.rejectValue("availability.endDate", "");
	      }
	   

	    }
	    
	    public boolean isNumeric(String s) {  
	        return s.matches("[-+]?\\d*\\.?\\d+");  
	    }  
	    

}
