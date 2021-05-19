package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {

    @Query("select r from reports r where r.state = ?1")
    List<Report> allReportsByState(String state);

    @Query(value = "select * from reports r where r.ending_date = ?1", nativeQuery = true)
    List<Report> allReportsByDateEnd(String date);

    @Query(value = "select * from reports r where r.date_apointment = ?1", nativeQuery = true)
    List<Report> allReportsByDateAppointment(String date);

    @Query(value = "select * from reports r where r.start_date = ?1", nativeQuery = true)
    List<Report> allReportsByDateStart(String date);

    @Query(value = "select * from reports r where r.priority = ?1", nativeQuery = true)
    List<Report> allReportsByPriority(String priority);

    @Query(value = "select * from reports r where r.cli_id = ?1", nativeQuery = true)
    List<Report> allReportsByIdClient(int id);

    @Query(value = "select id_client from clients c where c.last_name = ?1", nativeQuery = true)
    List<Integer> allReportsByClientLastName(String lastName);

    @Query(value = "select id_client from clients c where c.dni = ?1", nativeQuery = true)
    List<Integer> allReportsByClientDNI(String dni);

    @Query(value = "select * from reports r where r.emp_id = ?1", nativeQuery = true)
    List<Report> allReportsByIdEmployee(int id);

    @Query(value = "select id_employee from employees e where e.last_name = ?1", nativeQuery = true)
    List<Integer> allReportsByEmployeeLastName(String lastName);

    @Query(value = "select id_employee from employees e where e.dni = ?1", nativeQuery = true)
    List<Integer> allReportsByEmployeeDNI(String dni);
}
