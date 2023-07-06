<template>
  <div class="login">
    <video id="v1" autoplay loop muted>
      <source src="@/assets/video/video.mp4" type="video/mp4" />
    </video>
    <div
      class="container"
      @mouseenter="mouseIn($event)"
      @mouseleave="mouseOut($event)"
    >
      <div class="font">
        <h2>Welcome to WebChat</h2>
      </div>
      <form action="">
        <input
          class="tbx"
          type="text"
          placeholder="ID"
          v-model="loginInfo.id"
          @focus="closeOutControl"
          @blur="openOutControl"
        />
        <input
          class="tbx"
          type="password"
          placeholder="密码"
          v-model="loginInfo.password"
          @focus="closeOutControl"
          @blur="openOutControl"
        />
        <div class="verify-box">
          <input
            class="tbx"
            type="text"
            style="width: 100px"
            placeholder="验证码"
            v-model="loginInfo.verifyCode"
            @focus="closeOutControl"
            @blur="openOutControl"
          />
          <img :src="verifyImg" class="verify" @click="getVerifyImg" />
        </div>

        <input type="button" value="登录" class="sub" @click="startChat" />
        <input type="button" value="注册" class="sub" @click="gotoRegister" />
      </form>
      <span
        v-if="isAppear"
        :style="{
          top: position.top + 'px',
          left: position.left + 'px',
          backgroundColor: currentBgColor,
          color: fontColor,
        }"
        :class="{ in: !isIn, out: !isOut }"
      ></span>
      <div id="register" :style="{ right: currentRegister }">
        <div id="return">
          <svg
            t="1687869690461"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="2266"
            width="20"
            height="20"
            @click="gotoLogin"
          >
            <path
              d="M857.70558 495.009024 397.943314 27.513634c-7.132444-7.251148-18.794042-7.350408-26.048259-0.216941-7.253194 7.132444-7.350408 18.795065-0.216941 26.048259l446.952518 454.470749L365.856525 960.591855c-7.192819 7.192819-7.192819 18.85544 0 26.048259 3.596921 3.596921 8.311293 5.39487 13.024641 5.39487s9.42772-1.798972 13.024641-5.39487L857.596086 520.949836C864.747973 513.797949 864.796068 502.219239 857.70558 495.009024z"
              fill="#1296db"
              p-id="2267"
            ></path>
          </svg>
        </div>
        <div class="register-input">
          <div class="login_box">
            <!-- required就是不能为空 必须在css效果中有很大的作用 -->
            <!-- 可以简写为required -->
            <input type="text" required v-model="registerInfo.nickName" /><label
              >昵称</label
            >
          </div>
          <div class="login_box">
            <input
              type="password"
              required="required"
              v-model="registerInfo.password"
            /><label>密码</label>
          </div>
          <div class="login_box">
            <input
              type="text"
              required="required"
              v-model="registerInfo.email"
            /><label>邮箱</label>
          </div>
          <div class="login_box">
            <input
              type="text"
              required="required"
              v-model="registerInfo.code"
            /><label>验证码</label>
            <button class="sendVerify" @click="getEmailCode">
              {{ verifyText }}
            </button>
          </div>
          <a @click="startRegister">
            注册
            <span></span>
            <span></span>
            <span></span>
            <span></span>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Login from "@/api/login/index.js";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "login",
  setup() {
    //是否可以进入
    let isIn = ref(true);

    let loginInfo = reactive({
      id: "",
      password: "",
      verifyCode: "",
    });

    let registerInfo = reactive({
      nickName: "",
      password: "",
      code: "",
      email: "",
    });

    let login = new Login();

    let router = useRouter();

    //是否可以退出
    let isOut = ref(false);

    //函数执行控制器
    let inControl = ref(true);
    let outControl = ref(false);

    //验证码图片
    let verifyImg = ref("");

    //是否显示
    let isAppear = ref(false);

    //是否注册
    let isRegister = ref(false);

    const store = useStore();

    //span当前位置
    let position = reactive({
      left: 0,
      top: 0,
    });

    //当前color
    let color = reactive({
      r: 0,
      g: 0,
      b: 0,
    });

    let verifyText = ref("发送验证码");

    let currentBgColor = computed(() => {
      return `rgb(${color.r},${color.g},${color.b})`;
    });
    let fontColor = computed(() => {
      return `rgb(${255 - color.r},${255 - color.g},${255 - color.b})`;
    });
    let currentRegister = computed(() => {
      return isRegister.value ? `0%` : `-100%`;
    });

    //随时间变化的颜色
    function timeColor() {
      let date = new Date();
      let hours = date.getHours();
      let minutes = date.getMinutes();

      let color_hour = hours + minutes / 60;

      if (color_hour >= 0 && color_hour <= 5) {
        color.r = 0;
        color.g = 0;
        color.b = 0;
        color.b += color_hour * 16;
      } else if (color_hour > 5 && color_hour <= 13) {
        color.r = 255;
        color.g = 255;
        color.b = 255;
        color.g -= ((color_hour - 5) * 115) / 8;
      } else if (color_hour > 13 && color_hour <= 18) {
        color.r = 255;
        color.g = 140;
        color.b = 255;
        color.g += ((color_hour - 13) * 115) / 5;
      } else if (color_hour > 18 && color_hour <= 19) {
        color.r = 222;
        color.g = 145;
        color.b = 2;
      } else if (color_hour > 19 && color_hour < 24) {
        color.r = 0;
        color.g = 0;
        color.b = 80;
        color.b -= (color_hour - 19) * 16;
      }
    }

    //鼠标进入
    function mouseIn(e) {
      if (inControl.value) {
        //如果进去的门是打开的，就可以执行这个函数
        //获取进入时的鼠标位置
        //生成元素的位置=进入点距离窗口的距离-父盒子距离窗口的距离
        position.left = e.clientX - e.target.offsetLeft;
        position.top = e.clientY - e.target.offsetTop;
        timeColor();

        inControl.value = false;
        outControl.value = true;
        isAppear.value = true;
        isIn.value = false; //关闭进来的门（不能使用进来的方法）
        isOut.value = true; //打开出去的门（可以使用出去的方法）
      }
    }

    //鼠标退出
    function mouseOut(e) {
      if (outControl.value) {
        //如果进去的门是打开的，就可以执行这个函数

        //获取进入时的鼠标位置
        //生成元素的位置=进入点距离窗口的距离-父盒子距离窗口的距离
        position.left = e.clientX - e.target.offsetLeft;
        position.top = e.clientY - e.target.offsetTop;

        timeColor();

        outControl.value = false;
        isOut.value = false; //关闭出去的大门

        // 当动画结束后再删除元素
        setTimeout(() => {
          isAppear.value = false;
          inControl.value = true;
          isIn.value = true; //打开进来的大门
        }, 500);
      }
    }

    //鼠标退出可以触发退出动画
    function openOutControl() {
      outControl.value = true;
    }

    //鼠标退出不可以触发退出动画
    function closeOutControl() {
      outControl.value = false;
    }

    //转向注册页面
    function gotoRegister() {
      isRegister.value = true;
    }

    //转向登录页面
    function gotoLogin() {
      isRegister.value = false;
    }

    function startChat() {
      login.checkLogin(loginInfo).then((res) => {
        if (res.code === 200) {
          let data = res.data;
          store.state.info.id = data.id;
          store.state.info.name = data.nickName;
          store.state.info.photo = `data:image/webp;base64,${data.photo}`;
          router.push({
            name: "main",
          });
        } else {
          alert(res.msg);
        }
      });
    }

    function getEmailCode() {
      login.getEmailCode(registerInfo.email);
    }

    function startRegister() {
      login.startRegister(registerInfo).then((res) => {
        alert(res.msg);
      });
    }

    function getVerifyImg() {
      login.getVerifyImg().then((res) => {
        verifyImg.value = `data:image/webp;base64,${res.data}`;
      });
    }

    onMounted(() => {
      getVerifyImg();
    });

    return {
      isIn,
      isOut,
      inControl,
      outControl,
      isAppear,
      isRegister,
      currentBgColor,
      currentRegister,
      fontColor,
      position,
      loginInfo,
      registerInfo,
      verifyImg,
      verifyText,
      startChat,
      getVerifyImg,
      mouseIn,
      mouseOut,
      gotoLogin,
      gotoRegister,
      getEmailCode,
      startRegister,
      openOutControl,
      closeOutControl,
    };
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  text-decoration: none;
  list-style: none;
  box-sizing: border-box;
}
.verify {
  width: 100px;
  height: 30px;
}

