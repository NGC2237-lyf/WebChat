package tyut.homework.webchat.login.dto;

import lombok.Data;
import tyut.homework.webchat.common.domain.User;

@Data
public class RegisterDTO {

    private String nickName;

//    @NonNull
    private String password;

//    @NonNull
    private String code;

//    @NonNull
    private String email;

    public User transformToUser(){
        return new User(this.getNickName(), this.getPassword(),this.getEmail());
    }

    public RegisterDTO(String nickName, String password, String code, String email) {
        this.nickName = nickName;
        this.password = password;
        this.code = code;
        this.email = email;
    }
}
