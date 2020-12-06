package com.example.goodsom.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;

public interface FileMapper {
	
	void saveAuctionImgs(Map<String, Object> map) throws DataAccessException;
	
	void saveGroupBuyImgs(Map<String, Object> map) throws DataAccessException;
	
	void deleteAuctionImgs(int auctionId) throws DataAccessException;
	
	void deleteGroupBuyImgs(int groupBuyId) throws DataAccessException;
	
	List<Image_a> getAuctionImgs(int auctionId) throws DataAccessException;
	
	List<Image_g> getGroupBuyImgs(int groupBuyId) throws DataAccessException;
}
