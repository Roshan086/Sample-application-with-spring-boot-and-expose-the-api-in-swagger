package com.sample.dto;

import io.swagger.annotations.ApiModelProperty;

public class DepartmentDTO {
    @ApiModelProperty(value="Id",notes = "The database generated ID",hidden = true)
    private String id;
    private String departmentName;
    private String managerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
