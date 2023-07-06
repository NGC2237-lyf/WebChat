<template>
  <div class="my">
    <img :src="avatar" alt="" />
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "my",
  setup() {
    let store = useStore();
    let avatar = ref(store.state.info.photo);
    return {
      avatar,
    };
  },
};
</script>

<style scoped>
.my {
  width: 8%;
  background-color: rgba(0, 0, 0, 0.699);
}
.my img {
  width: 47px;
  height: 47px;
  display: block;
  margin: 20px auto;
}
</style>