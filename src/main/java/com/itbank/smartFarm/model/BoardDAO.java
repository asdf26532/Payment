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

    @Select("<script>" +
            "SELECT * FROM member_board_view WHERE type = 101 " +
            "<if test='group != null and search != null'> " +
            "AND ${group} LIKE CONCAT('%', #{search}, '%') " +
            "</if> " +
            "ORDER BY id DESC " +
            "LIMIT #{offset}, #{boardCount}" +
            "</script>")
    List<BoardVO> getAllNotices(Map<String, Object> param);

    @Select("SELECT * FROM member_board_view WHERE id = #{id} AND type = 101")
    public BoardVO getOneNotice(int id);

    @Select("<script>" +
            "SELECT * FROM member_board_view WHERE type = 102 " +
            "<if test='group != null and search != null'> " +
            "AND ${group} LIKE CONCAT('%', #{search}, '%') " +
            "</if> " +
            "ORDER BY id DESC " +
            "LIMIT #{offset}, #{boardCount}" +
            "</script>")
    List<BoardVO> selectFreeAll(Map<String, Object> param);

    @Update("UPDATE board SET v_count = v_count + 1 WHERE id = #{id}")
    public int updateViewCount(int id);

    @Select("SELECT * FROM member_board_view WHERE type = 102 AND id = #{id}")
    BoardVO selectFreeOne(int id);

    @Insert("INSERT INTO board(title, member_id, type, contents) VALUES(#{title}, 2, 101, #{contents})")
    public int addNotice(BoardVO input);

    @Delete("DELETE FROM board WHERE id = #{id}")
    public int deleteBoard(int id);

    @Update("UPDATE board SET title = #{title}, contents = #{contents} WHERE id = #{id}")
    public int updateBoard(BoardVO input);

    @Insert("INSERT INTO board(title, contents, member_id, type) VALUES(#{title}, #{contents}, #{member_id}, 102)")
    int insertFb(BoardVO input);

    @Select("<script>" +
            "SELECT * FROM member_board_view WHERE type = 104" +
            "<if test='category != null and !category.isEmpty()'>" +
            " AND category = #{category}" +
            "</if>" +
            "<if test='soldout != null'>" +
            " AND soldout = #{soldout}" +
            "</if>" +
            "<if test='group != null and search != null'> " +
            "AND ${group} LIKE CONCAT('%', #{search}, '%') " +
            "</if> " +
            "ORDER BY id DESC " +
            "LIMIT #{offset}, #{boardCount}" +
            "</script>")
    public List<BoardVO> getAllFreemarkets(Map<String, Object> param);

    @Select("SELECT * FROM member_board_view WHERE id = #{id} AND type = 104")
    public BoardVO getOneFreeMarket(int id);

    @Insert("INSERT INTO board(category, soldout, title, member_id, type, contents) " +
            "VALUES(#{category}, #{soldout}, #{title}, #{member_id}, 104, #{contents})")
    int addFreeMarket(BoardVO input);

    @Update("UPDATE board SET title = #{title}, contents = #{contents}, category = #{category}, soldout = #{soldout} WHERE id = #{id}")
    public int updateFreeMarket(BoardVO input);

    @Select("<script>" +
            "SELECT * FROM member_board_view WHERE type = 105 " +
            "<if test='group != null and search != null'> " +
            "AND ${group} LIKE CONCAT('%', #{search}, '%') " +
            "</if> " +
            "ORDER BY id DESC " +
            "LIMIT #{offset}, #{boardCount}" +
            "</script>")
    List<BoardVO> selectQnaAll(Map<String, Object> param);

    @Select("SELECT * FROM member_board_view WHERE type = 105 AND id = #{id}")
    BoardVO selectQnaOne(int id);

    @Insert("INSERT INTO board(title, contents, member_id, type, secret) VALUES(#{title}, #{contents}, #{member_id}, 105, #{secret})")
    int insertQna(BoardVO input);

    @Select("<script>" +
            "SELECT COUNT(*) FROM member_board_view WHERE type = #{num} " +
            "<if test='category != null'> " +
            "AND category = #{category} " +
            "</if> " +
            "<if test='soldout != null'> " +
            "AND soldout = #{soldout} " +
            "</if> " +
            "<if test='group != null and search != null'> " +
            "AND ${group} LIKE CONCAT('%', #{search}, '%') " +
            "</if>" +
            "</script>")
    int searchboard(Map<String, Object> param);

    @Select("SELECT COUNT(*) FROM member_board_view WHERE type = #{num}")
    int totalboard(int num);

    // 댓글 조회
    @Select(
            "SELECT * FROM reply_view WHERE board_id = #{board_id} " +
            "ORDER BY id DESC ")
    List<ReplyVO> getReplies(int board_id);

    // 댓글 추가
    @Insert("INSERT INTO reply (board_id, member_id, contents) VALUES (#{board_id}, #{member_id}, #{contents})")
    int addReply(ReplyVO reply);

    // 댓글 삭제
    @Delete("DELETE FROM reply WHERE id = #{id}")
    int deleteReply(int id);

    // 글 삭제 시 참조중인 모든 댓글 삭제
    @Delete("DELETE FROM reply WHERE board_id = #{board_id}")
    int deleteReplyByBoardId(int board_id);

    // 해당 글 댓글 총 수
    @Select("SELECT COUNT(*) FROM reply_view WHERE board_id = #{board_id}")
    int totalreply(int board_id);

}
