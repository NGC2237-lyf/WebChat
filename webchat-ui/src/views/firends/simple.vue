<template>
  <div v-for="(item, index) in names" class="simple">
    <img src="@/assets/avatar/1.jpg" />
    <p>{{ item }}</p>
  </div>
</template>

<script>
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "simple",
  setup() {
    let count = ref(0);
    let names = reactive(["异类小魔鬼", "瞅你咋地", "爱干嘛干嘛", "什么鬼"]);
    let name = computed(() => {
      return names[count % 4];
    });

    function icon(index) {
      // return index % 2 === 0
      //   ? "../../assets/avatar/1.jpg"
      //   : "../../assets/avatar/2.jpg";
    }

    return {
      names,
      name,
      icon,
    };
  },
};
</script>

<style scoped>
.simple {
  width: 100%;
  display: flex;
  /* border: 1px solid; */
  /* box-shadow: 1px 1px 1px black; */
  align-items: center;
  padding: 5px 0;
}
.simple img {
  width: 45px;
  height: 45px;
  object-fit: cover;
}
</style>