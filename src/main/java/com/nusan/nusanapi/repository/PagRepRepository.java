package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Report;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagRepRepository extends PagingAndSortingRepository<Report, Integer> {
}
