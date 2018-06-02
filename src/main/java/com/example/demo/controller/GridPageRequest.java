package com.example.demo.controller;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class GridPageRequest extends PageRequest {

    public GridPageRequest(int page, int rows,  String sidx, String sord) {
        super(page, rows, getSortProperty(sidx, sord));
    }

    private static Sort getSortProperty(String sidx, String sord) {
        if(StringUtils.isNullOrEmpty(sidx) || StringUtils.isNullOrEmpty(sord)) {
            return Sort.unsorted();
        }
        return Sort.by(Sort.Direction.fromString(sord), sidx);
    }
}
