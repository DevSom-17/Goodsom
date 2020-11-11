package  com.example.goodsom.dao;

import com.example.goodsom.domain.Comment_q;
import org.springframework.dao.DataAccessException;

public interface Comment_qDao {
	
	Comment_q getComment_q(int questionId) throws DataAccessException;
	
	Comment_q createComment_q(Comment_q comment) throws DataAccessException;
	
	void updateComment_q(Comment_q comment) throws DataAccessException;
	
	void deleteComment_q(int commentId) throws DataAccessException;
}
