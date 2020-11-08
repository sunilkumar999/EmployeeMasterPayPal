package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping("/v1/bfs/employees/{id}")
    ResponseEntity<String> employeeGetById(@PathVariable("id") int id);

    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------
    
    
    @RequestMapping("/v1/bfs/createEmployee/{firstName}/{lastName}/{dateOfBirth}/{line1}/{city}/{state}/{country}/{zipcode}/{line2}")
    ResponseEntity<String> addEmployee(@PathVariable("firstName")  String firstName,@PathVariable("lastName")  String lastName,@PathVariable("dateOfBirth")  String dateOfBirth,@PathVariable("line1")  String line1,@PathVariable("city")  String city,@PathVariable("state")  String state,@PathVariable("country")  String country,
    		@PathVariable("zipcode")  String zipcode,@PathVariable(value = "line2", required = false)  Optional<String> line2
    		);
}
