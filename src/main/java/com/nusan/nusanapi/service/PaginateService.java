package com.nusan.nusanapi.service;

import com.nusan.nusanapi.model.Client;
import com.nusan.nusanapi.model.Report;
import com.nusan.nusanapi.repository.PagCliRepository;
import com.nusan.nusanapi.repository.PagRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaginateService {

    @Autowired
    private PagCliRepository repositoryC;

    @Autowired
    private PagRepRepository repositoryR;

    public List<Client> getAllClients(Integer numPage, Integer sizePage, String sort, boolean ascending){
        Pageable paging;
        if(ascending){
            paging = PageRequest.of(numPage,sizePage, Sort.by(sort));
        }else{
            paging = PageRequest.of(numPage,sizePage, Sort.by(sort).descending());
        }
        Page<Client> pagedClients = repositoryC.findAll(paging);

        if(pagedClients.hasContent()){
            return pagedClients.getContent();
        }else {
            return new ArrayList<>();
        }
    }

    public List<Report> getAllReports(Integer numPage, Integer sizePage, String sort, boolean ascending){
        PageRequest paging;
        if(ascending){
            paging = PageRequest.of(numPage,sizePage, Sort.by(sort));
        }else{
            paging = PageRequest.of(numPage,sizePage, Sort.by(sort).descending());
        }
        Page<Report> pagedReports= repositoryR.findAll(paging);

        if(pagedReports.hasContent()){
            return pagedReports.getContent();
        }else {
            return new ArrayList<>();
        }
    }
}