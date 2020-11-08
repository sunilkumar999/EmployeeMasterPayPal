package com.tests;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.entity.AddressInfo;
import com.entity.EmployeeInfo;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import com.repo.EmplRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={EmployeeResourceImplTests.class})

@EnableJpaRepositories(basePackages = {"com.repo"})
@EntityScan("com.entity")
public class EmployeeResourceImplTests {
	
	@Autowired  
	EmplRepository empRepository;  
	
	
	
	//Negative test case
	@Test
	public void getEmployeeNegativeCase() {
		
		
		Optional <EmployeeInfo> optional = empRepository.findById(12);
		EmployeeInfo data  = optional.get();
		
		 assertEquals("rithish", data.getFirstName());
	}
	
	//Positive case
	
	@Test
	public void getEmployee() {
		
		
		Optional <EmployeeInfo> optional = empRepository.findById(0);
		EmployeeInfo data  = optional.get();
		
		 assertEquals("rithish", data.getFirstName());
	}
	@Test
	public void createEmployee() {
		EmployeeInfo employee = new EmployeeInfo();
    	employee.setFirstName("rithish");
    	employee.setLastName("abinav");
    	employee.setDateOfBirth("09/01/1991");
    	AddressInfo address = new AddressInfo();
    	address.setLine1("no 165 nedumaran street");
    	
    	address.setCity("chennai");
    	
    	address.setState("tamilnadu");
    	address.setCountry("india");
    	address.setZip_code("600087");
    	employee.setAddress(address);
    	empRepository.save(employee);
		
	}
	

}
