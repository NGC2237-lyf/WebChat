package tyut.homework.webchat.guy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tyut.homework.webchat.common.domain.User;
import tyut.homework.webchat.guy.dto.MessageDTO;
import tyut.homework.webchat.guy.dto.UserGuyDTO;

import java.util.List;

@Mapper
public interface IGuyMapper {
    List<User> guyList(@Param("name") String name);

    User getUserByEmail(@Param("email")String email);

    User getUserById(@Param("id")String id);

    int insertDataByEmail(@Param("user")User user);
    /**
     * 根据昵称查询好友列表
     *
     * @param account
     * @return
     */
    UserGuyDTO guyList(@Param("id") int account);

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
    void guyAdd(@Param("userGuy") UserGuyDTO userGuy);

    /**
     * 修改好友昵称
     *
     * @param remark
     * @param userGuy
     */
    void guyRemarkUpdate(@Param("remark") String remark, @Param("userGuy") UserGuyDTO userGuy);

    /**
     * 查询好友信息
     *
     * @param userGuy
     * @return
     */
    User guyInfo(@Param("userGuy") UserGuyDTO userGuy);


    void addMessage(@Param("myId") int myId,@Param("toId")int toId);

    MessageDTO getMessage(@Param("myId") int myId,@Param("toId")int toId);

    void updateMessage(@Param("myId") int myId,@Param("toId")int toId,@Param("flag")String flag);
}
