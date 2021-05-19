package com.nusan.nusanapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public Report createReport(Report report){return repository.save(report);}

    public Report getReportById(Long id){return repository.findById(id).orElse(null);}

    public Boolean existsById(Long id){return repository.existsById(id);}

    public void deleteReportById(Long id){repository.deleteById(id);}

    public void deleteReport(Report report){repository.delete(report);}

    public Report applyPatchToUser(JsonPatch patch, Report targetUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, Report.class);
    }

    public List<Report> allReportsByState(String state){return repository.allReportsByState(state);}

    public List<Report> allReportsByDateEnd(String date){ return repository.allReportsByDateEnd(date);}

    public List<Report> allReportsByDateAppointment(String date){ return repository.allReportsByDateAppointment(date);}

    public List<Report> allReportsByDateStart(String date){ return repository.allReportsByDateStart(date);}

    public List<Report> allReportsByPriority(String priority){ return repository.allReportsByPriority(priority);}

    public List<Report> allReportsByIdClient(int id){ return repository.allReportsByIdClient(id);}

    public List<Integer> allReportsByClientLastName(String lastName){ return repository.allReportsByClientLastName(lastName);}

    public List<Integer> allReportsByClientDNI(String dni){ return repository.allReportsByClientDNI(dni);}

    public List<Report> allReportsByIdEmployee(int id){ return repository.allReportsByIdEmployee(id);}

    public List<Integer> allReportsByEmployeeLastName(String lastName){ return repository.allReportsByEmployeeLastName(lastName);}

    public List<Integer> allReportsByEmployeeDNI(String dni){ return repository.allReportsByEmployeeDNI(dni);}





}
