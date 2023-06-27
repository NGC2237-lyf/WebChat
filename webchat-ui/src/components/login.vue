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
      <h1 class="">Welcome to WebChat</h1>
      <form action="">
        <input class="tbx" type="text" placeholder="账号" @focus="" @blur="" />
        <input class="tbx" type="password" placeholder="密码" />
        <input type="submit" value="登录" class="sub" />
        <input type="submit" value="注册" class="sub" />
      </form>
      <span
        v-if="isAppear"
        :style="{
          top: position.top + 'px',
          left: position.left + 'px',
          backgroundColor: currentColor,
          color: fontColor,
        }"
        :class="{ in: !isIn, out: !isOut }"
      ></span>
    </div>
  </div>
</template>

<script>
import { ref, reactive, onMounted, computed } from "vue";
export default {
  name: "login",
  setup() {
    //是否可以进入
    let isIn = ref(true);

    //是否可以退出
    let isOut = ref(false);

    //是否显示
    let isAppear = ref(false);

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
    let currentBgColor = computed(() => {
      return `rgb(${color.r},${color.g},${color.b})`;
    });
    let fontColor = computed(() => {
      return `rgb(${255 - color.r},${255 - color.g},${255 - color.b})`;
    });

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

    function mouseIn(e) {
      if (isIn) {
        //如果进去的门是打开的，就可以执行这个函数
        console.log(e);
        //获取进入时的鼠标位置
        //生成元素的位置=进入点距离窗口的距离-父盒子距离窗口的距离
        position.left = e.clientX - e.target.offsetLeft;
        position.top = e.clientY - e.target.offsetTop;
        // let inx = e.clientX - e.target.offsetLeft;
        // let iny = e.clientY - e.target.offsetTop;

        //创建一个span元素，给它对应的出生坐标
        // let el = document.createElement("span");
        // el.style.left = inx + "px";
        // el.style.top = iny + "px";
        // con.appendChild(el); //添加到con对应的元素，即container

        // $(".container span").removeClass("out"); //移除出去的动画
        // $(".container span").addClass("in"); //移除进入的动画
        // span = document.querySelector(".container span");
        timeColor();

        isAppear.value = true;
        isIn.value = false; //关闭进来的门（不能使用进来的方法）
        isOut.value = true; //打开出去的门（可以使用出去的方法）
        console.log(isAppear);
      }
    }
    function mouseOut(e) {
      if (isOut) {
        //如果进去的门是打开的，就可以执行这个函数

        //获取进入时的鼠标位置
        //生成元素的位置=进入点距离窗口的距离-父盒子距离窗口的距离
        position.left = e.clientX - e.target.offsetLeft;
        position.top = e.clientY - e.target.offsetTop;

        // $(".container span").removeClass("in"); //移除进入的动画
        // $(".container span").addClass("out"); //添加出去的动画
        timeColor();
        // $(".out").css("left", inx + "px"); //添加出去的坐标
        // $(".out").css("top", iny + "px");

        isOut.value = false; //关闭出去的大门

        // 当动画结束后再删除元素
        setTimeout(() => {
          isAppear.value = false;
          //   con.removeChild(span); //删除元素
          isIn.value = true; //打开进来的大门
          console.log(isAppear);
        }, 500);
      }
    }
    return {
      isIn,
      isOut,
      isAppear,
      currentBgColor,
      fontColor,
      position,
      mouseIn,
      mouseOut,
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
  justify-content: center;
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
  height: 200px;
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
</style>