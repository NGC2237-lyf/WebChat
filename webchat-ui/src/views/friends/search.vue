<template>
  <div class="search">
    <input
      type="text"
      v-model="word"
      placeholder="搜索"
      @keydown.enter="searchFriend"
      @focus="appear"
      @blur="iAppear"
    />
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref, reactive, onMounted, computed } from "vue";
import Friends from "@/api/friends/index.js";
export default {
  name: "search",
  setup() {
    let store = useStore();
    let word = ref("");

    let friends = new Friends();
    function searchFriend() {
      friends.findFriend(word.value).then((res) => {
        if (res.data != "null") {
          let data = JSON.parse(res.data)[0];
          store.state.search.id = data.id;
          store.state.search.nickName = data.nickName;
          store.state.search.email = data.email;
          store.state.search.photo = `data:image/webp;base64,${data.photo}`;
        } else {
          alert("未查找到信息");
        }
      });
    }

    function appear() {
      store.state.isShow.personInfo = true;
    }

    function iAppear() {
      if (!store.state.search.id) {
        store.state.isShow.personInfo = false;
      }
    }

    return {
      word,
      appear,
      iAppear,
      searchFriend,
    };
  },
};
</script>

<style scoped>
.search {
  width: 90%;
  height: 50px;
  margin: 0 auto;
  padding: 10px 10px;
  border-bottom: 1px solid grey;
}
.search input {
  width: 95%;
  padding: 1%;
  height: 20px;
  border: none;
  background-color: transparent;
  border: 1px solid black;
}
</style>