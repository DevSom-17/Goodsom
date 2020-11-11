package  com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Menu;

public interface MenuDao {
	
	List<Menu> getMenuList() throws DataAccessException;

	Menu getMenu(int menuId) throws DataAccessException;
}
