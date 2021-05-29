package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {
    /*
    @Query(value = "select r.* from reports as r join clients as c on c.id_client = r.cli_id join employees as e on r.emp_id = e.id_employee where state = ?1 and machine = ?2", nativeQuery = true)
    List<Report> allReportsByFilters(String filters);
    */
    @Query("select r from reports r where r.state = ?1")
    List<Report> allReportsByState(String state);

    @Query(value = "select * from reports r where r.ending_date = ?1", nativeQuery = true)
    List<Report> allReportsByDateEnd(String date);

    @Query(value = "select * from reports r where r.state = 'Abierto' AND r.emp_id = ?1", nativeQuery = true)
    List<Report> allReportsByUserId(int id);
}
