package glp.digiteam.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import glp.digiteam.entity.student.Student;

public class PublishProfileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student) target;

		// form_infos
		if (StringUtils.isEmpty(student.getEmail())) {
			errors.rejectValue("email", "field.required");
		}
		else if (!validateEmailAddress(student.getEmail())) {
			errors.rejectValue("email", "field.invalid");
		}

		if (StringUtils.isEmpty(student.getPhone())) {
			errors.rejectValue("phone", "field.required");
		}
		else if (!validatePhoneNumber(student.getPhone())) {
			errors.rejectValue("phone", "field.invalid");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "address.city", "field.required");
		
		ValidationUtils.rejectIfEmpty(errors, "address.street", "field.required");

		if (StringUtils.isEmpty(student.getAddress().getPostalCode())) {
			errors.rejectValue("address.postalCode", "field.required");
		}
		else if (!validatePostalCode(student.getAddress().getPostalCode())) {
			errors.rejectValue("address.postalCode", "field.invalid");
		}

		// form_contracts
		
		// form_souhait
		if (student.getWish().getMissions().isEmpty()) {
			errors.rejectValue("wish.missions", "field.required");
		}
		
		if (StringUtils.isEmpty(student.getMotivation())) {
			errors.rejectValue("motivation", "field.required");
		}
		else if (student.getMotivation().length() < 450) {
			errors.rejectValue("motivation", "field.invalid");
		}
		
		// form_dispos

		// form_divers
	}

	public String getFirstErrorTab(Errors errors) {

		// form_infos
		if (errors.hasFieldErrors("email") ||
			errors.hasFieldErrors("phone") ||
			errors.hasFieldErrors("address.city") ||
			errors.hasFieldErrors("address.street") ||
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
		
		// form_souhait
		if (errors.hasFieldErrors("wish.missions") ||
			errors.hasFieldErrors("motivation")
		) {
			return "souhait";
		}
		
		// form_dispos

		// form_divers
		
		// default
		return "intro";
	}
	
	public List<String> getErrorTabs(Errors errors) {
		
		List<String> errorTabs = new ArrayList<String>();

		// form_infos
		if (errors.hasFieldErrors("email") ||
			errors.hasFieldErrors("phone") ||
			errors.hasFieldErrors("address.city") ||
			errors.hasFieldErrors("address.street") ||
			errors.hasFieldErrors("address.postalCode")
		) {
			errorTabs.add("infos");
		}

		// form_contracts
		for (FieldError fe : errors.getFieldErrors()) {
			if (fe.getField().startsWith("externalContracts")) {
				errorTabs.add("contracts");
			}
		}
		
		// form_souhait
		if (errors.hasFieldErrors("wish.missions") ||
			errors.hasFieldErrors("motivation")
		) {
			errorTabs.add("souhait");
		}
		
		// form_dispos

		// form_divers
		
		return errorTabs;
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
