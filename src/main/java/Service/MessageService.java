package Service;

import java.util.List;
import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

public class MessageService {
  private MessageDAO messageDAO;
  private AccountDAO accountDAO;

  public MessageService(){
      messageDAO = new MessageDAO();
  }

  public MessageService(MessageDAO messageDAO){
      this.messageDAO = messageDAO;
  }
  
  public List<Message> getAllMessages() {
      return messageDAO.getAllMessages();
  }
  
  public Message addMessage(Message message) {
    if (message.getMessage_text().length() < 1 || message.getMessage_text().length() > 254 || accountDAO.getAccountByID(message.getPosted_by()) == null)
      return null;
    return messageDAO.insertMessage(message);
  }

  public Message getMessageByID(int id) {
    return messageDAO.getMessageByID(id);
  }

  public int deleteMessageByID(int id) {
    return messageDAO.deleteMessageByID(id);
  }

  public int updateMessageByID(int id, String newMessageText) {
    Message message = messageDAO.getMessageByID(id);
    if (message == null || newMessageText.length() < 1 || newMessageText.length() > 254)
      return 0;
    message.setMessage_text(newMessageText);
    return messageDAO.updateMessageByID(message);
  }

  public List<Message> getMessagesByAccountID(int id) {
    return messageDAO.getMessagesByAccountID(id);
  }

}