package com.example.demo.service.interfaces;

import com.example.demo.domain.Application;
import com.example.demo.service.base.IEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IApplicationService extends IEntityService<Application, String> {

    Page<Application> findAllByEnrolleeId(String enrollee_id, Pageable pageable);

    Page<Application> findAllBySpecialityIdOrderByPoint(String speciality_id, Short limit, Pageable pageable);
}