package tyut.homework.webchat.information.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NoArgsConstructor;

/**
 * @author TokisakiKurumi
 * @className DataType
 * @date 2023/6/28
 * @description
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@NoArgsConstructor
public enum DataType {
    //消息类型
    TEXT("text"),FILE("file"),JOIN("join"),Error("error");

    private String description;

    DataType(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static DataType fromValue(String value) {
        for (DataType type : DataType.values()) {
            if (type.description.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw  new IllegalArgumentException("Invalid dataType value: " + value);
    }

    @JsonValue
    public String toValue() {
        return description;
    }
}
