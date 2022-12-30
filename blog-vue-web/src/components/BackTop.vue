<template>
  <div class="rightside" :style="isShow">
    <i @click="backTop"   style="font-size: 25px;font-weight: bold;" class="el-icon-top  rightside-icon iconziyuanldpi"></i>
  </div>
</template>

<script>
export default {
  mounted() {
    window.addEventListener("scroll", this.scrollToTop);
  },
  destroyed() {
    window.removeEventListener("scroll", this.scrollToTop);
  },
  data: function() {
    return {
      isShow: ""
    };
  },
  methods: {
    // 回到顶部方法
    backTop() {
      window.scrollTo({
        behavior: "smooth",
        top: 0
      });
    },
    // 为了计算距离顶部的高度，当高度大于100显示回顶部图标，小于100则隐藏
    scrollToTop() {
      const that = this;
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      that.scrollTop = scrollTop;
      if (that.scrollTop > 20) {
        that.isShow = "opacity: 1;transform: translateX(-50px);";
      } else {
        that.isShow = "";
      }
    }
  }
};
</script>

<style scoped>
.rightside {
  z-index: 4;
  position: fixed;
  right: -43px;
  bottom: 85px;
  transition: all 0.5s;
}
.rightside-icon,
.setting-container {
  display: block;
  margin-bottom: 2px;
  width: 40px;
  height: 40px;
  background-color: #49b1f5;
  color: #fff;
  text-align: center;
  line-height: 40px;
  border-radius: 50%;
  cursor: pointer;
}
.rightside-icon:hover,
.setting-container:hover {
  background-color: #00B5AD;
}
.setting-container i {
  display: block;
  animation: turn-around 2s linear infinite;
}
@keyframes turn-around {
  0% {
    transform: rotate(0);
  }
  100% {
    transform: rotate(360deg);
  }
}
@keyframes rightsideOut {
  0% {
    transform: translate(0, 0);
  }
  100% {
    transform: translate(40px, 0);
  }
}
@keyframes rightsideIn {
  0% {
    transform: translate(40px, 0);
  }
  100% {
    transform: translate(0, 0);
  }
}
</style>
