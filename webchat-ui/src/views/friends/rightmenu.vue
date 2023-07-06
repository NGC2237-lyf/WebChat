<template>
  <div
    class="rightmenu"
    :style="{
      top: position.top + 'px',
      left: position.left + 'px',
    }"
  >
    <p @click="deleteFriend">删除</p>
    <!-- <p @click="remarkFriend">修改备注</p> -->
    <p @click="remarkFriend">查看信息</p>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref, reactive, onMounted, computed } from "vue";
import Friend from "@/api/friends/index.js";
export default {
  name: "rightmenu",
  props: ["position", "id"],
  setup(props, context) {
    let store = useStore();
    let friend = new Friend();
    function deleteFriend() {
      console.log(props);
      friend.deleteFriend(store.state.info.id, props.id).then((res) => {
        console.log(res);
      });
    }
    function remarkFriend() {
      friend.modifyFriend(store.state.info.id, props.id, "666").then((res) => {
        console.log(res);
      });
    }
    return {
      deleteFriend,
      remarkFriend,
    };
  },
};
</script>

<style scoped>
.rightmenu {
  width: 100px;
  position: absolute;
  z-index: 2;
  background-color: rgba(169, 223, 225, 0.707);
}
.rightmenu p {
  padding: 10px 0;
  margin: 0;
}
.rightmenu p:hover {
  background-color: rgb(48, 85, 86);
}
</style>