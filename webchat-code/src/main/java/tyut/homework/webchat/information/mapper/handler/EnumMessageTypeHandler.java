package tyut.homework.webchat.information.mapper.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import tyut.homework.webchat.information.entity.MessageType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author TokisakiKurumi
 * @className EnumMessageTypeHandler
 * @date 2023/7/1
 * @description
 **/
public class EnumMessageTypeHandler extends BaseTypeHandler<MessageType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, MessageType messageType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,messageType.getDescription());
    }

    @Override
    public MessageType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String des = resultSet.getString(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateEnum(des);
        }
    }

    @Override
    public MessageType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String des = resultSet.getString(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateEnum(des);
        }
    }

    @Override
    public MessageType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String des = callableStatement.getString(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return locateEnum(des);
        }
    }

    private MessageType locateEnum(String des) {
        for (MessageType type : MessageType.values()) {
            if (des.equalsIgnoreCase(type.getDescription())) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + des);
    }
}
