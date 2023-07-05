package tyut.homework.webchat.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author TokisakiKurumi
 * @className ServerMessage
 * @date 2023/7/4
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerMessage {
    private MessageType messageType;
    private DataType dataType;
    private String info;
}
