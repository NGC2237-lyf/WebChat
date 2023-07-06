<template>
  <div class="chatinput">
    <!-- 附加功能，表情，发送附件之类的 -->
    <div class="extend">
      <svg
        t="1687933295583"
        class="icon"
        viewBox="0 0 1024 1024"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        p-id="2305"
        width="20"
        height="20"
      >
        <path
          d="M945.424922 509.059018c0-240.652965-195.076954-435.727873-435.726849-435.727873-240.651942 0-435.727873 195.074907-435.727873 435.727873 0 240.662175 195.075931 435.737082 435.727873 435.737082 98.792027 0 189.248202-33.611515 262.339894-88.998987 3.003404-3.40761 4.975315-7.753591 4.975315-12.637832 0-10.666944-8.651031-19.315928-19.315928-19.315928-5.53404 0-10.464329 2.194992-13.981433 5.91471l-0.246617 0c-65.407686 48.12916-145.915277 76.945463-233.346559 76.945463-217.908962 0-394.587893-176.701444-394.587893-394.598126 0-217.931474 176.678932-394.588917 394.587893-394.588917 217.930451 0 394.58687 176.657442 394.58687 394.588917 0 66.571185-15.617699 129.220037-44.74611 184.229908l0 0.323365c-0.536212 1.838881-1.122567 3.631714-1.122567 5.602601 0 10.666944 8.650007 19.315928 19.314904 19.315928 8.312316 0 15.259542-5.335519 17.971303-12.68388l0 0.1361C927.164023 649.080052 945.424922 581.210291 945.424922 509.059018zM395.534963 391.95902c0-24.334221-19.71911-44.052309-44.053332-44.052309-24.334221 0-44.052309 19.718087-44.052309 44.052309 0 24.334221 19.718087 44.029796 44.052309 44.029796C375.815853 435.989839 395.534963 416.294264 395.534963 391.95902zM669.125085 347.974249c-24.314779 0-44.030819 19.696598-44.030819 44.029796s19.717064 44.052309 44.030819 44.052309c24.332175 0 44.052309-19.71911 44.052309-44.052309C713.17637 367.669824 693.456237 347.974249 669.125085 347.974249zM680.214654 642.001843c-2.62171 0-5.063319 0.647753-7.370875 1.477654l-0.201591 0c-49.497321 22.382776-104.283088 34.728966-162.137751 34.728966-58.752102 0-114.322745-12.097526-164.423817-35.111682l-0.448208 0c-1.74576-0.468674-3.473101-1.095961-5.377474-1.095961-11.965519 0-21.621437 9.655917-21.621437 21.597901 0 7.912204 5.685489 15.400758 12.027941 19.173688-0.169869-0.075725-0.297782-0.132006 0.048095 0.028653 54.629201 24.670889 116.382661 39.593763 180.219571 39.593763 64.24214 0 124.875079-14.564717 179.772386-39.526225 6.721075-3.697205 11.293207-10.891048 11.293207-19.114336C701.994704 651.726322 692.224176 642.001843 680.214654 642.001843z"
          fill="#272636"
          p-id="2306"
        ></path>
      </svg>
      <input type="file" id="upload" />
      <svg
        t="1687933319674"
        class="icon"
        viewBox="0 0 1024 1024"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        p-id="3320"
        width="20"
        height="20"
        @click="uploadFile"
      >
        <path
          d="M912 208H427.872l-50.368-94.176A63.936 63.936 0 0 0 321.056 80H112c-35.296 0-64 28.704-64 64v736c0 35.296 28.704 64 64 64h800c35.296 0 64-28.704 64-64v-608c0-35.296-28.704-64-64-64z m-800-64h209.056l68.448 128H912v97.984c-0.416 0-0.8-0.128-1.216-0.128H113.248c-0.416 0-0.8 0.128-1.248 0.128V144z m0 736v-96l1.248-350.144 798.752 1.216V784h0.064v96H112z"
          fill="#020202"
          p-id="3321"
        ></path>
      </svg>
    </div>
    <textarea class="textarea" v-model="text"></textarea>
    <button class="send" @click="addMessage">发送</button>
  </div>
</template>