.verify-box {
  display: flex;
}

.sendVerify {
  width: 100px;
  height: 50px;
  border: none;
  color: #409eff;
  background-color: transparent;
  position: absolute;
  cursor: pointer;
  right: 0;
}

.login {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;

  /* 一键五连 */

  /* 加载背景图 */
  /* background-image: url(two.jpg); */
  /* 背景图垂直、水平均居中 */
  /* background-position: center center; */
  /* 背景图不平铺 */
  /* background-repeat: no-repeat; */
  /* 当内容高度大于图片高度时,背景位置相对于viewport固定 */
  /* background-attachment: fixed; */
  /* 让背景图基于容器大小伸缩 */
  /* background-size: cover; */
  /* 设置背景颜色,背景图加载过程中会显示背景色 */
  /* background-color: #3498bd; */
}

video {
  position: fixed;
  right: 0px;
  bottom: 0px;
  min-width: 100vh;
  min-height: 100vh;
  /* height: auto;
    width: auto; */
  z-index: -11;
}

source {
  min-width: 100vh;
  min-height: 100vh;
  height: auto;
  width: auto;
}

.container {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  flex-direction: column;
  width: 300px;
  height: 450px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.1);
  overflow: hidden;
  position: relative;
  box-shadow: 20px 20px 50px rgba(0, 0, 0, 0.5);
  border-top: 1px solid rgba(255, 255, 255, 0.5);
  border-left: 1px solid rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(5px);
}

