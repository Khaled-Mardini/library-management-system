package com.maids.libms.main.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(
            WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes =
                super.getErrorAttributes(webRequest, options);
        List<Object> errors = new ArrayList<>();
        errors.add(errorAttributes.get("error"));

        errorAttributes.remove("timestamp");
        errorAttributes.remove("status");
        errorAttributes.remove("path");

        errorAttributes.put("data", null);
        errorAttributes.put("errors", errors);

        return errorAttributes;
    }
}
