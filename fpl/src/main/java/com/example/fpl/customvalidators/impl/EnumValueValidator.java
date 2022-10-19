package com.example.fpl.customvalidators.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.fpl.customvalidators.ValueOfEnum;

public class EnumValueValidator implements ConstraintValidator<ValueOfEnum, CharSequence>  {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(e -> e.toString().toLowerCase())
                .collect(Collectors.toList());
    }
	
	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		}
		String enteredPosition = value.toString().toLowerCase();
		for(String position : acceptedValues) {
			if(enteredPosition.contains(position)) {
				return true;
			}
		}
		return false;
	}
}
