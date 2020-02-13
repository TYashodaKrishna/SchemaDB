package com.SchemaDB.controller;

import com.SchemaDB.exceptions.SchemaNotFoundException;
import com.SchemaDB.exceptions.StoreException;
import com.SchemaDB.model.SchemaModel;
import com.SchemaDB.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class SchemaController {

    @Autowired
    private SchemaService schemaService;

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public ResponseEntity monitor() {
        return ResponseEntity.status(HttpStatus.OK).body("Working fine.");
    }

    @RequestMapping(value = "/{siteKey}/upload", method = RequestMethod.POST)
    public ResponseEntity upload(@PathVariable("siteKey") String siteKey, @RequestBody SchemaModel siteSchema) {
        try {
            schemaService.storeSchema(siteKey, siteSchema);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully stored the schema");
        } catch (StoreException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{siteKey}/get", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("siteKey") String siteKey) {
        try {
            SchemaModel schema = schemaService.getSchema(siteKey);
            return ResponseEntity.ok().body(schema);
        } catch (SchemaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{siteKey}/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("siteKey") String siteKey) {
        try {
            schemaService.deleteSchema(siteKey);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted schema for siteKey: " + siteKey);
        } catch (SchemaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{siteKey}/update", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("siteKey") String siteKey, @RequestBody SchemaModel siteSchema) {
        try {
            schemaService.updateSchema(siteKey, siteSchema);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated the schema for siteKey: " + siteKey);
        } catch (SchemaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (StoreException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