.container form {
  width: 400px;
  height: 60%;
  display: flex;
  justify-content: space-around;
  flex-direction: column;
  align-items: center;
  z-index: 1;
}

.container form .tbx {
  width: 200px;
  height: 40px;
  outline: none;
  border: none;
  border-bottom: 1px solid #fff;
  background: none;
  color: #fff;
}

.container form .tbx::placeholder {
  color: #fff;
  font-size: 15px;
}

.container form .sub {
  width: 200px;
  height: 40px;
  outline: none;
  border: 1px solid #fff;
  border-radius: 20px;
  letter-spacing: 5px;
  color: #fff;
  background: none;
  cursor: pointer;
}

.container h1 {
  z-index: 1;
  color: #ecf0f1;
  letter-spacing: 5px;
  padding-left: 5px;
  font-size: 50px;
  font-weight: 100;
  text-shadow: 5px 5px 5px rgba(33, 45, 58, 0.3);
}

/* 设置鼠标进入的样式 */
.container .in {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 0;
  height: 0;
  border-radius: 50%;
  background: #3498bd;
  transform: translate(-50%, -50%);
  /* 使用in动画，持续0.5s，缓出的时间函数，停留在最后一帧 */
  animation: in 0.5s ease-out forwards;
}

/* 设置鼠标离开的样式 */
.container .out {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 1200px;
  height: 1200px;
  border-radius: 50%;
  background: #3498bd;
  transform: translate(-50%, -50%);
  animation: out 0.5s ease-out forwards;
}

.font {
  z-index: 1;
  position: relative;
}

.font h2 {
  overflow: hidden;
  white-space: nowrap;
  letter-spacing: 1px;
  font-size: 1.2em;
  color: white;
  animation: typing 2s steps(20);
}

.font::after {
  content: "";
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  width: 2px;
  height: 100%;
  background: #fff;
  animation: blink 1.1s linear infinite;
}

@keyframes blink {
  0%,
  49% {
    opacity: 1;
  }
  50%,
  100% {
    opacity: 0;
  }
}

@keyframes typing {
  0% {
    width: 0;
  }
  100% {
    width: 236px;
  }
}

/* 设置鼠标进入时，元素的动画 */
@keyframes in {
  /* 初始关键帧 */
  0% {
    width: 0;
    height: 0;
  }

  /* 结尾关键帧 */
  100% {
    width: 1200px;
    height: 1200px;
  }
}

/* 设置鼠标离开时，元素的动画 */
@keyframes out {
  /* 初始关键帧 */
  0% {
    width: 1200px;
    height: 1200px;
  }

  /* 结尾关键帧 */
  100% {
    width: 0;
    height: 0;
  }
}

#register {
  width: 100%;
  height: 100%;
  position: absolute;
  background: #fff;
  right: -100%;
  transition: all 1s;
  z-index: 2;
}

#return {
  margin: 15px 0 0 10px;
}

#return svg {
  transform: rotateY(180deg);
  transition: all 1s;
}

