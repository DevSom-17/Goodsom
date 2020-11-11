package  com.example.goodsom.dao;

import com.example.goodsom.domain.Comment_p;
import java.util.*;
import org.springframework.dao.DataAccessException;

public interface Comment_pDao {
	
	List<Comment_p> getComment_pList(int postId) throws DataAccessException;
	
	Comment_p getComment_p(int postId) throws DataAccessException;
	
	Comment_p createCommnet_p(Comment_p comment) throws DataAccessException;
	
	void updateComment_p(Comment_p comment) throws DataAccessException;
	
	void deleteComment_p(int commentId) throws DataAccessException;
}
