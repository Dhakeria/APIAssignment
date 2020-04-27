package com.apiassignment.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class BaseClass {
    Properties properties;

    public BaseClass() throws IOException {
        FileInputStream configFis =  new FileInputStream(new File(System.getProperty("user.dir")+"/src/main//resources/Config.properties"));
        properties = new Properties();
        properties.load(configFis);
    }

    public Response getRequest(String url){
        RequestSpecification request = RestAssured.given();
        return request.get(url);
    }

    public Response getRequest(String url, Map<String,?> headers){
        RequestSpecification request = RestAssured.given().headers(headers);
        return request.get(url);
    }

    public Response postRequest(String body, Map<String,String> headers,String Url){
        RequestSpecification request = RestAssured.given().body(body).headers(headers);
        return request.post(Url);
    }

    public Response postRequest(String body, String Url){
        RequestSpecification request = RestAssured.given().body(body);
        Response response = request.post(Url);
        return response;
    }

    public Response putRequest(String body, String Url){
        RequestSpecification request = RestAssured.given().body(body);
        Response response = request.put(Url);
        return response;
    }
    public String getEnvironmentConfig() throws IOException {
        String environment = properties.getProperty("environment");
        FileInputStream envFis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/"+environment+".properties"));
        properties.load(envFis);
        return  properties.getProperty("BaseUrl");
    }

}
