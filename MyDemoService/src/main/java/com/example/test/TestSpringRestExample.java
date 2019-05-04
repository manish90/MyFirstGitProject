package com.example.test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.imageio.spi.ServiceRegistry;


import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.demo.EmpRestURIConstants;
//import com.journaldev.spring.controller.EmpRestURIConstants;
import com.journaldev.spring.model.Employee;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import okio.BufferedSink;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:8080/";
	
	public static void main(String args[]) throws IOException{
		
		//testGetDummyEmployee();
		System.out.println("*****");
		//testCreateEmployee();
		System.out.println("*****");
		//testGetEmployee();
		System.out.println("*****");
		//testGetAllEmployee();
		getTestService();
	}

	private static void testGetAllEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Employee> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+EmpRestURIConstants.GET_ALL_EMP, List.class);
		System.out.println(emps.size());
		for(LinkedHashMap map : emps){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = new Employee();
		emp.setId(3);emp.setName("Rahul Kumar");
		Employee response = restTemplate.postForObject(SERVER_URI+EmpRestURIConstants.CREATE_EMP, emp, Employee.class);
		printEmpData(response);
	}

	private static void testGetEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", Employee.class);
		printEmpData(emp);
	}

	private static void testGetDummyEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.getForObject(SERVER_URI+EmpRestURIConstants.DUMMY_EMP, Employee.class);
		printEmpData(emp);
	}
	
	public static void getTestService() throws IOException {
		RestTemplate template = new RestTemplate();
		//template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		//template.getMessageConverters().add(new StringHttpMessageConverter());
		//Employee emp = new Employee();
		String str1 ="{\"name\" : \"manish\"}";
		/*emp.setName("Manish");
		String str = template.postForObject(SERVER_URI+EmpRestURIConstants.TEST_SERVICE, str1, String.class);
		ResponseEntity<?> response = template.postForEntity(SERVER_URI+EmpRestURIConstants.TEST_SERVICE, str1, String.class);
		
		System.out.println(str);*/
		
	/*	MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	    RequestBody body = RequestBody.create(JSON, str1);
		
		OkHttpClient client = new OkHttpClient();
		//RequestBody body = RequestBody.create("application/json", str1);
				
		Request request = new Request.Builder()
		  .url("http://localhost:8080/test/MyService")
		  .post(body).build();

		Response response1 = client.newCall(request).execute();
		
		System.out.println(response1.body().string());
		
		*/
		Client client1 = Client.create();
		WebResource web = client1.resource("http://localhost:8080/test/MyService");
		ClientResponse res = web.type("application/json").post(ClientResponse.class, str1);
		String output = res.getEntity(String.class);
		System.out.println(output);
		
	}
	
	public static void printEmpData(Employee emp){
		System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",CreatedDate="+emp.getCreatedDate());
	}
}
