package com.nusan.nusanapi.repository;

import com.nusan.nusanapi.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagCliRepository extends PagingAndSortingRepository<Client, Long> {
}
