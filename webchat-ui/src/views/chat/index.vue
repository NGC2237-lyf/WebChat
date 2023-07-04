<template>
  <div class="chat">
    <Screen :message="message" />
    <ChatInput @sendMessage="sendMessage" />
  </div>
</template>

<script>
import ChatInput from "./chatInput.vue";
import Screen from "./screen.vue";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "chat",
  components: {
    ChatInput,
    Screen,
  },
  setup() {
    let message = reactive([]);
    function sendMessage(obj) {
      console.log(obj);
      message.splice(message.length, 0, obj);
      console.log(message);
    }
    return {
      message,
      sendMessage,
    };
  },
};
</script>

<style scoped>
.chat {
  width: 70%;
}
</style>