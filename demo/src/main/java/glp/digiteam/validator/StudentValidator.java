package glp.digiteam.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import glp.digiteam.entity.student.Student;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student) target;

		if (!validate(student.getEmail())) {
			errors.rejectValue("email", "");
		}
		if (student.getPhone().length() < 10 && !isNumeric(student.getPhone())) {
			errors.rejectValue("phone", "");
		}

		if (student.getAddress().getCity().equals("")) {
			errors.rejectValue("address.city", "");
		}
		if (student.getAddress().getStreet().equals("")) {
			errors.rejectValue("address.street", "");
		}

		if (student.getAddress().getPostalCode().length() != 5 && !isNumeric(student.getAddress().getPostalCode())) {
			errors.rejectValue("address.postalCode", "");
		}

		/*if (student.getAvailability().getStartDate().after(student.getAvailability().getEndDate())) {
			errors.rejectValue("availability.startDate", "");
			errors.rejectValue("availability.endDate", "");
		}*/
		
		if (student.getWish().getMissions().size()==0) {
			errors.rejectValue("wish.missions", "");
		}
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}
