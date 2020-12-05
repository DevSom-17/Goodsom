package com.example.goodsom.dao.mybatis;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.dao.FileDao;
import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;

public class MybatisFileDao implements FileDao {

	@Override
	public void saveAuctionImgs(int auctionId) throws DataAccessException {
		
	}

	@Override
	public void saveGroupBuyImgs(int groupBuyId) throws DataAccessException {
		
	}

	@Override
	public void deleteAuctionImgs(int auctionId) throws DataAccessException {
		
	}
	
	@Override
	public void deleteGroupBuyImgs(int groupBuyId) throws DataAccessException {
		
	}

	@Override
	public List<Image_a> getAuctionImgs(int auctionId) throws DataAccessException {
		return null;
	}

	@Override
	public List<Image_g> getGroupBuyImgs(int groupBuyId) throws DataAccessException {
		return null;
	}

	
}
