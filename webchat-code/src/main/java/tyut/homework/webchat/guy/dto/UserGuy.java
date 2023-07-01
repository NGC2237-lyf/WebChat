package tyut.homework.webchat.guy.dto;

import lombok.Data;
import tyut.homework.webchat.common.domain.User;

import java.util.List;

@Data
public class UserGuy {
    private Integer myId;
    private Integer guyId;
    private String remark;
    private List<User> guys;
}
