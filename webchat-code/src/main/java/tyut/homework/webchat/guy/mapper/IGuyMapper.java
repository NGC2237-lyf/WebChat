package tyut.homework.webchat.guy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuy;

import java.util.List;

@Repository
public interface IGuyMapper {
    UserGuy guyList(@Param("name") String name);

    List<User> guySearch(User user);
}
