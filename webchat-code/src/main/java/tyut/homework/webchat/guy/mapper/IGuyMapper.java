package tyut.homework.webchat.guy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tyut.homework.webchat.guy.domain.User;

import java.util.List;

@Repository
public interface IGuyMapper {
    List<User> guyList(@Param("name") String name);

    User getUserByNickName(@Param("nickName")String nickName);

    User findUserByEmail(@Param("email")String email);

    int insertDataByEmail(@Param("user")User user);
}
