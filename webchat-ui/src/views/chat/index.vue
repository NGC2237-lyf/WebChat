<template>
  <div class="chat">
    <Title />
    <Screen />
    <ChatInput />
  </div>
</template>

<script>
import ChatInput from "./chatInput.vue";
import Screen from "./screen.vue";
import Title from "./title.vue";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "chat",
  components: {
    ChatInput,
    Screen,
    Title,
  },
  setup() {
    let message = reactive([]);
    let info = reactive({});
    return {
      message,
    };
  },
};
</script>

<style scoped>
.chat {
  width: 73%;
  padding: 0;
}
</style>