package com.nusan.nusanapi.service;

import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    public Report createReport(Report report){return repository.save(report);}

    public Report getReportById(Long id){return repository.findById(id).orElse(null);}

    public Boolean existsById(Long id){return repository.existsById(id);}

    public void deleteReportById(Long id){repository.deleteById(id);}

    public void deleteReport(Report report){repository.delete(report);}


}