<script>
import WebsocketLink from "@/api/websocket/index.js";
import { ref, reactive, onMounted, computed, markRaw, toRaw } from "vue";
import { useStore } from "vuex";
export default {
  name: "chatInput",
  setup(props, context) {
    let store = useStore();
    let text = ref("");
    let wsInstance = new WebsocketLink(
      `/chat?userId=${store.state.info.id}`,
      "chat",
      {
        token: "666",
        data: "4f4a",
      }
    );
    let ws = wsInstance.init();
    ws.onmessage = function (MessageEvent) {
      let data = JSON.parse(MessageEvent.data);
      if (data.dataType !== "error") {
        if (data.messageType === "broadcast") {
          //用户更新
          if (data.dataType === "update") {
            let info = JSON.parse(data.info);
            store.state.onlinePeople = info.map((item) => {
              item.photo = `data:image/png;base64,${item.photo}`;
              return item;
            });
            store.state.onlinePerson = info.length;
          } else if (data.dataType === "join") {
            //
          } else {
            store.state.messages.splice(store.state.messages.length, 0, data);
          }
        } else if (data.messageType === "single") {
          store.state.messages.splice(store.state.messages.length, 0, data);
        }
      }

      console.log(data);
    };
    ws.onclose = function (e) {
      console.log(e);
    };

    let date = new Date();
    let dateString = `${date.getFullYear()}-${
      date.getMonth() + 1
    }-${date.getDate()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;

    function uploadFile() {
      let upload_inp = document.querySelector("#upload");
      upload_inp.click();
      // let upload_to_server = document.querySelector(".upload_to_server");
      let _file;
      let type;
      let data;
      upload_inp.addEventListener("change", function () {
        _file = upload_inp.files[0];
        let fileReader = new FileReader();
        fileReader.addEventListener("load", function () {
          let result = fileReader.result;
          console.log(result);
          data = result.split(",");
          type = data[0].split(";")[0].split("/")[1];
          if (store.state.currentChat.name === "聊天室") {
            wsInstance.sendWs({
              messageType: "broadcast",
              userId: store.state.info.id,
              dateTime: dateString,
              oppositeId: "",
              dataType: "file",
              fileName: `${_file.name}`,
              fileType: _file.type,
              info: data[1],
            });
          } else {
            wsInstance.sendWs({
              messageType: "single",
              userId: store.state.info.id,
              dateTime: dateString,
              oppositeId: store.state.currentChat.id,
              dataType: "file",
              fileName: `${_file.name}`,
              fileType: _file.type,
              info: data[1],
            });
          }
        });
        fileReader.readAsDataURL(_file);
        console.log(upload_inp.files);
      });
    }

    function send() {
      if (text.value !== "") {
        if (store.state.currentChat.name === "聊天室") {
          wsInstance.sendWs({
            messageType: "broadcast",
            userId: `${store.state.info.id}`,
            dateTime: dateString,
            dataType: "text",
            oppositeId: "",
            fileName: "",
            info: text.value,
          });
        } else {
          wsInstance.sendWs({
            messageType: "single",
            userId: `${store.state.info.id}`,
            dateTime: dateString,
            dataType: "text",
            oppositeId: store.state.currentChat.id,
            fileName: "",
            info: text.value,
          });
        }
      }
      setTimeout(() => {
        document.getElementsByClassName("screen")[0].scrollTop =
          document.getElementsByClassName("screen")[0].scrollHeight;
      }, 50);
    }
    function addMessage() {
      let text1 = toRaw(text).value;
      // let textInfo = {
      //   messageType: "broadcast",
      //   userId: `${store.state.info.id}`,
      //   dateTime: new Date(),
      //   dataType: "text",
      //   oppositeId: "",
      //   fileName: "",
      //   info: text.value,
      // };
      // //TODO
      // store.state.messages.splice(store.state.messages.length, 0, textInfo);
      // context.emit("sendMessage", text1);
      send();
      text.value = "";
    }
    return {
      text,
      addMessage,
      uploadFile,
    };
  },
};
</script>

<style scoped>
.chatinput {
  width: 100%;
  border-top: 1px solid grey;
  height: 27%;
  position: relative;
}
.extend {
  display: flex;
}
.extend svg {
  padding: 5px 0 0 5px;
}
.textarea {
  width: 98%;
  background-color: transparent;
  border: none;
  padding: 0 1%;
  padding-top: 5px;
  font-size: 18px;
  resize: none;
  outline: none;
  height: 75%;
  overflow-y: auto;
}
/* 定义滚动条宽高及背景，宽高分别对应横竖滚动条的尺寸 */
.textarea::-webkit-scrollbar {
  width: 10px; /* 对垂直滚动条有效 */
}

/* 定义滚动条的轨道颜色、内阴影及圆角 */
.textarea::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
}

/* 定义滑块颜色、内阴影及圆角 */
.textarea::-webkit-scrollbar-thumb {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 0px rgba(0, 0, 0, 0.173);
  background-color: rgba(0, 0, 0, 0.1);
}

/* 光标移到滚动滑块上样式颜色变深 */
.textarea::-webkit-scrollbar-thumb:hover {
  background-color: rgba(0, 0, 0, 0.2);
}

/* 定义两端按钮的样式 */
.textarea::-webkit-scrollbar-button {
  background-color: cyan;
}

/* 定义右下角汇合处的样式 */
.textarea::-webkit-scrollbar-corner {
  background: khaki;
}
.send {
  background-color: rgba(135, 207, 235, 0.62);
  width: 80px;
  height: 30px;
  padding: 5px;
  cursor: pointer;
  border: none;
  position: absolute;
  bottom: 10px;
  right: 10px;
}
#upload {
  display: none;
}
</style>