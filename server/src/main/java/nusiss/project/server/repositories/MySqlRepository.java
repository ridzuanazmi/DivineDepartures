package nusiss.project.server.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nusiss.project.server.models.UserDto;
import nusiss.project.server.models.user.Role;
import nusiss.project.server.models.user.User;

@Repository
public class MySqlRepository {

  private final JdbcTemplate jdbcTemplate;

  public MySqlRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // MYSQL Queries
  public static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT user_id, full_name, phone_number, email, password, role, created_date FROM _user";
  public static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT user_id, full_name, phone_number, email, password, role, created_date FROM _user WHERE user_id = ?";
  public static final String SQL_DELETE_ACCOUNT = "DELETE FROM _user WHERE user_id = ?";
  public static final String SQL_UPDATE_ACCOUNT_BY_ID = "UPDATE _user SET full_name = ?, email = ?, phone_number = ?, role = ? WHERE user_id = ?";

  public List<User> getAllAccounts() {
    List<User> accounts = new LinkedList<>();
    SqlRowSet rs = null;
    rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_ACCOUNTS);
    while (rs.next()) {
      setUser(accounts, rs);
    }
    return accounts;
  }

  public Optional<User> getAccountById(String id) {
    User account = null;
    SqlRowSet rs = null;
    rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ACCOUNT_BY_ID, id);
    while (rs.next()) {
      account = new User();
      account.setUserId(rs.getInt("user_id"));
      account.setFullName(rs.getString("full_name"));
      account.setPhoneNumber(rs.getString("phone_number"));
      account.setEmail(rs.getString("email"));
      account.setPassword(rs.getString("password"));
      String role = rs.getString("role");
      account.setRole(role != null ? Role.valueOf(role.toUpperCase()) : null);
      account.setCreatedDate(rs.getDate("created_date"));
      // Add here other fields you want to retrieve.
    }

    if (null == account) {
      return Optional.empty();
    }

    return Optional.of(account);
  }

  public void deleteAccountById(int id) {
    jdbcTemplate.update(SQL_DELETE_ACCOUNT, id);
  }

  public void updateAccount(Integer id, UserDto user) throws Exception {
    try {
      System.out.println("\n User: "+ user);
      jdbcTemplate.update(SQL_UPDATE_ACCOUNT_BY_ID, user.getFullName(), user.getEmail(), user.getPhoneNumber(),
          user.getRole(), id);
    } catch (Exception e) {
      throw new Exception("Error updating user", e);
    }
  }

  // Helper methods
  private void setUser(List<User> accounts, SqlRowSet rs) {
    User user = new User();
    user.setUserId(rs.getInt("user_id"));
    user.setFullName(rs.getString("full_name"));
    user.setPhoneNumber(rs.getString("phone_number"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));
    String role = rs.getString("role");
    user.setRole(role != null ? Role.valueOf(role.toUpperCase()) : null);
    user.setCreatedDate(rs.getDate("created_date"));
    accounts.add(user);
  }
}
