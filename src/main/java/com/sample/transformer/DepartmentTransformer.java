package com.sample.transformer;

import com.sample.beans.DepartmentBean;
import com.sample.dto.DepartmentDTO;

import java.util.ArrayList;
import java.util.List;

public class DepartmentTransformer {

    public static DepartmentBean getDepartmentBeanFromDTO(DepartmentDTO departmentDTO){
        DepartmentBean departmentBean=new DepartmentBean();
        if(departmentDTO.getId()!=null && !departmentDTO.getId().equals("")){
            departmentBean.setDepartmentId(Long.parseLong(departmentDTO.getId()));
        }
        if(departmentDTO.getDepartmentName()!=null && !departmentDTO.getDepartmentName().equals("")){
            departmentBean.setDepartmentName(departmentDTO.getDepartmentName());
        }
        if(departmentDTO.getManagerId()!=null && !departmentDTO.getManagerId().equals("")){
            departmentBean.setManagerId(Integer.parseInt(departmentDTO.getManagerId()));
        }

        return departmentBean;
    }

    public static DepartmentDTO getDepartmentDTOFromBean(DepartmentBean departmentBean){
        DepartmentDTO departmentDTO=new DepartmentDTO();
        if(departmentBean.getDepartmentId()!=null && !departmentBean.getDepartmentId().equals("")){
            departmentDTO.setId(departmentBean.getDepartmentId().toString());
        }
        if(departmentBean.getDepartmentName()!=null && !departmentBean.getDepartmentName().equals("")){
            departmentDTO.setDepartmentName(departmentBean.getDepartmentName());
        }
        if(departmentBean.getManagerId()!=null && !departmentBean.getManagerId().equals("")){
            departmentDTO.setManagerId(departmentBean.getManagerId().toString());
        }

        return departmentDTO;
    }

    public static List<DepartmentDTO> listOfDepartmentDTOFromBean(List<DepartmentBean> departmentBeans){
        List<DepartmentDTO> departmentDTOS=new ArrayList<>();
        for(DepartmentBean departmentBean:departmentBeans){
            DepartmentDTO departmentDTO=DepartmentTransformer.getDepartmentDTOFromBean(departmentBean);
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }

}
