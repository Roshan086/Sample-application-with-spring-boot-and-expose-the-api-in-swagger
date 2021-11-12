package com.sample.transformer;

import com.sample.beans.EmployeeBean;
import com.sample.dto.DepartmentDTO;
import com.sample.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTransformer {
    public static EmployeeBean getBean(EmployeeDTO employeeDTO){
        EmployeeBean employeeBean=new EmployeeBean();
        if(employeeDTO.getEmployeeId()!=null && !employeeDTO.getEmployeeId().equals("")){
            employeeBean.setEmployeeId(Long.parseLong(employeeDTO.getEmployeeId()));
        }
        if(employeeDTO.getFirstName()!=null && !employeeDTO.getFirstName().equals("")){
            employeeBean.setFirstName(employeeDTO.getFirstName());
        }
        if(employeeDTO.getLastName()!=null && !employeeDTO.getLastName().equals("")){
            employeeBean.setLastName(employeeDTO.getLastName());
        }
        if(employeeDTO.getEmail()!=null && !employeeDTO.getEmail().equals("")){
            employeeBean.setEmail(employeeDTO.getEmail());
        }
        if(employeeDTO.getManagerId()!=null && !employeeDTO.getManagerId().equals("")){
            employeeBean.setManagerId(Integer.parseInt(employeeDTO.getManagerId()));
        }
        if(employeeDTO.getPhoneNumber()!=null && !employeeDTO.getPhoneNumber().equals("")){
            employeeBean.setPhoneNumber(employeeDTO.getPhoneNumber());
        }
        if(employeeDTO.getSalary()!=null && !employeeDTO.getSalary().equals("")){
            employeeBean.setSalary(Integer.parseInt(employeeDTO.getSalary()));
        }
        if(employeeDTO.getDepartmentDTO()!=null){
            employeeBean.setDepartmentBean(DepartmentTransformer.getDepartmentBeanFromDTO(employeeDTO.getDepartmentDTO()));
        }
        return employeeBean;
    }

    public static EmployeeDTO getDTO(EmployeeBean employeeBean){
        EmployeeDTO employeeDTO=new EmployeeDTO();
        if(employeeBean.getEmployeeId()!=null && !employeeBean.getEmployeeId().equals("")){
            employeeDTO.setEmployeeId(employeeBean.getEmployeeId().toString());
        }
        if(employeeBean.getFirstName()!=null && !employeeBean.getFirstName().equals("")){
            employeeDTO.setFirstName(employeeBean.getFirstName());
        }
        if(employeeBean.getLastName()!=null && !employeeBean.getLastName().equals("")){
            employeeDTO.setLastName(employeeBean.getLastName());
        }
        if(employeeBean.getEmail()!=null && !employeeBean.getEmail().equals("")){
            employeeDTO.setEmail(employeeBean.getEmail());
        }
        if(employeeBean.getManagerId()!=null && !employeeBean.getManagerId().equals("")){
            employeeDTO.setManagerId(employeeBean.getManagerId().toString());
        }
        if(employeeBean.getPhoneNumber()!=null && !employeeBean.getPhoneNumber().equals("")){
            employeeDTO.setPhoneNumber(employeeBean.getPhoneNumber());
        }
        if(employeeBean.getSalary()!=null && !employeeBean.getSalary().equals("")){
            employeeDTO.setSalary(employeeBean.getSalary().toString());
        }
        if(employeeBean.getDepartmentBean()!=null){
            employeeDTO.setDepartmentDTO(DepartmentTransformer.getDepartmentDTOFromBean(employeeBean.getDepartmentBean()));
        }
        return employeeDTO;
    }

    public static List<EmployeeDTO> getListDTOS(List<EmployeeBean> employeeBeans){
        List<EmployeeDTO> employeeDTOS=new ArrayList<>();
        for(EmployeeBean employeeBean:employeeBeans){
            EmployeeDTO employeeDTO=EmployeeTransformer.getDTO(employeeBean);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
}
