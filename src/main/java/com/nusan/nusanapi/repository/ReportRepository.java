package com.nusan.nusanapi.repository;


import com.nusan.nusanapi.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {

    @Query("select r from reports r where r.state = ?1")
    List<Report> allReportsByState(String state);
}
