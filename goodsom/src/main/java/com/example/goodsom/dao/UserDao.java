package  com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;

/**
 * @author Yejin Lee | kimdahyee  | Seonmi Hwang
 * @since 2020.05.06 | 2020.06.12 | 2020.06.21
 */

public interface UserDao {

	User getUserByEmail(String email) throws DataAccessException; // email로 user객체 얻어옴
	
	User getUserByUserId(int userId) throws DataAccessException; // userId로 user객체 얻어옴
	
	void createUser(User user) throws DataAccessException;

	int updateUser(User user) throws DataAccessException;

	void deleteUser(User user) throws DataAccessException;
	
	List<Order> getAuctionOrderList(int userId);
	
	List<Order> getGroupBuyOrderList(int userId);
	
}
