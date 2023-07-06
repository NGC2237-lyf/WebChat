<template>
  <div class="title">
    <p>{{ store.state.currentChat }}({{ store.state.onlinePerson }})</p>
    <svg
      t="1688605385641"
      class="icon"
      viewBox="0 0 1024 1024"
      version="1.1"
      xmlns="http://www.w3.org/2000/svg"
      p-id="2302"
      width="30"
      height="30"
      @click="revealPerson"
    >
      <path
        d="M288 512m-64 0a64 64 0 1 0 128 0 64 64 0 1 0-128 0Z"
        fill="#272636"
        p-id="2303"
      ></path>
      <path
        d="M512 512m-64 0a64 64 0 1 0 128 0 64 64 0 1 0-128 0Z"
        fill="#272636"
        p-id="2304"
      ></path>
      <path
        d="M736 512m-64 0a64 64 0 1 0 128 0 64 64 0 1 0-128 0Z"
        fill="#272636"
        p-id="2305"
      ></path>
    </svg>
  </div>
</template>

<script>
import { useStore } from "vuex";
export default {
  name: "title",
  setup() {
    let store = useStore();
    function revealPerson() {
      store.state.isShow.onlinePerson = !store.state.isShow.onlinePerson;
    }
    return {
      store,
      revealPerson,
    };
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
.title {
  width: 100%;
  height: 5%;
  font-size: 23px;
  padding: 10px;
  padding-right: 0;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid rgba(128, 128, 128, 0.208);
  display: flex;
}
.icon {
  cursor: pointer;
}
</style>