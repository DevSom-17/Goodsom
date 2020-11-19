package  com.example.goodsom.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.GroupBuy;

public interface GroupBuyDao {
	
	GroupBuy getGroupBuy(int groupBuyId) throws DataAccessException;

	void createGroupBuy(GroupBuy groupBuy) throws DataAccessException;

	int updateGroupBuy(GroupBuy groupBuy) throws DataAccessException;
	
	void deleteOptions(int groupBuyId) throws DataAccessException;
	
	void deleteGroupBuy(int groupBuyId) throws DataAccessException;

	void createOptions(GroupBuy groupBuy) throws DataAccessException;
	
	List<GroupBuy> getGroupBuyList() throws DataAccessException;
	
	void increaseCount(GroupBuy groupBuy) throws DataAccessException;

	int updateState(GroupBuy groupBuy) throws DataAccessException;
	
	void closeEvent() throws DataAccessException;
	
	void achieveEvent() throws DataAccessException;
	
	void updateAchieveNoti(int groupBuyId) throws DataAccessException;
	
	void updateCloseNoti(int groupBuyId) throws DataAccessException;
	
	int[] getGroupBuyIdForAchieveNoti() throws DataAccessException;
	
	int[] getGroupBuyIdForCloseNoti() throws DataAccessException;
	
	List<GroupBuy> getRecentGroupBuyList() throws DataAccessException;
	
	List<GroupBuy> groupBuyListByKeyword(String keyword) throws DataAccessException;
	
}
