package pl.sobczak.rebel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.sobczak.rebel.utils.DataFaker;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String email;
    private String password;
    private String repeatedPassword;

    public static User createFakeUser() {
        String username = DataFaker.getFakeUsername();
        String email = DataFaker.getFakeEmail();
        String password = DataFaker.getFakePassword();

        return new User(username, email, password, password);
    }
}
