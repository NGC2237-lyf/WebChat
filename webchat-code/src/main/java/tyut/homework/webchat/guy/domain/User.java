package tyut.homework.webchat.guy.domain;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String nickName;
    private String password;
    private String email;
    private byte[] photo;
    private List<User> guyList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
