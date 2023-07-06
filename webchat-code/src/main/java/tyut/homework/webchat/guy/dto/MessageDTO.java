package tyut.homework.webchat.guy.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private Integer myId;
    private Integer toId;
    private String flag;
}
