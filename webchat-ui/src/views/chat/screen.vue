<template>
  <div class="screen">
    <Message v-for="item in message" :info="item" />
  </div>
</template>

<script>
import Message from "./message.vue";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "screen",
  props: ["message"],
  components: {
    Message,
  },
  setup() {
    let person = ref(0);
    let users = reactive([
      {
        id: 666,
        name: "",
        icon: "",
      },
    ]);
    let words = reactive(["你好", ""]);
    function getWords() {}
    function generateWords() {}
    return {
      getWords,
      generateWords,
    };
  },
};
</script>

<style>
.screen {
  width: 100%;
  height: 500px;
  height: 70%;
  border: 1px solid;
}
</style>