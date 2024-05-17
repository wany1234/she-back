package com.she.security.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DateFormatConverter implements AttributeConverter<DateFormat, String> {

    public String convertToDatabaseColumn(DateFormat value) {
        if (value == null) {
            return null;
        }

        return value.getDateFormat();
    }

    public DateFormat convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return DateFormat.fromCode(value);
    }
}
