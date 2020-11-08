package com.repo;

import org.springframework.data.repository.CrudRepository;

import com.entity.EmployeeInfo;

public interface EmplRepository extends CrudRepository<EmployeeInfo, Integer>   {

}
