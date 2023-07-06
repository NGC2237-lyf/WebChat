<template>
  <div class="simple-box">
    <RightMenu
      v-if="store.state.isShow.rightmenu"
      :position="position"
      :id="selectId"
    />
    <div
      v-for="(item, index) in store.state.friends"
      class="simple"
      @contextmenu.prevent="showRightMenu($event, item.id)"
      @click="switchChatRoom(item.nickName)"
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

    function switchChatRoom(name) {
      store.state.currentChat = name;
    }

    return {
      store,
      selectId,
      position,
      showRightMenu,
    };
  },
};
</script>

<style scoped>
.simple-box {
  position: relative;
  overflow-y: auto;
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