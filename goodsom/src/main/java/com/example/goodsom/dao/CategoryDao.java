package  com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Category;

public interface CategoryDao {
	
	List<Category> getCategoryList(String menuId) throws DataAccessException;

	Category getCategory(String categoryId) throws DataAccessException;

}
