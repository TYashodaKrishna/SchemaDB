const frisby = require("frisby");

var path=require('path');
var fs=require('fs');
var schemaDB1_domain = "http://localhost:8080/api";
var schemaDB2_domain = "http://localhost:8081/api";

describe("monitor", function() {
    it("Monitor SchemaDB1 service", function(done){
        frisby.get(schemaDB1_domain+'/monitor')
            .expect('status', 200)
            .expect('bodyContains', 'Working fine.')
            .done(done);
    });

    it("Monitor SchemaDB2 service", function(done){
            frisby.get(schemaDB2_domain+'/monitor')
                .expect('status', 200)
                .expect('bodyContains', 'Working fine.')
                .done(done);
        });
});

describe("Get Schema without data in DB", function() {
    it("should return 404, schema does not exist", function(done) {
        frisby.get(schemaDB1_domain+'/siteKey/get')
            .expect('status', 404)
            .expect('bodyContains', 'Schema not found for siteKey: siteKey')
            .done(done);
    });

    it("should return 404, schema does not exist", function(done) {
            frisby.get(schemaDB2_domain+'/siteKey/get')
                .expect('status', 404)
                .expect('bodyContains', 'Schema not found for siteKey: siteKey')
                .done(done);
        });
});

describe("Update Schema without data in DB", function() {
    it("should return 404, schema does not exist", function(done) {
        frisby.put(schemaDB1_domain+'/siteKey/update', {
                                                         "siteKey": "test-site",
                                                         "schema": [
                                                           {
                                                             "dataType": "longText",
                                                             "multiValue": "false",
                                                             "autoSuggest": "false",
                                                             "fieldName": "attribute_set_id"
                                                           }
                                                         ]
                                                       })
            .expect('status', 404)
            .expect('bodyContains', 'Schema does not exist for the siteKey: siteKey to update')
            .done(done);
    });

    it("should return 404, schema does not exist", function(done) {
        frisby.put(schemaDB2_domain+'/siteKey/update', {
                                                         "siteKey": "test-site",
                                                         "schema": [
                                                           {
                                                             "dataType": "longText",
                                                             "multiValue": "false",
                                                             "autoSuggest": "false",
                                                             "fieldName": "attribute_set_id"
                                                           }
                                                         ]
                                                       })
            .expect('status', 404)
            .expect('bodyContains', 'Schema does not exist for the siteKey: siteKey to update')
            .done(done);
    });
});

describe("Delete Schema without data in DB", function() {
    it("should return 404, schema does not exist", function(done) {
        frisby.delete(schemaDB1_domain+'/siteKey/delete')
            .expect('status', 404)
            .expect('bodyContains', 'Schema not found for siteKey: siteKey')
            .done(done);
    });

    it("should return 404, schema does not exist", function(done) {
        frisby.delete(schemaDB2_domain+'/siteKey/delete')
            .expect('status', 404)
            .expect('bodyContains', 'Schema not found for siteKey: siteKey')
            .done(done);
    });
});

describe("Upload Schema to DB", function() {
    it("should store schema in redis", function(done) {
        frisby.post(schemaDB1_domain+'/siteKey/upload', {
                                                          "siteKey": "test-site",
                                                          "schema": [
                                                            {
                                                              "dataType": "longText",
                                                              "multiValue": "false",
                                                              "autoSuggest": "false",
                                                              "fieldName": "attribute_set_id"
                                                            }
                                                          ]
                                                        })
            .expect('status', 200)
            .expect('bodyContains', 'Successfully stored the schema')
            .done(done);
    });

    it("should store schema in redis", function(done) {
            frisby.post(schemaDB2_domain+'/siteKey/upload', {
                                                              "siteKey": "test-site",
                                                              "schema": [
                                                                {
                                                                  "dataType": "longText",
                                                                  "multiValue": "false",
                                                                  "autoSuggest": "false",
                                                                  "fieldName": "attribute_set_id"
                                                                }
                                                              ]
                                                            })
                .expect('status', 200)
                .expect('bodyContains', 'Successfully stored the schema')
                .done(done);
        });
});

describe("Get schema when data in DB", function() {
    it("Get Schema stored in redis", function(done) {
        frisby.get(schemaDB1_domain+'/siteKey/get')
            .expect('status', 200)
            .expect('bodyContains', '{"siteKey":"test-site","schema":[{"dataType":"longText","multiValue":"false","autoSuggest":"false","fieldName":"attribute_set_id"}]}')
            .done(done);
    });

    it("Get Schema stored in mongo", function(done) {
        frisby.get(schemaDB2_domain+'/siteKey/get')
            .expect('status', 200)
            .expect('bodyContains', '{"siteKey":"test-site","schema":[{"dataType":"longText","multiValue":"false","autoSuggest":"false","fieldName":"attribute_set_id"}]}')
            .done(done);
    });
});

describe("Update Schema when data in DB", function() {
    it("Update Schema in Redis", function(done) {
        frisby.put(schemaDB1_domain+'/siteKey/update', {
                                                        "siteKey": "test-site",
                                                        "schema": [
                                                          {
                                                            "dataType": "Text",
                                                            "multiValue": "false",
                                                            "autoSuggest": "false",
                                                            "fieldName": "title"
                                                          }
                                                        ]
                                                      })
        .expect('status', 200)
        .expect('bodyContains', 'Successfully updated the schema for siteKey: siteKey')
        .done(done);
    });

    it("Update Schema in Mongo", function(done) {
        frisby.put(schemaDB2_domain+'/siteKey/update', {
                                                        "siteKey": "test-site",
                                                        "schema": [
                                                          {
                                                            "dataType": "Text",
                                                            "multiValue": "false",
                                                            "autoSuggest": "false",
                                                            "fieldName": "title"
                                                          }
                                                        ]
                                                      })
        .expect('status', 200)
        .expect('bodyContains', 'Successfully updated the schema for siteKey: siteKey')
        .done(done);
    });
});

describe("Get schema when data in DB", function() {
    it("Get Schema stored in redis", function(done) {
        frisby.get(schemaDB1_domain+'/siteKey/get')
            .expect('status', 200)
            .expect('bodyContains', '{"siteKey":"test-site","schema":[{"dataType":"Text","multiValue":"false","autoSuggest":"false","fieldName":"title"}]}')
            .done(done);
    });

    it("Get Schema stored in mongo", function(done) {
        frisby.get(schemaDB2_domain+'/siteKey/get')
            .expect('status', 200)
            .expect('bodyContains', '{"siteKey":"test-site","schema":[{"dataType":"Text","multiValue":"false","autoSuggest":"false","fieldName":"title"}]}')
            .done(done);
    });
});

describe("Delete Schema when data in DB", function() {
    it("Delete Schema in Redis", function(done) {
        frisby.delete(schemaDB1_domain+'/siteKey/delete')
            .expect('status', 200)
            .expect('bodyContains', 'Deleted schema for siteKey: siteKey')
            .done(done);
    });

    it("Delete Schema in Redis", function(done) {
        frisby.delete(schemaDB2_domain+'/siteKey/delete')
            .expect('status', 200)
            .expect('bodyContains', 'Deleted schema for siteKey: siteKey')
            .done(done);
    });
});