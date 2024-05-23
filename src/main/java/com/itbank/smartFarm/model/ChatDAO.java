package com.itbank.smartFarm.model;

import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.MessageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatDAO {

    @Select("SELECT m.*, u.nick, m.timestamp FROM message m JOIN member u ON m.sender_id = u.id " +
            "WHERE (m.sender_id = #{senderId} AND m.receiver_id = #{receiverId}) " +
            "OR (m.sender_id = #{receiverId} AND m.receiver_id = #{senderId}) " +
            "ORDER BY m.timestamp")
    List<MessageVO> findBySenderIdAndReceiverId(int senderId, int receiverId);

    @Insert("INSERT INTO message (sender_id, receiver_id, content, timestamp) VALUES (#{senderId}, #{receiverId}, #{content}, NOW())")
    void insertMessage(MessageVO message);

    @Select("SELECT m.*, u.nick, m.timestamp FROM message m JOIN member u ON m.sender_id = u.id WHERE m.id = #{messageId}")
    MessageVO findMessageById(int messageId);

    @Select("SELECT m.*, u.nick FROM message m JOIN member u ON m.sender_id = u.id WHERE m.id = LAST_INSERT_ID()")
    MessageVO findLastMessage();

    @Select("SELECT DISTINCT u.* FROM message m JOIN member u ON m.sender_id = u.id WHERE m.receiver_id = #{receiverId}")
    List<MemberVO> findSendersByReceiverId(int receiverId);

}
