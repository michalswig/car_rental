package com.company.service;

import com.company.dao.CommonDAO;
import com.company.dto.TopEarnersByMonth;
import com.company.dto.TopEarnersByRentedDays;
import com.company.dto.TopEarnersByYear;

import java.util.List;

public class CommonService {

    private CommonDAO commonDAO;


    public CommonService(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    public List<TopEarnersByMonth> getTopEarnerMonth() {
        return commonDAO.getTopEarnersByMonthReport();
    }

    public List<TopEarnersByYear> getTopEarnerYear() {
        return commonDAO.getTopEarnersByYearReport();
    }

    public List<TopEarnersByRentedDays> getTopEarnerByRentedDays() {
        return commonDAO.getTopEarnersByRentedDaysReport();
    }


}
