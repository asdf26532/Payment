package com.itbank.smartFarm.model;

import com.itbank.smartFarm.vo.ReplyVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.itbank.smartFarm.vo.BoardVO;

@Mapper
public interface BoardDAO {
    @Select("SELECT * FROM member_board_view WHERE type = 101 ORDER BY id DESC")
    public List<BoardVO> getAllNotices();

    @Select("SELECT * FROM member_board_view WHERE id = #{id} AND type = 101")
    public BoardVO getOneNotice(int id);

    List<BoardVO>selectFreeAll(Map<String, Object> param);

    @Update("UPDATE board SET v_count = v_count + 1 WHERE id = #{id}")
    public int updateViewCount(int id);

    @Select("select * from member_board_view where type = 102 and id = #{id}")
    BoardVO selectFreeOne(int id);

    @Insert("INSERT INTO BOARD(title, member_id, type, contents) values(#{title}, 1001, 101, #{contents})")
    public int addNotice(BoardVO input);

//    @Insert("insert into Board(title, contents) values(#{title}, #{contents}) where type_number = 103")
//    void inComment(BoardVO input);

    @Delete("DELETE FROM BOARD WHERE id = #{id}")
    public int deleteBoard(int id);

    @Select("select count(*) from member_board_view where type = 102")
    int totalBoard();

    @Update("UPDATE board SET title = #{title}, contents = #{contents} WHERE id = #{id}")
    public int updateBoard(BoardVO input);

    @Insert("insert into board(title, contents, member_id, type) values(#{title}, #{contents}, #{member_id}, 102)")
    int insertFb(BoardVO input);

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

    List<BoardVO> selectQnaAll(Map<String, Object> param);

    @Select("select * from board where type = 105 and id = #{id}")
    BoardVO selectQnaOne(int id);

    @Insert("insert into board(title, contents, member_id) values(#{title}, #{contents}, #{member_id}) " +
            "where type = 105")
    int insertQna(BoardVO input);


    @Select("select * from reply_view order by id desc")
    List<ReplyVO> selectReplyAll();

    List<ReplyVO> selectReplys(int b_idx);

    int insertReply(ReplyVO input);

}
