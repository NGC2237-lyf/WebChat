<template>
  <div class="screen">
    <person></person>
    <p @click="getChatHistory" class="history">查看历史记录</p>
    <Message
      v-for="item in messages"
      :text="item.info"
      :isMY="store.state.info.id == item.userId"
      :id="item.userId"
    />
  </div>
</template>

<script>
import { useStore } from "vuex";
import getHistory from "@/api/chat/index.js";
import Message from "./message.vue";
import { ref, reactive, onMounted, computed } from "vue";
import Person from "./person.vue";
export default {
  name: "screen",
  components: {
    Message,
    Person,
  },
  setup() {
    let store = useStore();
    let messages = store.state.messages;
    let start = ref(0);
    let step = ref(10);
    function getWords() {}
    function generateWords() {}
    function getChatHistory() {
      getHistory(store.state.info.id, start.value, step.value).then((res) => {
        if (res.code === 200) {
          store.state.messages.unshift(...res.data);
        }
        start.value += step.value;
      });
    }
    onMounted(() => {
      setTimeout(() => {
        getChatHistory();
      }, 200);
    });
    return {
      store,
      messages,
      getWords,
      getChatHistory,
      generateWords,
    };
  },
};
</script>

<style>
.screen {
  width: 100%;
  height: 500px;
  height: 65%;
  padding-left: 1%;
  margin: 0 auto;
  overflow-y: auto;
  overflow-x: hidden;
  position: relative;
}
/* 定义滚动条宽高及背景，宽高分别对应横竖滚动条的尺寸 */
.screen::-webkit-scrollbar {
  width: 10px; /* 对垂直滚动条有效 */
}

/* 定义滚动条的轨道颜色、内阴影及圆角 */
.screen::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
}

/* 定义滑块颜色、内阴影及圆角 */
.screen::-webkit-scrollbar-thumb {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 0px rgba(0, 0, 0, 0.173);
  background-color: rgba(0, 0, 0, 0.1);
}

/* 光标移到滚动滑块上样式颜色变深 */
.screen::-webkit-scrollbar-thumb:hover {
  background-color: rgba(0, 0, 0, 0.2);
}

/* 定义两端按钮的样式 */
.screen::-webkit-scrollbar-button {
  background-color: cyan;
}

/* 定义右下角汇合处的样式 */
.screen::-webkit-scrollbar-corner {
  background: khaki;
}
.history {
  text-align: center;
  color: blue;
  cursor: pointer;
}
</style>