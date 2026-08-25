// package DAO;

// import Model.Message;
// import Util.ConnectionUtil;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// public class MessageDAO {

//   public List<Message> getAllMessages(){
//     Connection connection = ConnectionUtil.getConnection();
//     List<Message> messages = new ArrayList<>();
//     try {
//         String sql = "select * from Message";
//         PreparedStatement preparedStatement = connection.prepareStatement(sql);
//         ResultSet rs = preparedStatement.executeQuery();
//         while(rs.next()){
//             Message message = new Message(rs.getInt("message_id"),
//                     rs.getInt("author_id"),
//                     rs.getString("title"),
//                     rs.getInt("copies_available"));
//             messages.add(message);
//         }
//     }catch(SQLException e){
//         System.out.println(e.getMessage());
//     }
//     return messages;
//   }

//   public Message getMessageByIsbn(int isbn){
//       Connection connection = ConnectionUtil.getConnection();
//       try {
//           //Write SQL logic here
//           String sql = "select * from Message where isbn = ?";
//           PreparedStatement preparedStatement = connection.prepareStatement(sql);
//           preparedStatement.setInt(1, isbn);
//           //write preparedStatement's setInt method here.

//           ResultSet rs = preparedStatement.executeQuery();
//           while(rs.next()){
//               Message message = new Message(rs.getInt("isbn"),
//                       rs.getInt("author_id"),
//                       rs.getString("title"),
//                       rs.getInt("copies_available"));
//               return message;
//           }
//       }catch(SQLException e){
//           System.out.println(e.getMessage());
//       }
//       return null;
//   }

//   public Message insertMessage(Message message){
//       Connection connection = ConnectionUtil.getConnection();
//       try {
//           String sql = "insert into Message (isbn, author_id, title, copies_available) values (?, ?, ?, ?)" ;
//           PreparedStatement preparedStatement = connection.prepareStatement(sql);
//           preparedStatement.setInt(1, message.getIsbn());
//           preparedStatement.setInt(2, message.getAuthor_id());
//           preparedStatement.setString(3, message.getTitle());
//           preparedStatement.setInt(4, message.getCopies_available());
//           preparedStatement.executeUpdate();
//           return message;
//       }catch(SQLException e){
//           System.out.println(e.getMessage());
//       }
//       return null;
//   }

//   public List<Message> getMessagesWithMessageCountOverZero(){
//       Connection connection = ConnectionUtil.getConnection();
//       List<Message> messages = new ArrayList<>();
//       try {
//           //Write SQL logic here
//           String sql = "select * from Message where copies_available > 0";
//           PreparedStatement preparedStatement = connection.prepareStatement(sql);

//           ResultSet rs = preparedStatement.executeQuery();
//           while(rs.next()){
//               Message message = new Message(rs.getInt("isbn"),
//                       rs.getInt("author_id"),
//                       rs.getString("title"),
//                       rs.getInt("copies_available"));
//               messages.add(message);
//           }
//       }catch(SQLException e){
//           System.out.println(e.getMessage());
//       }
//       return messages;
//   }

// }
