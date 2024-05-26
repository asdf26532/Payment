package com.itbank.smartFarm.model;

import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.MessageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatDAO {

    @Select("SELECT m.*, u.nick, m.timestamp, m.senderId, m.receiverId FROM message m JOIN member u ON m.senderId = u.id " +
            "WHERE (m.senderId = #{senderId} AND m.receiverId = #{receiverId}) " +
            "OR (m.senderId = #{receiverId} AND m.receiverId = #{senderId}) " +
            "ORDER BY m.timestamp")
    List<MessageVO> findBySenderIdAndReceiverId(int senderId, int receiverId);

    @Insert("INSERT INTO message (senderId, receiverId, content, timestamp) VALUES (#{senderId}, #{receiverId}, #{content}, NOW())")
    void insertMessage(MessageVO message);

    @Select("SELECT m.*, u.nick, m.timestamp FROM message m JOIN member u ON m.senderId = u.id WHERE m.id = #{messageId}")
    MessageVO findMessageById(int messageId);

    @Select("SELECT m.*, u.nick FROM message m JOIN member u ON m.senderId = u.id WHERE m.id = LAST_INSERT_ID()")
    MessageVO findLastMessage();

    @Select("SELECT DISTINCT u.* " +
            "FROM message m " +
            "JOIN member u ON (m.senderId = u.id OR m.receiverId = u.id)" +
            "WHERE (m.senderId = #{receiverId} OR m.receiverId = #{receiverId})" +
            "AND u.id != #{receiverId}")
    List<MemberVO> findSendersByReceiverId(int receiverId);

}
