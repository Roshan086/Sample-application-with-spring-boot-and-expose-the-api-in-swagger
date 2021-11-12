package com.sample.servicesImpl;

import com.sample.beans.EmployeeBean;
import com.sample.repository.EmployeeRepository;
import com.sample.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeBean saveEmployee(EmployeeBean employeeBean) {
        return employeeRepository.save(employeeBean);
    }

    @Override
    public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
        if (employeeBean.getEmployeeId() != null) {
            Optional<EmployeeBean> persisted = employeeRepository.findById(employeeBean.getEmployeeId());
            if (persisted == null) {
                return null;
            }
            EmployeeBean updated = employeeRepository.save(employeeBean);
            return updated;
        }
        return null;
    }

    @Override
    public EmployeeBean deleteEmployee(EmployeeBean employeeBean) {
        if (employeeBean.getEmployeeId() != null) {
            employeeRepository.delete(employeeBean);
            return employeeBean;
        }
        return null;
    }

    @Override
    public List<EmployeeBean> listOfEmployees() {
        List<EmployeeBean> employeeBeans = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeBean -> employeeBeans.add(employeeBean));
        return employeeBeans;
    }

    @Override
    public EmployeeBean findById(Long id) {
        Optional<EmployeeBean> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<EmployeeBean> findAllByFilterWithPaging(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
