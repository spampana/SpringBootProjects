package com.howtodoinjava.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;
import com.howtodoinjava.demo.web.EmployeeController;

@RunWith(SpringRunner.class) // or @RunWith(SpringJUnit4ClassRunner.class) 
@WebMvcTest(EmployeeController.class)
public class TestEmployeeRESTController  {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EmployeeService service;
	
	private static List<EmployeeEntity> employees;       
     
	@BeforeClass
	 public static void setUp() {
		employees = new ArrayList<>();
		employees.add(new EmployeeEntity(1L, "satya1", "pampana1", "user1@gmail.com"));
		employees.add(new EmployeeEntity(2L, "satya2", "pampana2", "user2@gmail.com"));
		employees.add(new EmployeeEntity(3L, "satya3", "pampana3", "user3@gmail.com"));

	}
	 
	@Test
	public void getAllEmployeesAPI() throws Exception 
	{
	 Mockito.when(service.getAllEmployees()).thenReturn(employees);
	  mvc.perform( MockMvcRequestBuilders
	      .get("/employees")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.size()",is(employees.size())));
	      
	}
	 
	@Test
	public void getEmployeeByIdAPI() throws Exception 
	{
	  Mockito.when(service.getEmployeeById(1L)).thenReturn(employees.get(0));
	  mvc.perform( MockMvcRequestBuilders
	      .get("/employees/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("satya1"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("pampana1"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("user1@gmail.com"));
	}
	
	@Test
	public void getEmployeeByIdAPINotFound() throws Exception 
	{
	  Mockito.when(service.getEmployeeById(1L)).thenReturn(employees.get(0));
	  mvc.perform( MockMvcRequestBuilders
	      .get("/employees/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isNotFound());

	}
	
	
	
	
}
