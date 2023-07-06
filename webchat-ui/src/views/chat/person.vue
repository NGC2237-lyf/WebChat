<template>
  <div class="person" :style="{ right: currentRight }">
    <p>在线人数</p>
    <div class="persons">
      <div v-for="item in store.state.onlinePeople">
        <img :src="item.photo" />
        <p>{{ item.nickName }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "person",
  setup() {
    let store = useStore();
    let currentRight = computed(() => {
      return store.state.isShow.onlinePerson ? "0%" : "-350px";
    });
    return {
      store,
      currentRight,
    };
  },
};
</script>

<style scoped>
.person {
  width: 300px;
  position: sticky;
  transition: all 1s;
  top: 0;
  padding: 5px 10px;
  z-index: 2;
  background-color: white;
}
.person p {
  margin: 0;
  margin-bottom: 8px;
  font-size: 15px;
  text-align: center;
  text-overflow: ellipsis;
}
.person img {
  width: 47px;
  height: 47px;
  display: block;
  margin: 0 auto;
}
.persons {
  display: flex;
  flex-wrap: wrap;
}
.persons div {
  width: 65px;
  height: 65px;
  margin: 0 10px;
}
</style>