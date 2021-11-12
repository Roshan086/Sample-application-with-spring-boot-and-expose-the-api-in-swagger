package com.sample.servicesImpl;

import com.sample.beans.DepartmentBean;
import com.sample.repository.DepartmentRepository;
import com.sample.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentBean saveDepartment(DepartmentBean departmentBean) {
        return departmentRepository.save(departmentBean);
    }

    @Override
    public DepartmentBean updateDepartment(DepartmentBean departmentBean) {
        if (departmentBean.getDepartmentId() != null) {
            Optional<DepartmentBean> persisted = departmentRepository.findById(departmentBean.getDepartmentId());
            if (persisted == null) {
                return null;
            }
            DepartmentBean updated = departmentRepository.save(departmentBean);
            return updated;
        }
        return null;
    }

    @Override
    public DepartmentBean deleteDepartment(DepartmentBean departmentBean) {
        if (departmentBean.getDepartmentId() != null) {
            departmentRepository.delete(departmentBean);
            return departmentBean;
        }
        return null;
    }

    @Override
    public List<DepartmentBean> listOfDepartments() {
        List<DepartmentBean> departmentBeans = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentBean -> departmentBeans.add(departmentBean));
        return departmentBeans;
    }

    @Override
    public DepartmentBean findById(Long id) {
        Optional<DepartmentBean> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<DepartmentBean> findAllByFilterWithPaging(Pageable pageable) {
       return departmentRepository.findAll(pageable);
    }
}
