<template>
  <!-- <login></login> -->
  <div @click="closePop">
    <router-view></router-view>
  </div>
</template>

<script>
import { useStore } from "vuex";
import Login from "./components/login.vue";
export default {
  name: "App",
  components: {
    Login,
  },
  setup() {
    let store = useStore();
    function closePop() {
      if (store.state.isShow.rightmenu === true) {
        store.state.isShow.rightmenu = !store.state.isShow.rightmenu;
      }
    }
    return {
      closePop,
    };
  },
};
</script>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
</style>
