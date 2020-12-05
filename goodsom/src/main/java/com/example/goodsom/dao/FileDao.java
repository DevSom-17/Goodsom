package com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;

public interface FileDao {

	void saveAuctionImgs(int auctionId) throws DataAccessException;
	
	void saveGroupBuyImgs(int groupBuyId) throws DataAccessException;

	void deleteAuctionImgs(int auctionId) throws DataAccessException;
	
	void deleteGroupBuyImgs(int groupBuyId) throws DataAccessException;

	List<Image_a> getAuctionImgs(int auctionId) throws DataAccessException;
	
	List<Image_g> getGroupBuyImgs(int groupBuyId) throws DataAccessException;
	
}
