package com.sample.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

public class EmployeeDTO {

    @ApiModelProperty(value="Id",notes = "The database generated ID",hidden = true)
    private String employeeId;
    @ApiModelProperty(value = "First Name", notes = "First Name can not be Null", required = true)
    private String firstName;
    @ApiModelProperty(value = "Last Name", notes = "Last Name can not be Null", required = true)
    private String lastName;
    private String email;
    private String phoneNumber;
    private String salary;
    private String managerId;
    private DepartmentDTO departmentDTO;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }
}
