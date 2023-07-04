<template>
  <div :class="[isMY === true ? 'message' : 'message1']">
    <!-- 头像 -->
    <img src="@/assets/avatar/1.jpg" />
    <div class="info">
      <p>{{ info.name }}</p>
      <p>{{ info.text }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "message",
  props: ["isMY", "info"],
};
</script>

<style scoped>
.message {
  display: flex;
  padding: 5px;
}
.message1 {
  display: flex;
  flex-direction: row-reverse;
  padding: 5px;
}
.message img,
.message1 img {
  width: 47px;
  height: 47px;
}
.info {
  padding: 0 5px;
}
.info p {
  padding: 0;
  margin: 5px 0;
  font-size: 15px;
}
</style>