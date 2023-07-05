package tyut.homework.webchat.login.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String verifyCode;

    public LoginDTO(@NonNull String email, @NonNull String password, @NonNull String verifyCode) {
        this.email = email;
        this.password = password;
        this.verifyCode = verifyCode;
    }

    public LoginDTO() {
    }
}
