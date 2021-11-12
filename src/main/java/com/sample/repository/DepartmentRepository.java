package com.sample.repository;

import com.sample.beans.DepartmentBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import org.springframework.data.domain.Pageable;

public interface DepartmentRepository extends JpaRepository<DepartmentBean, Long>, JpaSpecificationExecutor<DepartmentBean> {
    Page<DepartmentBean> findAll(Pageable pageable);
}
