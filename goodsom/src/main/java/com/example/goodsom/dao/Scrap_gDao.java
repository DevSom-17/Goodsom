package  com.example.goodsom.dao;

import java.util.*;
import com.example.goodsom.domain.Scrap_g;
import org.springframework.dao.DataAccessException;

public interface Scrap_gDao {
	
	List<Scrap_g> getScrap_gList() throws DataAccessException;
	
	Scrap_g createScrap_g(int groupBuyId) throws DataAccessException;
	
	int deleteScrap_g(int groupBuyId) throws DataAccessException;
}
