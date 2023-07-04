package tyut.homework.webchat.common.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String nickName;
    private String password;
    private String email;
    private byte[] photo;

    public User(String nickName, String password, String email) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public User(String nickName, String password, String email, byte[] photo) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.photo = photo;
    }

}
