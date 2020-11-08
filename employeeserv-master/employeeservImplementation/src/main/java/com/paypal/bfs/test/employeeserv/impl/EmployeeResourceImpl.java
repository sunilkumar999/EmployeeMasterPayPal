package com.paypal.bfs.test.employeeserv.impl;

import com.entity.AddressInfo;
import com.entity.EmployeeInfo;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.repo.EmplRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation class for employee resource.
 */
@RestController
@Validated
public class EmployeeResourceImpl implements EmployeeResource {

	public String message ="";
	@Autowired  
	EmplRepository empRepository;  
	
    @Override
    public ResponseEntity<String> employeeGetById(int id) {

       
    		
    	 Optional <EmployeeInfo> optional = empRepository.findById(id);
    	 if(!optional.isPresent())
    	 {
    		 return new ResponseEntity<>("Id is wrong check details", HttpStatus.NOT_FOUND);
    	 }
    	 EmployeeInfo data  = optional.get();
    	 Employee emp = new Employee();
    	 emp.setFirstName(data.getFirstName());
    	 emp.setLastName(data.getLastName());
    	 emp.setDateofbirth(data.getDateOfBirth());
    	 message = emp.toString();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
   
   

	@Override
	public ResponseEntity<String> addEmployee(String firstName, String lastName, String dateOfBirth, String line1
			, String city, String state, String country,String zipCode , Optional<String> line2) {
	

    	EmployeeInfo employee = new EmployeeInfo();
    	employee.setFirstName(firstName);
    	employee.setLastName(lastName);
    	employee.setDateOfBirth(dateOfBirth);
    	AddressInfo address = new AddressInfo();
    	address.setLine1(line1);
    	if(line2.isPresent())
    	{
    	address.setLine2(line2.get());
    	}
    	address.setCity(city);
    	
    	address.setState(state);
    	address.setCountry(country);
    	address.setZip_code(zipCode);
    	employee.setAddress(address);
    	
    	if(validateParams(employee)) {}
    	
    	else {
    	message="Employee saved successfully";
    	empRepository.save(employee);
    	}
    		
    	
    	
    	return new ResponseEntity<>(message, HttpStatus.OK);
		
		
		
		
	}
	
	public boolean validateParams(EmployeeInfo info)
	{
		if(!info.getFirstName().matches("[A-Z][a-z]*"))
		
		{
			message="firstname should not be empty and First letter should be in caps";
			return true;
			
		}
		if(!info.getLastName().matches("[A-Z][a-z]*"))
			
		{
			message="firstname should not be empty and First letter should be in caps";
			return true;
			
		}
		String dateRegex = "^(1[0-2]|0[1-9])-(3[01]"
                + "|[12][0-9]|0[1-9])-[0-9]{4}$"; 
		Pattern pattern = Pattern.compile(dateRegex); 
		Matcher matcher = pattern.matcher((CharSequence)info.getDateOfBirth()); 
		if(!matcher.matches())
		{
			message="Date format is not correct please enter as MM-DD-YYYY";
			return true;
		}
		
		
		String addressReg = "^[#.0-9a-zA-Z\\s,-]+$";
		if(!info.getAddress().getLine1().matches(addressReg))
		{
			message="Address should be in proper format eg #1, North Street, Chennai - 11   ";
			return true;
			
		}
		String zipCoderegex = "\\d{5}(-\\d{4})?";
		if(!info.getAddress().getZip_code().matches(zipCoderegex))
		{
			
			message="Zipcode should be in proper format like this 83592-1537   ";
			return true;
		}
		String cityCountryRegex = "([a-zA-Z]+|[a-zA-Z]+\\\\s[a-zA-Z]+)";
		if(!info.getAddress().getCity().matches(cityCountryRegex))
		{
			message="City should be in proper format   ";
			return true;
			
		}
		
		if(!info.getAddress().getCountry().matches(cityCountryRegex))
		{
			message="country should be in proper format   ";
			return true;
			
		}
		
		return false;
	}
	
	
	
	
	
	
}
