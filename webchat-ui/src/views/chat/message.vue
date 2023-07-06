<template>
  <div :class="[isMY === true ? 'message1' : 'message2']">
    <!-- 头像 -->
    <img :src="avatar" />
    <div class="info">
      <p>{{ name }}</p>
      <p :id="[isMY === true ? 'mes1' : 'mes2']">{{ text }}</p>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import Friends from "@/api/friends/index.js";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "message",
  props: ["isMY", "text", "id"],
  setup(props) {
    let store = useStore();
    let info = {};
    let friend = new Friends();
    let avatar = ref("");
    let name = ref("");
    function findInfo() {
      info = store.state.onlinePeople.find((item) => {
        return item.id == props.id;
      });
      info = store.state.inflinePeople.find((item) => {
        return item.id == props.id;
      });
      console.log(info);
      //如果为空，查找信息
      if (info === undefined) {
        friend.findFriend(props.id).then((res) => {
          if (res.data != "null") {
            let data = JSON.parse(res.data)[0];
            console.log(data);
            let obj = {
              id: data.id,
              nickName: data.nickName,
              email: data.email,
              photo: `data:image/webp;base64,${data.photo}`,
            };
            store.state.inflinePeople[store.state.inflinePeople.length] = obj;
            info =
              store.state.inflinePeople[store.state.inflinePeople.length - 1];
            console.log(info);
            avatar.value = info.photo;
            name.value = info.nickName;
          }
        });
      }
    }

    onMounted(() => {
      findInfo();
    });
    return {
      info,
      store,
      name,
      avatar,
    };
  },
};
</script>

<style scoped>
.message2 {
  display: flex;
  padding: 8px;
}
.message1 {
  display: flex;
  flex-direction: row-reverse;
  padding: 8px;
}
.message2 img,
.message1 img {
  width: 47px;
  height: 47px;
}
.info {
  padding: 0 5px;
}
.info p {
  padding: 0;
  margin: 5px 0;
  font-size: 15px;
}
#mes1 {
  padding: 8px 10px;
  background-color: rgb(125, 191, 249);
  border-radius: 5px;
}
#mes2 {
  padding: 8px 10px;
  background-color: rgb(255, 255, 255);
  border-radius: 5px;
}
</style>