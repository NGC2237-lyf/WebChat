<template>
  <div :class="[isMY === true ? 'message1' : 'message2']">
    <!-- 头像 -->
    <img :src="avatar" />
    <div class="info">
      <p>{{ name }}</p>
      <div class="download-box">
        <div class="downloadicon">
          <svg
            t="1688656494411"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="2324"
            width="40"
            height="40"
          >
            <path
              d="M682.666667 245.333333a10.666667 10.666667 0 0 0 10.666666 10.666667h189.913334c-0.913333-1.066667-1.86-2.12-2.866667-3.126667L685.793333 58.286667c-1.006667-1.006667-2.06-1.953333-3.126666-2.866667z"
              fill="#5C5C66"
              p-id="2325"
            ></path>
            <path
              d="M640 245.333333V42.666667H181.333333a53.393333 53.393333 0 0 0-53.333333 53.333333v832a53.393333 53.393333 0 0 0 53.333333 53.333333h661.333334a53.393333 53.393333 0 0 0 53.333333-53.333333V298.666667h-202.666667a53.393333 53.393333 0 0 1-53.333333-53.333334z m-320 10.666667h170.666667a21.333333 21.333333 0 0 1 0 42.666667H320a21.333333 21.333333 0 0 1 0-42.666667z m384 512H320a21.333333 21.333333 0 0 1 0-42.666667h384a21.333333 21.333333 0 0 1 0 42.666667z m21.333333-234.666667a21.333333 21.333333 0 0 1-21.333333 21.333334H320a21.333333 21.333333 0 0 1 0-42.666667h384a21.333333 21.333333 0 0 1 21.333333 21.333333z"
              fill="#5C5C66"
              p-id="2326"
            ></path>
          </svg>
          <p>{{ fileName }}</p>
        </div>
        <div class="download" @click="download">
          <a>下载</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import Friends from "@/api/friends/index.js";
import downloadFile from "@/api/chat/download.js";
import downloadBase64 from "@/api/extendsion/download.js";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "message",
  props: ["isMY", "fileName", "id", "fileType"],
  setup(props) {
    let store = useStore();
    let info = {};
    let friend = new Friends();
    let avatar = ref("");
    let name = ref("");
    function findInfo() {
      info = store.state.onlinePeople.find((item) => {
        return item.id == props.id;
      });
      info = store.state.inflinePeople.find((item) => {
        return item.id == props.id;
      });
      //如果为空，查找信息
      if (info === undefined) {
        friend.findFriend(props.id).then((res) => {
          if (res.data != "null") {
            let data = JSON.parse(res.data)[0];
            console.log(data);
            let obj = {
              id: data.id,
              nickName: data.nickName,
              email: data.email,
              photo: `data:image/webp;base64,${data.photo}`,
            };
            store.state.inflinePeople[store.state.inflinePeople.length] = obj;
            info =
              store.state.inflinePeople[store.state.inflinePeople.length - 1];
            console.log(info);
            avatar.value = info.photo;
            name.value = info.nickName;
          }
        });
      } else {
        avatar.value = info.photo;
        name.value = info.nickName;
      }
    }

    function download() {
      let type =
        store.state.currentChat.name === "聊天室" ? "broadcast" : "single";
      downloadFile(type, props.id, props.fileName).then((res) => {
        downloadBase64(res.data, props.fileType, props.fileName);
      });
    }

    onMounted(() => {
      findInfo();
    });
    return {
      info,
      store,
      name,
      avatar,
      download,
    };
  },
};
</script>

<style scoped>
.message2 {
  display: flex;
  padding: 8px;
}
.message1 {
  display: flex;
  flex-direction: row-reverse;
  padding: 8px;
}
.message2 img,
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
#mes1 {
  padding: 8px 10px;
  background-color: rgb(125, 191, 249);
  border-radius: 5px;
}
#mes2 {
  padding: 8px 10px;
  background-color: rgb(255, 255, 255);
  border-radius: 5px;
}
.download-box {
  width: 200px;
  height: 75px;

  border: 1px solid rgba(128, 128, 128, 0.438);
  background-color: white;
}
.download-box p {
  display: inline-block;
}
.downloadicon {
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgba(128, 128, 128, 0.438);
}
.downloadicon svg {
  padding: 5px;
}
.download {
  font-size: 15px;
  cursor: pointer;
  color: rgba(0, 0, 255, 0.645);
  width: 100%;
}
.download a {
  padding: 5px 10px;
}
</style>