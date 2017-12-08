package com.javamog.potapov.utils;

import com.javamog.potapov.model.FileBucket;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return FileBucket.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@Nullable Object object, Errors errors) {
        FileBucket file = (FileBucket) object;

        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }

    }
}
