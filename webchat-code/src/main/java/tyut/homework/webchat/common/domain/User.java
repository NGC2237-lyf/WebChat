package tyut.homework.webchat.common.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String nickName;
    private String password;
    private String email;
    private byte[] photo;
}
