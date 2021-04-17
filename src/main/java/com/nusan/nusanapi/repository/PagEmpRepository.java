package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Employees;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagEmpRepository extends PagingAndSortingRepository<Employees, Long> {
}
