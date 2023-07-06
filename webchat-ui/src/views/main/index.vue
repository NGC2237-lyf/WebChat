<template>
  <div class="main">
    <My />
    <Friends />
    <Chat />
  </div>
</template>

<script>
import My from "@/views/my/index.vue";
import Chat from "@/views/chat/index.vue";
import Friends from "@/views/friends/index.vue";
import { useRouter } from "vue-router";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "main",
  components: {
    Chat,
    Friends,
    My,
  },
  setup() {
    let router = new useRouter();
    let info = reactive({
      id: "",
      photo: "",
    });
    return { info };
  },
};
</script>

<style scoped>
.main {
  width: 80%;
  min-width: 1000px;
  margin: 0 auto;
  background-color: aquamarine;
  height: 800px;
  box-shadow: 5px 0px 3px #888888;
  display: flex;
}
</style>