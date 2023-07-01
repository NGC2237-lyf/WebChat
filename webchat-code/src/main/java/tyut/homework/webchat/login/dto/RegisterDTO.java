package tyut.homework.webchat.login.dto;

import lombok.Data;
import lombok.NonNull;
import tyut.homework.webchat.guy.domain.User;

@Data
public class RegisterDTO {
    @NonNull
    private String nickName;

    @NonNull
    private String password;

    @NonNull
    private String sex;

    @NonNull
    private String email;

    public User transformToUser(){
        return new User(this.getNickName(), this.getPassword(),this.getEmail());
    }
}
