package example.Service;

import example.Repository.MessageRepository;
import example.Request.Message;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Result create(Message message) { return messageRepository.create(message); }

    public Result getMessagesByID(int MemberID) { return messageRepository.getMessagesByID(MemberID); }

    public Result hasUnReadMessagesByID(int MemberID) { return messageRepository.hasUnReadMessagesByID(MemberID); }
}
