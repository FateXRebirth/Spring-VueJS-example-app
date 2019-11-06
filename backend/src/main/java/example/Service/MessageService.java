package example.Service;

import example.Repository.DialogueRepository;
import example.Repository.MessageRepository;
import example.Request.Dialogue;
import example.Request.Message;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DialogueRepository dialogueRepository;

    public Result Message(Message message) { return messageRepository.create(message); }

    public Result getMessagesByID(int MemberID) { return messageRepository.getMessagesByID(MemberID); }

    public Result hasUnReadMessagesByID(int MemberID) { return messageRepository.hasUnReadMessagesByID(MemberID); }

    public Result Dialogue(Dialogue dialogue) { return dialogueRepository.create(dialogue); }

    public Result getDialoguesByID(int MemberID) { return dialogueRepository.getDialogueByID(MemberID); }
}
