package tyut.homework.webchat.login.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
public class LoginDTO {
    @NonNull
    private String eamil;

    @NonNull
    private String password;

    @NonNull
    private String verifyCode;
}
