package com.example.goodsom.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.goodsom.dao.FileDao;
import com.example.goodsom.dao.mybatis.mapper.FileMapper;
import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;

@Repository
public class MybatisFileDao implements FileDao {

	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public void saveAuctionImgs(List<Image_a> auctionImgs) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();	
		map.put("list", auctionImgs);
		fileMapper.saveAuctionImgs(map);
	}

	@Override
	public void saveGroupBuyImgs(List<Image_g> groupBuyImgs) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();	
		map.put("groupBuyImgsList", groupBuyImgs);
		fileMapper.saveGroupBuyImgs(map);
	}

	@Override
	public void deleteAuctionImgs(int auctionId) throws DataAccessException {
		fileMapper.deleteAuctionImgs(auctionId);
	}
	
	@Override
	public void deleteGroupBuyImgs(int groupBuyId) throws DataAccessException {
		fileMapper.deleteGroupBuyImgs(groupBuyId);
	}

	@Override
	public List<Image_a> getAuctionImgs(int auctionId) throws DataAccessException {
		return fileMapper.getAuctionImgs(auctionId);
	}

	@Override
	public List<Image_g> getGroupBuyImgs(int groupBuyId) throws DataAccessException {
		return fileMapper.getGroupBuyImgs(groupBuyId);
	}

	
}
