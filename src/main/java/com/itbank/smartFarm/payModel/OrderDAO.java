package com.itbank.smartFarm.payModel;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.OrderItemVo;
import com.itbank.smartFarm.vo.ShipmentsVO;

@Mapper
public interface OrderDAO {
	//상품 전체
	@Select("select * from orderitem order by id desc")
	List<OrderItemVo> selectAll();
	
	//상품 하나
	@Select("select * from orderitem where id = #{id}")
	OrderItemVo selectOne(int id);
	
	//장바구니
	@Select("select * from cart where member_id=#{id}")
	public List<CartVO> getOrders(int i);

	//수정할 주문 하나
	@Select("select * from cart where order_id=#{order_id}")
	public CartVO getOrder(int id);

	//삭제
    @Delete("DELETE FROM orderitem WHERE order_id = #{order_id}")
    int deleteOrderItems(@Param("order_id") int orderId);
    
    @Delete("DELETE FROM orders WHERE id = #{order_id}")
    int deleteOrder(@Param("order_id") int orderId);
    
    @Delete("DELETE FROM shipments WHERE id = (SELECT delivery_id FROM orders WHERE id = #{order_id})")
    int deleteShipmentByOrder(@Param("order_id") int orderId);
    
    //주문 수정
	@Update("UPDATE orderitem SET count = #{count} WHERE order_id = #{order_id}")
	public int order(CartVO input);

    @Update("update shipments set address=#{address}  where id=#{delivery_id}")
	public int modifyaddress(CartVO input);
}