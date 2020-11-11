package  com.example.goodsom.dao;

import java.util.*;
import com.example.goodsom.domain.Scrap_a;
import org.springframework.dao.DataAccessException;

public interface Scrap_aDao {
	
	List<Scrap_a> getScrap_aList() throws DataAccessException;
	
	Scrap_a createScrap_a(int auctionId) throws DataAccessException;
	
	int deleteScrap_a(int auctionId) throws DataAccessException;
	
}
