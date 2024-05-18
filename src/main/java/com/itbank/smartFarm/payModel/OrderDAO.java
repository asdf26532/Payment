package com.itbank.smartFarm.payModel;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.OrderItemVo;

@Mapper
public interface OrderDAO {

//	@Insert("insert into orders(id, member_id, orderitems_id, delivery_id, status) values(#{id}, #{member_id}, #{orderitems_id}, #{delivery_id}, #{status}")
//	public int addorders(OrdersVO ov);
//	
//	@Insert("insert into shipments(id, address, status) values(#{id}, #{address}, #{status}")
//	public int addshipments(ShipmentsVO sv);
//	
//	@Insert("insert into orderitems(id, order_id, order_price, count) values(#{id}, #{order_id}, #{order_price},#{count}")
//	public int addorderitems(OrderItemVo oiv);
	
	@Select("select * from cart where member_id=#{member_id}")
	public List<CartVO> getOrders(int membe_id);

	@Select("select * from cart where order_id=#{order_id}")
	public CartVO getOrder(int id);

	@Update("UPDATE orderitems SET count = #{count} WHERE order_id = #{order_id}")
	public int order(OrderItemVo input);

	@Delete("delete from orderitems where id=#{id}")
	public int deleteOrder(int id);

	

	

	


}