<template>
  <div class="friends">
    <Search />
    <Info v-if="store.state.isShow.personInfo" />
    <Simple v-else />
  </div>
</template>

<script>
import { ref, reactive, onMounted, computed } from "vue";
import Search from "./search.vue";
import Simple from "./simple.vue";
import Info from "./info.vue";
import Friends from "@/api/friends/index.js";
import { useStore } from "vuex";
export default {
  name: "firends",
  components: {
    Search,
    Simple,
    Info,
  },
  setup() {
    let store = useStore();
    let friend = new Friends();
    onMounted(() => {
      friend.getFriendsList(store.state.info.id).then((res) => {
        if (res.data !== null) {
          let data = res.data;
          let guys = data.guys.filter((item) => {
            item.photo = `data:image/png;base64,${item.photo}`;
            return item.id !== store.state.info.id;
          });
          store.state.friends = [];
          console.log(guys);
          store.state.friends.splice(store.state.friends.length, 0, ...guys);
        }
      });
    });
    return {
      store,
    };
  },
};
</script>

<style scoped>
.friends {
  width: 18%;
  position: relative;
  border-right: 1px solid grey;
}
</style>