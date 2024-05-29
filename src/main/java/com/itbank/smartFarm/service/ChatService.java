package com.itbank.smartFarm.service;

import com.itbank.smartFarm.model.ChatDAO;
import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatDAO dao;

    public List<MessageVO> getMessagesBySenderAndReceiver(int senderId, int receiverId) {
        return dao.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public void saveMessage(MessageVO message) {
        dao.insertMessage(message);
    }

    public MessageVO getCompleteMessageInfo(int messageId) {
        return dao.findMessageById(messageId);
    }

    public MessageVO getLastMessage() {
        return dao.findLastMessage();
    }

    public List<MemberVO> getSendersByReceiverId(int receiverId) {
        return dao.findSendersByReceiverId(receiverId);
    }

    public void startChat(MessageVO message) {
        dao.insertMessage(message);
    }
}
