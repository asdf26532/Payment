package com.itbank.smartFarm.Model;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.OrderItemVO;
import com.itbank.smartFarm.vo.OrdersVO;

@Mapper
public interface OrderDAO {
	// 상품 전체 - 데이터베이스에서 모든 주문 항목을 검색합니다.
	@Select("select * from orderitem order by id desc")
	List<OrderItemVO> selectAll();

	// 상품 하나 -  ID를 기반으로 단일 주문 항목을 검색합니다.
	@Select("select * from orderitem where id = #{id}")
	OrderItemVO selectOne(int id);

	// 장바구니 - order 페이지 - 특정 회원의 주문(장바구니 항목)을 검색합니다.
	@Select("SELECT * FROM cart WHERE member_id=#{id} AND delivery_status='결제 전'")
	public List<CartVO> getOrders(int i);

	// 수정할 주문 하나 - update 페이지 -업데이트를 위해 단일 주문(장바구니 항목)을 검색합니다.
	@Select("select * from cart where order_id=#{order_id}")
	public CartVO getOrder(int id);

	// 삭제 - 주문정보 - 데이터베이스에서 주문을 삭제합니다.
	@Delete("DELETE FROM orders WHERE id = #{order_id}")
	public int deleteOrder(@Param("order_id") int id);

	// 삭제 - 배송정보 - 주문과 관련된 배송 정보를 삭제합니다.
	@Delete("DELETE FROM shipments WHERE id = (SELECT delivery_id FROM orders WHERE id = #{id})")
	public int deleteShipmentByOrder(int id);

	// 주문 수정 - 개수 - 주문의 수량을 업데이트합니다.
	@Update("UPDATE orders SET count = #{count} WHERE id = #{order_id}")
	public int order(CartVO input);

	// 주문 수정 - 주소 -배송지의 주소를 업데이트합니다.
	@Update("update shipments set address=#{address}  where id=#{delivery_id}")
	public int modifyaddress(CartVO input);

	// 주문정보 생성 - 데이터베이스에 새 주문 항목을 생성합니다.
	@Insert("insert into orders(member_id, orderitem_id, delivery_id) values(#{member_id}, #{orderitem_id}, #{delivery_id})")
	public int makerorder(OrdersVO ov);

	// 배송정보 생성 데이터베이스에 새 배송 항목을 생성합니다.
	@Insert("insert into shipments(address) values(#{address})")
	public int makedelivery(String address);

	//최신 배송 ID를 검색합니다.
	@Select("SELECT id FROM shipments ORDER BY id DESC FETCH FIRST 1 ROWS ONLY")
	public int getdeliveryid();

	//최신 주문 항목 ID를 검색합니다.
	@Select("SELECT order_id FROM orderitem ORDER BY id DESC FETCH FIRST 1 ROWS ONLY")
	int getorderitem_id();

	//주문과 관련된 배송 ID를 검색합니다.
	@Select("select delivery_id from orders where id=#{id}")
	int getdelid(int id);

	//데이터베이스에서 배송 항목을 삭제합니다.
	@Delete("delete from shipments where id=#{deliverid}")
	int deliverid(int deliverid);

	//주문의 수량을 업데이트합니다.
	@Update("UPDATE orders SET count = #{count} WHERE id = #{order_id}")
	int count(CartVO cv);

	//최신 주문 ID를 검색합니다.
	@Select("SELECT id FROM orders ORDER BY id DESC FETCH FIRST 1 ROWS ONLY")
	int getorderid();

	//배송 상태를 "결제완료"로 업데이트합니다.
	@Update("update shipments set status ='결제완료' where id=#{delivery_id}")
	int deliveryid(int delivery_id);

	// 결제가 완료된 후의 회원의 장바구니 항목을 검색합니다.
	@Select("SELECT * FROM cart WHERE member_id=#{id} AND delivery_status!='결제 전'")
	List<CartVO> afterPay(int memberid);

	//특정 회원 및 주문 항목에 대한 기존 주문 ID를 검색합니다.
	@Select("SELECT o.id "
			+ "FROM orders o "
			+ "JOIN shipments s ON o.delivery_id = s.id  "
			+ "WHERE o.member_id = #{memberId} "
			+ "AND o.orderitem_id = #{orderItemId} "
			+ "AND s.status = '결제 전'")
	@ResultType(Integer.class)
	Integer getExistingOrderId(@Param("memberId") int memberId, @Param("orderItemId") int orderItemId);

	//기존 주문의 수량을 증가시킵니다.
	@Update("UPDATE orders "
			+ "SET count = count + #{count} "
			+ "WHERE id = #{order_id} "
			+ "AND delivery_id IN (SELECT id FROM shipments WHERE status != '결제완료')")
	int countUp(CartVO input);

}