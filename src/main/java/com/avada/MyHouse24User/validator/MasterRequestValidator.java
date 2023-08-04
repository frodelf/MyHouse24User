package com.avada.MyHouse24User.validator;

import com.avada.MyHouse24User.model.MasterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class MasterRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MasterRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "field.required", "Поле не може бути пустим");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flat", "field.required", "Поле не може бути пустим");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "Поле не може бути пустим");

        MasterRequestDTO dto = (MasterRequestDTO) target;

        if (dto.getDate() == null) {
            errors.rejectValue("date", "field.required", "Поле не може бути пустим");
        } else {
            Date currentDate = new Date();
            if (dto.getDate().before(currentDate)) {
                errors.rejectValue("date", "field.invalid", "Дата не може бути минулою");
            }
        }

        if (dto.getDate() == null) {
            errors.rejectValue("time", "field.required", "Поле не може бути пустим");
        }
    }
}
