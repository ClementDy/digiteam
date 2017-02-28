package glp.digiteam.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import glp.digiteam.entity.student.Student;

public class SaveProfileValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student) target;

		// form_infos
		if (!validateEmailAddress(student.getEmail())) {
			errors.rejectValue("email", "field.invalid");
		}

		if (!validatePhoneNumber(student.getPhone())) {
			errors.rejectValue("phone", "field.invalid");
		}
		
		if (!validatePostalCode(student.getAddress().getPostalCode())) {
			errors.rejectValue("address.postalCode", "field.invalid");
		}
		
		// form_contracts

		// form_dispos
	}

	public String getFirstErrorTab(Errors errors) {

		// form_infos
		if (errors.hasFieldErrors("email") ||
			errors.hasFieldErrors("phone") ||
			errors.hasFieldErrors("address.postalCode")
		) {
			return "infos";
		}

		// form_contracts
		for (FieldError fe : errors.getFieldErrors()) {
			if (fe.getField().startsWith("externalContracts")) {
				return "contracts";
			}
		}
		
		// form_dispos

		// default
		return "intro";
	}
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private static boolean validateEmailAddress(String emailAddress) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress);
		return matcher.find();
	}

	private static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile(
			"^[+]?\\d*$",
			Pattern.CASE_INSENSITIVE);
	
	private static boolean validatePhoneNumber(String phoneNumber) {
		Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
		return matcher.find();
	}
	
	private static final Pattern VALID_POSTAL_CODE_REGEX = Pattern.compile(
			"^\\d{5}$",
			Pattern.CASE_INSENSITIVE);
	
	private static boolean validatePostalCode(String postalCode) {
		Matcher matcher = VALID_POSTAL_CODE_REGEX.matcher(postalCode);
		return matcher.find();
	}
}