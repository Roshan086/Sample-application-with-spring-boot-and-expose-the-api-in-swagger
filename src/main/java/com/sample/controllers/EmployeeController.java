package com.sample.controllers;


import com.sample.beans.DepartmentBean;
import com.sample.beans.EmployeeBean;
import com.sample.dto.DepartmentDTO;
import com.sample.dto.EmployeeDTO;
import com.sample.dto.PageDTO;
import com.sample.dto.StatusDTO;
import com.sample.services.DepartmentService;
import com.sample.services.EmployeeService;
import com.sample.transformer.DepartmentTransformer;
import com.sample.transformer.EmployeeTransformer;
import com.sample.utill.PaginationUtil;
import com.sample.utill.Utility;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/employee/")
public class EmployeeController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    //creating Post mapping that get the list of employees from the database
    @PostMapping("getAllEmployees")
    public PageDTO getAll(@ModelAttribute PaginationUtil paginationUtil){
        Map<String, String> params=new HashMap<>();
        params.put("page",paginationUtil.getCurrentPage().toString());
        params.put("itemsPerPage",paginationUtil.getItemsPerPages().toString());
        params.put("sortBy",paginationUtil.getSortBy());
        params.put("direction",paginationUtil.getDirection());
        Page<EmployeeBean> page = employeeService.findAllByFilterWithPaging(Utility.createPageRequest(params));
        return new PageDTO(EmployeeTransformer.getListDTOS(page.getContent()), page.getTotalElements(), page.getTotalPages());
    }

    //creating post mapping that save the employee detail in the database
    @ApiImplicitParams({
            @ApiImplicitParam(name = "departmentDTO.id", value = "Department Id ",readOnly = true, dataType = "string",paramType = "query",required = true),
    })
    @PostMapping("/saveEmployee")
    private ResponseEntity<StatusDTO> saveEmployee(@ModelAttribute EmployeeDTO employeeDTO)
    {
        try{
            if(employeeDTO.getFirstName().length()<=2){
                return new ResponseEntity<>(new StatusDTO(0, "First Name should be more than 2 character! "), HttpStatus.OK);
            }
            if(employeeDTO.getLastName().length()<=2){
                return new ResponseEntity<>(new StatusDTO(0, "Last Name should be more than 2 character! "), HttpStatus.OK);
            }
            if(employeeDTO.getSalary()!=null){
                if(Integer.parseInt(employeeDTO.getSalary())<=0){
                    return new ResponseEntity<>(new StatusDTO(0, "Salary must be greater than 0 "), HttpStatus.OK);
                }
            }
            if(employeeDTO.getDepartmentDTO().getId()!=null){
                DepartmentBean departmentBean=departmentService.findById(Long.parseLong(employeeDTO.getDepartmentDTO().getId()));
                if(departmentBean==null){
                    return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);
                }
            }
            if(employeeDTO.getPhoneNumber()!=null){
                Boolean bool=Utility.checkPhoneNumber(employeeDTO.getPhoneNumber());
                if(bool==false){
                    return new ResponseEntity<>(new StatusDTO(0, "Phone number contains only digit and dashes "), HttpStatus.OK);
                }
            }
            EmployeeBean employeeBean= EmployeeTransformer.getBean(employeeDTO);
            EmployeeBean employee=employeeService.saveEmployee(employeeBean);
            return new ResponseEntity<>(new StatusDTO(1, "Employee Added Successfully ",EmployeeTransformer.getDTO(employee)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }

    //creating post mapping that update the employee in the database
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "Employee Id ",readOnly = true, dataType = "string",paramType = "query",required = true),
            @ApiImplicitParam(name = "departmentDTO.id", value = "Department Id ",readOnly = true, dataType = "string",paramType = "query",required = true),
    })
    @PostMapping("/updateEmployee")
    private ResponseEntity<StatusDTO> updateEmployee(@ModelAttribute EmployeeDTO employeeDTO)
    {
        try{
            EmployeeBean employee=employeeService.findById(Long.parseLong(employeeDTO.getEmployeeId()));
            if(employee==null){
                return new ResponseEntity<>(new StatusDTO(0, "Employee Id Not Found! "), HttpStatus.OK);
            }
            if(employeeDTO.getFirstName().length()<=2){
                return new ResponseEntity<>(new StatusDTO(0, "First Name should be more than 2 character! "), HttpStatus.OK);
            }
            if(employeeDTO.getLastName().length()<=2){
                return new ResponseEntity<>(new StatusDTO(0, "Last Name should be more than 2 character! "), HttpStatus.OK);
            }
            if(employeeDTO.getSalary()!=null){
                if(Integer.parseInt(employeeDTO.getSalary())<=0){
                    return new ResponseEntity<>(new StatusDTO(0, "Salary must be greater than 0 "), HttpStatus.OK);
                }
            }
            if(employeeDTO.getDepartmentDTO().getId()!=null){
                DepartmentBean departmentBean=departmentService.findById(Long.parseLong(employeeDTO.getDepartmentDTO().getId()));
                if(departmentBean==null){
                    return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);
                }
            }
            if(employeeDTO.getPhoneNumber()!=null){
                Boolean bool=Utility.checkPhoneNumber(employeeDTO.getPhoneNumber());
                if(bool==false){
                    return new ResponseEntity<>(new StatusDTO(0, "Phone number contains only digit and dashes "), HttpStatus.OK);
                }
            }
            EmployeeBean employeeBean= EmployeeTransformer.getBean(employeeDTO);
            EmployeeBean updateEmployee=employeeService.updateEmployee(employeeBean);
            return new ResponseEntity<>(new StatusDTO(1, "Employee Update Successfully ", EmployeeTransformer.getDTO(updateEmployee)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }

    //creating get mapping that delete the department from the database
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> deleteEmployee(@PathVariable Long id) {

        EmployeeBean employeeBean=employeeService.findById(id);
        try {
            if (employeeBean == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Employee Id Not Found! "), HttpStatus.OK);
            } else {
                EmployeeBean employee=employeeService.deleteEmployee(employeeBean);
                return new ResponseEntity<>(new StatusDTO(1, "Employee Delete Successfully ", EmployeeTransformer.getDTO(employee)), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }

    //creating get mapping that get the department from the database
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<StatusDTO> findEmployeeById(@PathVariable Long id) {
        EmployeeBean employeeBean;
        EmployeeDTO employeeDTO = null;
        try {
            employeeBean = employeeService.findById(id);
            if(employeeBean!=null){
                employeeDTO=EmployeeTransformer.getDTO(employeeBean);
                return new ResponseEntity<>(new StatusDTO(1, "Employee Found", EmployeeTransformer.getDTO(employeeBean)), HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>(new StatusDTO(0, "Employee Id Not Found! "), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
}
