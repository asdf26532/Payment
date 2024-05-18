package com.itbank.smartFarm.payModel;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.OrderItemVo;

@Mapper
public interface OrderDAO {
	@Select("select * from cart where member_id=#{member_id}")
	public List<CartVO> getOrders(int membe_id);

	@Select("select * from cart where order_id=#{order_id}")
	public CartVO getOrder(int id);

	@Update("UPDATE orderitems SET count = #{count} WHERE order_id = #{order_id}")
	public int order(OrderItemVo input);

    @Delete("DELETE FROM orderitems WHERE order_id = #{order_id}")
    int deleteOrderItems(@Param("order_id") int orderId);
    
    @Delete("DELETE FROM orders WHERE id = #{order_id}")
    int deleteOrder(@Param("order_id") int orderId);
    
    @Delete("DELETE FROM shipments WHERE id = (SELECT delivery_id FROM orders WHERE id = #{order_id})")
    int deleteShipmentByOrder(@Param("order_id") int orderId);
}