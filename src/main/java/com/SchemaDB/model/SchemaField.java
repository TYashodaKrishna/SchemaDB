package com.SchemaDB.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchemaField {
    private String dataType;
    private String multiValue;
    private String autoSuggest;
    private String fieldName;
}
