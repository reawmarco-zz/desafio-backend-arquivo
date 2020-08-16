package com.salesman.service;

import com.salesman.dto.ReportDTO;
import org.springframework.stereotype.Service;

@Service
public class DataResultService implements ISaveResultFile {

    public DataResultService() {
    }

    @Override
    public String saveResult(ReportDTO reportDTO) {
        return reportDTO.formattedReport();
    }
}
