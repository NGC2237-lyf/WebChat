package tyut.homework.webchat.guy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tyut.homework.webchat.common.domain.User;

import java.util.List;

@Data
@NoArgsConstructor
public class UserGuyDTO {
    private Integer myId;
    private Integer guyId;
    private String remark;
    private List<User> guys;

    public UserGuyDTO(Integer myId, Integer guyId) {
        this.myId = myId;
        this.guyId = guyId;
    }
}
