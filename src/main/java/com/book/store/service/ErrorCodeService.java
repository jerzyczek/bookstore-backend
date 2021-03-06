package com.book.store.service;

import com.book.store.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
public class ErrorCodeService {

    private static final String ERROR_CODES_BUNDLE_NAME = "error-codes";

    public static String getTranslatedCode(ErrorCode code) {
        ResourceBundle bundle = ResourceBundle.getBundle(ERROR_CODES_BUNDLE_NAME);
        String translated = null;
        try {
            translated = bundle.getString(code.getCode());
        } catch (MissingResourceException e) {
            log.warn("Missing translation for error {}", code);
            translated = bundle.getString(ErrorCode.MISSING_ERROR_CODE.name());
        }

        return translated;
    }
}
