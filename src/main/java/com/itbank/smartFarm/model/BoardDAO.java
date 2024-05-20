package com.itbank.smartFarm.model;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.itbank.smartFarm.model.vo.BoardVO;

@Mapper
public interface BoardDAO {
    @Select("SELECT * FROM member_board_view WHERE type = 101 ORDER BY id DESC")
    public List<BoardVO> getAllNotices();

    @Select("SELECT * FROM member_board_view WHERE id = #{id} AND type = 101")
    public BoardVO getOneNotice(int id);

    @Update("UPDATE board SET v_count = v_count + 1 WHERE id = #{id}")
    public int updateViewCount(int id);

    @Insert("INSERT INTO BOARD(title, member_id, type, contents) values(#{title}, 1001, 101, #{contents})")
    public int addNotice(BoardVO input);

    @Delete("DELETE FROM BOARD WHERE id = #{id}")
    public int deleteBoard(int id);

    @Update("UPDATE board SET title = #{title}, contents = #{contents} WHERE id = #{id}")
    public int updateNotice(BoardVO input);

    @Select("<script>" +
            "SELECT * FROM member_board_view WHERE type = 104" +
            "<if test='category != null and !category.isEmpty()'>" +
            " AND category = #{category}" +
            "</if>" +
            "<if test='soldout != null'>" +
            " AND soldout = #{soldout}" +
            "</if>" +
            " ORDER BY id DESC" +
            "</script>")
    public List<BoardVO> getAllFreemarkets(@Param("category") String category, @Param("soldout") Integer soldout);

    @Select("SELECT * FROM member_board_view WHERE id = #{id} AND type = 104")
	public BoardVO getOneFreeMarket(int id);
    
    @Insert("INSERT INTO BOARD(category, soldout, title, member_id, type, contents) " +
            "VALUES(#{category}, #{soldout}, #{title}, #{member_id}, 104, #{contents})")
    int addFreeMarket(BoardVO input);
    
    @Update("UPDATE board SET title = #{title}, contents = #{contents}, category = #{category}, soldout = #{soldout} WHERE id = #{id}")
    public int updateFreeMarket(BoardVO input);


}
