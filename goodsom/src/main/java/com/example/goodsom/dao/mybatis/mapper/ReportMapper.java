package com.example.goodsom.dao.mybatis.mapper;

import java.util.List;

import com.example.goodsom.controller.user.CreateReportForm;

public interface ReportMapper {

	List<String> getReportList(int userId); // 신고 현황 상세 페이지
	
	void createReport_a(CreateReportForm reportForm);
	
	void createReport_g(CreateReportForm reportForm);
	
	int getReport(int userId);
	
	void updateReport(int userId);
	
	void deleteReportsByUserId_a(int userId);
	
	void deleteReportsByUserId_g(int userId);
	
	void updateWarning(int warning, int userId);
}
