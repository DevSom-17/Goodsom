package com.example.goodsom.controller.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.goodsom.service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
    @RequestMapping("/clickLikeAuction.do")
    @ResponseBody
    public Map<String,Object> clickLikeAuction(@RequestParam Map<String,Object> commandMap){
        System.out.println("request: /clickLikeAuction.do");
        int resultCode = 1;
        int likeCheck = 1;
        int auctionId = Integer.valueOf((String)commandMap.get("auctionId"));
        int userId = Integer.valueOf((String)commandMap.get("userId"));
        int result = 0;
        Map<String,Object> resultMap = new HashMap<>();
        try {
            result = likeService.likeCheckOfAuctionByUserId(userId, auctionId);
            if(result == 0) {
                //처음 좋아요 누른것 likecheck=1, 좋아요 빨간색이 되야됨
                likeService.likeAuction(userId, auctionId); //좋아요 테이블 인서트
                resultCode = 1;
            }
            else {
                //좋아요 취소한거 likecheck=0, 빈하트 되야됨
                likeCheck = 0;
                likeService.unlikeAuction(userId, auctionId);
                resultCode = 0;
            }
            int likeCount = likeService.getLikeCountOfAuction(auctionId); 
            resultMap.put("likeCount", likeCount);
        } catch (Exception e) {
//            logger.debug(e.getMessage());
            resultCode = -1;
        }
        resultMap.put("likeCheck", likeCheck);
        resultMap.put("resultCode", resultCode);
        //resultCode가 1이면 빨간하트 0이면 빈하트
        return resultMap;
    }
    
    @RequestMapping("/clickLikeGroupBuy.do")
    @ResponseBody
    public Map<String,Object> clickLikeGroupBuy(@RequestParam Map<String,Object> commandMap){
        System.out.println("request: /clickLikeGroupBuy.do");
        int resultCode = 1;
        int likeCheck = 1;
        int groupBuyId = Integer.valueOf((String)commandMap.get("groupBuyId"));
        int userId = Integer.valueOf((String)commandMap.get("userId"));
        int result = 0;
        Map<String,Object> resultMap = new HashMap<>();
        try {
            result = likeService.likeCheckOfGroupBuyByUserId(userId, groupBuyId);
            if(result == 0) {
                //처음 좋아요 누른것 likecheck=1, 좋아요 빨간색이 되야됨
                likeService.likeGroupBuy(userId, groupBuyId); //좋아요 테이블 인서트
                resultCode = 1;
            }
            else {
                //좋아요 취소한거 likecheck=0, 빈하트 되야됨
                likeCheck = 0;
                likeService.unlikeGroupBuy(userId, groupBuyId);
                resultCode = 0;
            }
            int likeCount = likeService.getLikeCountOfGroupBuy(groupBuyId); 
            resultMap.put("likeCount", likeCount);
        } catch (Exception e) {
//            logger.debug(e.getMessage());
            resultCode = -1;
        }
        resultMap.put("likeCheck", likeCheck);
        resultMap.put("resultCode", resultCode);
        //resultCode가 1이면 빨간하트 0이면 빈하트
        return resultMap;
    }
}
