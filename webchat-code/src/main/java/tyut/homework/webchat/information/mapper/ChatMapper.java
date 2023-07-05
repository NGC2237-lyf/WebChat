package tyut.homework.webchat.information.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tyut.homework.webchat.information.entity.Message;

import java.util.List;

/**
 * @author TokisakiKurumi
 * @className ChatMapper
 * @date 2023/7/1
 * @description
 **/
@Mapper
public interface ChatMapper {

    void insertRecord(@Param("message") Message message);

    List<Object> getRecord(@Param("start") int start, @Param("end") int end);
}
