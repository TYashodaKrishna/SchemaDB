package com.SchemaDB.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchemaModel {
    private String siteKey;
    private List<SchemaField> schema;
}
