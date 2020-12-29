package com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;
/**
 * @author Yejin Lee
 * @since 2020.12.5
 */
public interface FileDao {

	void saveAuctionImgs(List<Image_a> auctionImgs) throws DataAccessException;
	
	void saveGroupBuyImgs(List<Image_g> groupBuyImgs) throws DataAccessException;

	void deleteAuctionImgs(int auctionId) throws DataAccessException;
	
	void deleteGroupBuyImgs(int groupBuyId) throws DataAccessException;

	List<Image_a> getAuctionImgs(int auctionId) throws DataAccessException;
	
	List<Image_g> getGroupBuyImgs(int groupBuyId) throws DataAccessException;
	
}
