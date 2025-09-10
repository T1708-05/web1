package murach.data;

import murach.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDB {
  private static final List<User> USERS = new ArrayList<>();

  public static void insert(User u) {
    if (u != null) USERS.add(u);
  }

  public static List<User> all() {
    return new ArrayList<>(USERS);
  }
}
