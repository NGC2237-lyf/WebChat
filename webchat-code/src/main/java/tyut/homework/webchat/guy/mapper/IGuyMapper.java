package tyut.homework.webchat.guy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.UserGuy;

import java.util.List;

@Mapper
public interface IGuyMapper {
    List<User> guyList(@Param("name") String name);

    User getUserByEmail(@Param("email")String email);

    User findUserByEmail(@Param("email")String email);

    int insertDataByEmail(@Param("user")User user);
    /**
     * 根据昵称查询好友列表
     *
     * @param account
     * @return
     */
    UserGuy guyList(@Param("id")int account);

    /**
     * 搜索好友
     *
     * @param user
     * @return
     */
    List<User> guySearch(@Param("user") User user);

    /**
     * 删除好友
     *
     * @param myId
     * @param guyId
     */
    void guyDelete(@Param("myId") int myId, @Param("guyId") int guyId);

    /**
     * 增添好友
     *
     * @param userGuy
     */
    void guyAdd(@Param("userGuy") UserGuy userGuy);

    /**
     * 修改好友昵称
     *
     * @param remark
     * @param userGuy
     */
    void guyRemarkUpdate(@Param("remark") String remark, @Param("userGuy") UserGuy userGuy);

    /**
     * 查询好友信息
     *
     * @param userGuy
     * @return
     */
    User guyInfo(@Param("userGuy") UserGuy userGuy);


    List<User> addMessage(@Param("account") int account);
}
