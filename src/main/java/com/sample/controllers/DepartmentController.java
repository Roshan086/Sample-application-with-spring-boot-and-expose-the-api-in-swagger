package com.sample.controllers;

import com.sample.beans.DepartmentBean;
import com.sample.dto.DepartmentDTO;
import com.sample.dto.PageDTO;
import com.sample.dto.StatusDTO;
import com.sample.services.DepartmentService;
import com.sample.transformer.DepartmentTransformer;
import com.sample.transformer.EmployeeTransformer;
import com.sample.utill.PaginationUtil;
import com.sample.utill.Utility;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/department/")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    //creating Post mapping that get the list of departments from the database
    @PostMapping("getAllDepartments")
    public PageDTO getAll(@RequestBody PaginationUtil paginationUtil){
        Map<String, String> params=new HashMap<>();
        System.out.println(paginationUtil.toString());
        params.put("page",paginationUtil.getCurrentPage().toString());
        params.put("itemsPerPage",paginationUtil.getItemsPerPages().toString());
        params.put("sortBy",paginationUtil.getSortBy());
        params.put("direction",paginationUtil.getDirection());
        Page<DepartmentBean> page = departmentService.findAllByFilterWithPaging(Utility.createPageRequest(params));
        return new PageDTO(DepartmentTransformer.listOfDepartmentDTOFromBean(page.getContent()), page.getTotalElements(), page.getTotalPages());
    }

    //creating post mapping that save the department detail in the database
    @PostMapping("/saveDepartment")
    private ResponseEntity<StatusDTO> saveDepartment(@ModelAttribute DepartmentDTO departmentDTO)
    {
        try{
            DepartmentBean departmentBean= DepartmentTransformer.getDepartmentBeanFromDTO(departmentDTO);
            DepartmentBean department=departmentService.saveDepartment(departmentBean);
            return new ResponseEntity<>(new StatusDTO(1, "Department Added Successfully ", DepartmentTransformer.getDepartmentDTOFromBean(department)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }

    //creating post mapping that update the department in the database
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Department Id ",readOnly = true, dataType = "string",paramType = "query",required = true),
    })
    @PostMapping("/updateDepartment")
    private ResponseEntity<StatusDTO> updateDepartment(@ModelAttribute DepartmentDTO departmentDTO)
    {
        try{

            DepartmentBean departmentBean= departmentService.findById(Long.parseLong(departmentDTO.getId()));
            if(departmentBean==null){
                return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);
            }
            DepartmentBean department=DepartmentTransformer.getDepartmentBeanFromDTO(departmentDTO);
            DepartmentBean updateDepartment=departmentService.updateDepartment(department);
            return new ResponseEntity<>(new StatusDTO(1, "Department Update Successfully ", DepartmentTransformer.getDepartmentDTOFromBean(updateDepartment)), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }

    //creating get mapping that delete the department from the database
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {

        DepartmentBean departmentBean=departmentService.findById(id);
        try {
            if (departmentBean == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);

            } else {
                DepartmentBean department=departmentService.deleteDepartment(departmentBean);
                return new ResponseEntity<>(new StatusDTO(1, "Department Delete Successfully ", DepartmentTransformer.getDepartmentDTOFromBean(department)), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);

        }
    }

    //creating get mapping that get the department from the database
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id) {
        DepartmentBean departmentBean;
        DepartmentDTO departmentDTO = null;
        try {
            departmentBean = departmentService.findById(id);
            if(departmentBean!=null){
                departmentDTO=DepartmentTransformer.getDepartmentDTOFromBean(departmentBean);
                return new ResponseEntity<>(new StatusDTO(1, "Department Found Successfully ", departmentDTO), HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);
            }
        }  catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
}
