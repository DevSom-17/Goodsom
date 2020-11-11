package  com.example.goodsom.dao;

import java.util.List;

import com.example.goodsom.domain.LineGroupBuy;

/**
 * @author Seonmi Hwang
 * @since 2020.06.27
 */

public interface LineGroupBuyDao {
	
	void insertLineGroupBuy(LineGroupBuy lineGroupBuy);
	
	List<LineGroupBuy> getLineGroupBuys(int orderId);
}
