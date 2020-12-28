package com.example.goodsom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.controller.user.CreateReportForm;
import com.example.goodsom.dao.ReportDao;
import com.example.goodsom.dao.mybatis.mapper.ReportMapper;

/**
 * @author Seonmi Hwang
 * @since 2020.11.25
 */

@Repository
public class MybatisReportDao implements ReportDao {

	@Autowired
	ReportMapper reportMapper;
	
	public List<String> getReportList(int userId) throws DataAccessException { // 신고 현황 상세 페이지 
		return reportMapper.getReportList(userId);
	}
	
	public void createReport_a(CreateReportForm reportForm) throws DataAccessException { // 신고 현황 상세 페이지 
		reportMapper.createReport_a(reportForm);
	}
	
	public void createReport_g(CreateReportForm reportForm) throws DataAccessException { // 신고 현황 상세 페이지 
		reportMapper.createReport_g(reportForm);
	}
	
	@Transactional
	public void updateReport(int userId) throws DataAccessException {
		reportMapper.updateReport(userId);
		
		int report = reportMapper.getReport(userId);
		int warning = report / 3;
		
		reportMapper.updateWarning(warning, userId);
	}
	
}
