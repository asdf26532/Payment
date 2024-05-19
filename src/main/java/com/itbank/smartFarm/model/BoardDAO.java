package com.itbank.smartFarm.model;

import com.itbank.smartFarm.vo.BoardVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDAO {

    List<BoardVO>selectFreeAll(Map<String, Object> param);

    @Select("select * from board where type_number = 102 and id_number = #{idx}")
    BoardVO selectFreeOne(int idx);

    @Insert("insert into Board(title, contents) values(#{title}, #{contents}) where type_number = 103")
    void inComment(BoardVO input);

    @Select("select count(*) from freeBoard_view")
    int totalBoard();

    @Insert("insert into board(title, contents, member_id) values(#{title}, #{contents}, #{member_id}) " +
            "where type_number = 102")
    int insertFb(BoardVO input);

    @Delete("delete from board where id_number = #{idx}")
    int delete(int idx);

    List<BoardVO> selectQnaAll(Map<String, Object> param);

    @Select("select * from board where type_number = 105 and id_number = #{idx}")
    BoardVO selectQnaOne(int idx);

    @Insert("insert into board(title, contents, member_id) values(#{title}, #{contents}, #{member_id}) " +
            "where type_number = 105")
    int insertQna(BoardVO input);

}
