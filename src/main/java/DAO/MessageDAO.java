package DAO;

import Model.Message;
import Util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

  public List<Message> getAllMessages(){
    Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();
    try {
        String sql = "select * from Message";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
            messages.add(message);
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return messages;
  }

  public List<Message> getMessagesByAccountID(int id){
    Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();
    try {
        String sql = "select * from Message where posted_by = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
            messages.add(message);
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return messages;
  }

  public Message getMessageByID(int id){
      Connection connection = ConnectionUtil.getConnection();
      try {
          String sql = "select * from Message where message_id = ?";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setInt(1, id);
          ResultSet rs = preparedStatement.executeQuery();
          while(rs.next()){
              Message message = new Message(rs.getInt("message_id"),
                  rs.getInt("posted_by"),
                  rs.getString("message_text"),
                  rs.getLong("time_posted_epoch"));
              return message;
          }
      }catch(SQLException e){
          System.out.println(e.getMessage());
      }
      return null;
  }

  public Message insertMessage(Message message){
      Connection connection = ConnectionUtil.getConnection();
      try {
        String sql = "insert into Message (posted_by, message_text, time_posted_epoch) values (?, ?, ?)" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, message.getPosted_by());
        preparedStatement.setString(2, message.getMessage_text());
        preparedStatement.setLong(3, message.getTime_posted_epoch());
        preparedStatement.executeUpdate();
        sql = "select message_id from Message";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        int id = -1;
        while(rs.next()) {
            id = rs.getInt("message_id");
        }
        return new Message(id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
      }catch(SQLException e){
          System.out.println(e.getMessage());
      }
      return null;
  }

  public int deleteMessageByID(int id){
    Connection connection = ConnectionUtil.getConnection();
    try {
        String sql = "delete from Message where message_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return -1;
  }

  public Message updateMessageByID(Message message){
    Connection connection = ConnectionUtil.getConnection();
    try {
        String sql = "update Message set message_text = ? where message_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, message.getMessage_text());
        preparedStatement.setInt(2, message.getMessage_id());
        preparedStatement.executeUpdate();
        return message;
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
  }

}
