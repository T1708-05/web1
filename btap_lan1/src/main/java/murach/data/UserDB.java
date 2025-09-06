package murach.data;

import murach.business.User;

public class UserDB {
    public static void insert(User user) {
        // demo: chưa kết nối DB thật
        System.out.println("Saving user: " + user.getEmail());
    }
}
