package tyut.homework.webchat.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author TokisakiKurumi
 * @className Message
 * @date 2023/6/28
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private MessageType messageType;
    private String userId;
    private String oppositeId;
    private DataType dataType;
    private String fileName;
    private String info;
    private String dateTime;
}

