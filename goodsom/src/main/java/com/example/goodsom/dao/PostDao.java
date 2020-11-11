package  com.example.goodsom.dao;

import com.example.goodsom.domain.Post;
import java.util.*;
import org.springframework.dao.DataAccessException;

public interface PostDao {
	
	List<Post> getPostList() throws DataAccessException;
	
	Post getPost(int postId) throws DataAccessException;
	
	Post createPost(Post post) throws DataAccessException;
	
	void updatePost(Post post) throws DataAccessException;
	
	void deletePost(int postId) throws DataAccessException;
	
	List<Post> getPostByKeyword(String keyword) throws DataAccessException;
	
}
