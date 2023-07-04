<template>
  <div class="friends">
    <Simple />
    <Info />
  </div>
</template>

<script>
import Simple from "./simple.vue";
import Info from "./info.vue";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "firends",
  components: {
    Simple,
    Info,
  },
};
</script>

<style scoped>
.friends {
  width: 20%;
}
</style>