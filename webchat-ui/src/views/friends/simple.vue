<template>
  <div class="simple-box">
    <RightMenu
      v-if="store.state.isShow.rightmenu"
      :position="position"
      :id="selectId"
    />
    <div class="simple" @click="switchChatRoom('聊天室', store.state.info.id)">
      <svg
        t="1688660571637"
        class="icon"
        viewBox="0 0 1024 1024"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        p-id="2399"
        width="47"
        height="47"
      >
        <path
          d="M296.832 672H832V256H192v503.36L296.832 672z m-137.344 197.76A19.2 19.2 0 0 1 128 855.04V256a64 64 0 0 1 64-64h640a64 64 0 0 1 64 64v416a64 64 0 0 1-64 64H320l-160.512 133.76z"
          fill="#000000"
          fill-opacity=".9"
          p-id="2400"
        ></path>
      </svg>
      <p>聊天室</p>
    </div>
    <div
      v-for="(item, index) in store.state.friends"
      class="simple"
      @contextmenu.prevent="showRightMenu($event, item.id)"
      @click="switchChatRoom(item.nickName, item.id)"
    >
      <img :src="item.photo" />
      <p>{{ item.nickName }}</p>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref, reactive, onMounted, computed } from "vue";
import RightMenu from "./rightmenu.vue";
export default {
  name: "simple",
  components: {
    RightMenu,
  },
  setup() {
    let store = useStore();

    let position = reactive({
      left: 0,
      top: 0,
    });

    let selectId = ref(0);

    function showRightMenu(e, id) {
      selectId.value = id;
      position.left = e.layerX;
      position.top = e.layerY;

      store.state.isShow.rightmenu = !store.state.isShow.rightmenu;
    }

    function switchChatRoom(name, id) {
      console.log(name, id);
      if (name !== store.state.currentChat.name) {
        console.log(store.state.currentChat);

        if (!store.state.chatRoom[0]) {
          store.state.chatRoom = store.state.messages;
          store.state.messages = [];
          console.log(1);
        } else if (name === "聊天室") {
          // console.log(store.state.private);
          store.state.private[store.state.currentChat.id] =
            store.state.messages;
          console.log(
            store.state.messages,
            store.state.private[store.state.currentChat.id]
          );
          store.state.messages = store.state.chatRoom;
          console.log(2);
        } else {
          // console.log(store.state.private);
          store.state.chatRoom = store.state.messages;
          store.state.messages = store.state.private[id];
          console.log(store.state.private[id]);
          console.log(3);
        }
        store.state.currentChat.name = name;

        store.state.currentChat.id = id;
      }
    }

    return {
      store,
      selectId,
      position,
      showRightMenu,
      switchChatRoom,
    };
  },
};
</script>

<style scoped>
.simple-box {
  position: relative;
  height: 90%;
  overflow-y: auto;
  overflow-x: hidden;
  max-height: 90%;
}

/* 定义滚动条宽高及背景，宽高分别对应横竖滚动条的尺寸 */
.simple-box::-webkit-scrollbar {
  width: 10px; /* 对垂直滚动条有效 */
}

/* 定义滚动条的轨道颜色、内阴影及圆角 */
.simple-box::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #ececec;
  border-radius: 5px;
}

/* 定义滑块颜色、内阴影及圆角 */
.simple-box::-webkit-scrollbar-thumb {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 0px rgba(0, 0, 0, 0.173);
  background-color: rgba(0, 0, 0, 0.1);
}

/* 光标移到滚动滑块上样式颜色变深 */
.simple-box::-webkit-scrollbar-thumb:hover {
  background-color: rgba(0, 0, 0, 0.2);
}

/* 定义两端按钮的样式 */
.simple-box::-webkit-scrollbar-button {
  background-color: cyan;
}

/* 定义右下角汇合处的样式 */
.simple-box::-webkit-scrollbar-corner {
  background: khaki;
}
.simple {
  width: 96%;
  display: flex;
  /* border: 1px solid; */
  /* box-shadow: 1px 1px 1px black; */
  align-items: center;
  padding: 2%;
  margin: 0 auto;
  cursor: pointer;
}
.simple img {
  width: 45px;
  height: 45px;
  object-fit: cover;
}
.simple p {
  margin-left: 15px;
}
</style>