#return svg:hover {
  transform: rotateY(0deg);
}

.register-input {
  /* 弹性布局 让子元素称为弹性项目 */
  display: flex;
  /* 让弹性项目垂直排列 原理是改变弹性盒子的主轴方向 父元素就是弹性盒子 现在改变后的主轴方向是向下了 */
  flex-direction: column;
  /* 让弹性项目在交叉轴方向水平居中 现在主轴的方向是向下 交叉轴的方向是与主轴垂直 交叉轴的方向是向右 */
  align-items: center;
  width: 100%;
  padding: 40px;
  color: #409eff;
}
.register-input h2 {
  color: #409eff;
  margin-bottom: 30px;
}
.register-input .login_box {
  /* 相对定位 */
  position: relative;
  width: 100%;
}
.register-input .login_box input {
  /* 清除input框自带的边框和轮廓 */
  outline: none;
  border: none;
  width: 100%;
  padding: 10px 0;
  margin-bottom: 30px;
  color: #409eff;
  font-size: 16px;
  border-bottom: 2px solid #409eff;
  /* 背景颜色为透明色 */
  background-color: transparent;
}
.register-input .login_box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  color: #409eff;
  /* 这个属性的默认值是auto 默认是这个元素可以被点击 但是如果我们写了none 就是这个元素不能被点击 , 就好像它可见但是不能用 可望而不可即 */
  /* 这个就是两者的区别 */
  pointer-events: none;
  /* 加个过渡 */
  transition: all 0.5s;
}
/* :focus 选择器是当input获得焦点是触发的样式 + 是相邻兄弟选择器 去找与input相邻的兄弟label */
/* :valid 选择器是判断input框的内容是否合法,如果合法会执行下面的属性代码,不合法就不会执行,我们刚开始写布局的时候给input框写了required 我们删掉看对比 当没有required的话input框的值就会被认为一直合法,所以一直都是下方的样式 ,但是密码不会,密码框内的值为空,那么这句话局不合法,required不能为空 当我们给密码框写点东西的时候才会执行以下代码*/
.register-input .login_box input:focus + label,
.register-input .login_box input:valid + label {
  top: -20px;
  color: #409eff;
  font-size: 12px;
}
.register-input a {
  overflow: hidden;
  position: relative;
  padding: 10px 20px;
  color: #409eff;
  /* 取消a表现原有的下划线 */
  text-decoration: none;
  /* 同样加个过渡 */
  transition: all 0.5s;
  cursor: pointer;
}
.register-input a:hover {
  color: #fff;
  border-radius: 5px;
  background-color: #409eff;
  box-shadow: 0 0 5px #409eff, 0 0 25px #409eff, 0 0 50px #409eff,
    0 0 100px #409eff;
}
.register-input a span {
  position: absolute;
}
.register-input a span:first-child {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  /* to right 就是往右边 下面的同理 */
  background: linear-gradient(to right, transparent, #409eff);
  /* 动画 名称 时长 linear是匀速运动 infinite是无限次运动 */
  animation: move1 1s linear infinite;
}
.register-input a span:nth-child(2) {
  right: 0;
  top: -100%;
  width: 2px;
  height: 100%;
  background: linear-gradient(transparent, #409eff);
  /* 这里多了个0.25s其实是延迟时间 */
  animation: move2 1s linear 0.25s infinite;
}
.register-input a span:nth-child(3) {
  right: -100%;
  bottom: 0;
  width: 100%;
  height: 2px;
  background: linear-gradient(to left, transparent, #409eff);
  animation: move3 1s linear 0.5s infinite;
}
.register-input a span:last-child {
  left: 0;
  bottom: -100%;
  width: 2px;
  height: 100%;

  background: linear-gradient(#409eff, transparent);
  animation: move4 1s linear 0.75s infinite;
}
/* 写一下动画 再坚持一下 视频马上就完了 */
@keyframes move1 {
  0% {
    left: -100%;
  }
  50%,
  100% {
    left: 100%;
  }
}
@keyframes move2 {
  0% {
    top: -100%;
  }
  50%,
  100% {
    top: 100%;
  }
}
@keyframes move3 {
  0% {
    right: -100%;
  }
  50%,
  100% {
    right: 100%;
  }
}
@keyframes move4 {
  0% {
    bottom: -100%;
  }
  50%,
  100% {
    bottom: 100%;
  }
}
</style>