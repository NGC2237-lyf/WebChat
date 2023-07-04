<template>
  <div class="main">
    <My />
    <Firends />
    <Chat />
  </div>
</template>

<script>
import My from "@/views/my/index.vue";
import Chat from "@/views/chat/index.vue";
import Firends from "@/views/firends/index.vue";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "main",
  components: {
    Chat,
    Firends,
    My,
  },
  setup() {
    onMounted(() => {});
  },
};
</script>

<style scoped>
.main {
  width: 80%;
  margin: 0 auto;
  background-color: aquamarine;
  height: 800px;
  display: flex;
}
</style>