<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <!--头像-->
          <img src="../assets/logo.png" alt />
      </div>
      <!--添加表单-->
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginRules"
        class="login_form"
        label-width="0px"
      >
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="iconfont icon-denglu"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="iconfont icon-mima" type="password"></el-input>
        </el-form-item>
        <el-form-item prop="seccode">
          <el-input
                  class="log-input"
                  v-model="loginForm.seccode"
                  placeholder="验证码"
                  prefix-icon="iconfont icon-verify"
                  @keydown.enter.native="checkCode"
          >
          </el-input>
          <span class="checkCode" @click="createCode">{{ checkCode}}</span>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
          <el-button type="primary" @click="regist">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import Cookies from "js-cookie";

  export default {
  data() {
    return {
      loginForm: {
        username: "system",
        password: "123456",
        seccode:""
      },
      checkCode:'',
      loginRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
      
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
     
        ],
        seccode: [{ required: true, message: "请输验证码", trigger: "blur" }]
      },

    };
  },
  mounted(){
    this.createCode();
    this.getCookie();
  },
  methods: {
    createCode() {
      var code = "";
      const codeLength = 4; //验证码的长度
      const random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
              'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数
      for(let i = 0; i < codeLength; i++) { //循环操作
        let index = Math.floor(Math.random() * 36); //取得随机数的索引（0~35）
        code += random[index]; //根据索引取得随机数加到code上
      }
      this.checkCode = code; //把code值赋给验证码
      /*loginForm.seccode=code;*/
    },

    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : password
      };
    },
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    login() {
      console.log("12344566");
       console.log(this.loginForm.seccode);
        console.log( this.checkCode);
      if(this.loginForm.seccode != this.checkCode) {
        this.$message.error("验证码错误！！！");
        this.createCode();
        return false;
      };
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;
        // 调用get请求
        const {data :res} = await this.$http.post("login", this.loginForm);
      /*  const res = 'ok';*/
         if (res.flag == "ok" ) {
           window.sessionStorage.setItem('flag','ok'); // session 放置
           this.$message.success("登陆成功！！！");
           Cookies.set("username", this.loginForm.username, { expires: 30 });
           Cookies.set("password", this.loginForm.password, { expires: 30 });

           this.$router.push({ path: "/home"});
         }else{
          this.$message.error("登录失败！！！");
         }
      });
    },
    regist(){
      if(this.loginForm.seccode != this.checkCode) {
        this.$message.error("验证码错误！！！");
        this.createCode();
        return false;
      };
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;
        // 调用get请求
        const {data :res} = await this.$http.post("regist", this.loginForm);
        /*  const res = 'ok';*/
        if (res.flag == "erro" ) {
          this.$message.error("用户已存在！！！");
        }else{
          this.$message.success("注册成功！！！");
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>

// 跟节点样式
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
// 登录框
.login_box {
  width: 450px;
  height: 350px;
  background-color: #fff;
  border-radius: 3px;// 圆角
  position: absolute;// 绝对定位
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);// 根据自己位置 以自己为长度位移
  // 头像框
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%; // 加圆角
    padding: 10px;
    box-shadow: 0 0 10px #ddd;// 盒子阴影
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #0ee;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%; // 加圆角
      background-color: #eee;
    }
  }
  .log-input{
    width: 60%;
  }
  .btns {
    display: flex;// 弹性布局
    justify-content: flex-end; // 尾部对齐
  }
  .login_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;// 设置边框
  }
  .checkCode{
    padding-left: 20px;
    color: #df5000;
  }
}
</style>
