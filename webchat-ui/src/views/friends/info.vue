<template>
  <div class="friendinfo">
    <div v-if="store.state.search.id">
      <div class="info">
        <img :src="store.state.search.photo" />
        <p>{{ store.state.search.nickName }}</p>
      </div>
      <div class="buttons">
        <button class="cbutton" @click="addFriends">添加</button>
        <button class="cbutton" @click="cancelSearch">取消</button>
      </div>
    </div>
    <div class="add" v-else>
      <p>添加好友</p>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref, reactive, onMounted, computed } from "vue";
import Friends from "@/api/friends/index.js";
export default {
  name: "firendInformations",
  setup() {
    let friend = new Friends();

    let store = useStore();
    let info = ref(false);
    function addFriends() {
      friend
        .addFriend(store.state.info.id, store.state.search.id, "")
        .then((res) => {
          alert(res.msg);
          if (res.code === 200) {
            friend.getFriendsList(store.state.info.id).then((res) => {
              if (res.data !== null) {
                let data = res.data;
                let guys = data.guys.filter((item) => {
                  item.photo = `data:image/png;base64,${item.photo}`;
                  return item.id !== store.state.info.id;
                });
                // store.state.friends=
                store.state.friends = [];
                store.state.friends.splice(
                  store.state.friends.length,
                  0,
                  ...guys
                );
              }
            });
          }
        });
    }
    function cancelSearch() {
      store.state.isShow.personInfo = false;
    }
    return {
      store,
      info,
      addFriends,
      cancelSearch,
    };
  },
};
</script>

<style scoped>
.friendinfo {
  width: 95%;
  height: 100px;
  background-color: transparent;
  margin: 0 auto;
  left: 0;
  right: 0;
  position: absolute;
  z-index: 5;
}
.friendinfo img {
  width: 47px;
  height: 47px;
}
.buttons {
  display: flex;
  justify-content: space-around;
}
.cbutton {
  background-color: rgba(135, 207, 235, 0.62);
  width: 80px;
  height: 30px;
  padding: 5px;
  cursor: pointer;
  border: none;
}
.add {
  width: 100%;
  padding: 5px 0;
  background-color: rgba(165, 42, 42, 0.117);
}
.info {
  margin: 10px;
  display: flex;
  justify-content: space-around;
}
</style>