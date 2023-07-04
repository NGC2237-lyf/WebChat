package tyut.homework.webchat.information.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author TokisakiKurumi
 * @className MessageType
 * @date 2023/6/28
 * @description
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@NoArgsConstructor
public enum MessageType {
    //点对点             //广播
    SINGLE("single"), BROADCAST("broadcast");
    private String description;

    MessageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonCreator
    public static MessageType fromValue(String value) {
        for (MessageType type : MessageType.values()) {
            if (type.description.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw  new IllegalArgumentException("Invalid messageType value: " + value);
    }

    @JsonValue
    public String toValue() {
        return description;
    }
}
