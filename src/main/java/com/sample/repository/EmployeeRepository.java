package com.sample.repository;


import com.sample.beans.EmployeeBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository  extends JpaRepository<EmployeeBean, Long>, JpaSpecificationExecutor<EmployeeBean> {
    Page<EmployeeBean> findAll(Pageable pageable);
}
