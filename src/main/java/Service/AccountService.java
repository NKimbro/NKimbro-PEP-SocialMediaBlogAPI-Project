package Service;

import java.util.List;
import Model.Account;
import DAO.AccountDAO;

public class AccountService {
  private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }
  
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
    
    public Account addAccount(Account account) {
      if (account.getUsername().equals("") || account.getPassword().length() < 4 || accountDAO.getAccountByUsername(account.getUsername()) != null)
        return null;
      return accountDAO.insertAccount(account);
    }

}
