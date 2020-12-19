package com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.controller.user.CreateReportForm;

public interface ReportDao {

	List<String> getReportList(int userId) throws DataAccessException; // 신고 현황 상세 페이지
	
	void createReport_a(CreateReportForm reportForm) throws DataAccessException;
	
	void createReport_g(CreateReportForm reportForm) throws DataAccessException;
	
	void updateReport(int userId) throws DataAccessException;
	
}
