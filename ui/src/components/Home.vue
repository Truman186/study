<template>
  <!--引入container布局-->
  <el-container class="home_container">
    <el-header>
      <div>
        <img src="../assets/logo.png" alt/>
      

      </div>
      <span>个人运动平台</span>
      <!--头部-->
      <span>
        <div class="userInfo">
        欢迎{{username}}
        </div>
      </span>
      <el-button type="info" @click="logout()">安全退出</el-button></el-header>

    <!--主体-->
    <el-container>
      <el-aside :width="iscollapse?'64px':'200px'">
        <div class="toggle-button" @click="toggleCollapase()">|||</div>
        <el-menu
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#4093ff"
                unique-opened :collapse="iscollapse" :collapse-transition="false"
                :router="true" :default-active="activePath">
          <!--一级菜单-->
          <el-submenu :index="item.id+''" :key="item.id" v-for="item in menulist" >
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>{{item.title}}</span>
            </template>
            <!--  二级菜单-->
            <div>
            <el-menu-item :index="it.path+''" v-for="(it,index) in item.slist" :key="index" @click="savaNavState(it.path)">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{it.title}}</span>
              </template>
            </el-menu-item>
            </div>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import Cookies from "js-cookie";
  export default {
    data(){
      return{
        username:'',
        menulist:[],
        iscollapse:false,
        activePath:'/welcome',
      }
    },
    created() {
      this.getMenulist();
      this.activePath=window.sessionStorage.getItem('activePath');
      this.username=Cookies.get("username");
    },
    mounted(){
      this.getCookie();
    },
    methods: {
      logout() {
        console.log(1111);
        window.sessionStorage.clear();//清除sseion
        this.$router.push("/login");
      },
      getCookie() {
        const username = Cookies.get("username");
        const password = Cookies.get("password");
      },
      //获取菜单列表
      async getMenulist(){
        const username = Cookies.get("username");
        const {data:res}=await this.$http.get("menus?username="+username);
        console.log(res);
        if (res.flag!=200){
          return this.$message.error("获取列表失败");
        }
        this.menulist =res.menus;  //数据回填
      },
      toggleCollapase(){  //控制伸缩
        this.iscollapse=!this.iscollapse;
      },
      //保存路径
      savaNavState(activePath){
        window.sessionStorage.setItem('activePath',activePath);
        this.activePath=activePath;
      }
    }
  };
</script>

<style lang="less" scoped>
  //头部样式
  .home_container{
    height: 100%;
  }
  .el-header{

    display: flex;
    justify-content: space-between;
    padding-left: 0%;
    align-items: center;
    background-color: #404040;
    font-size: 40px;
    font-family:"楷体","楷体_GB2312";
    color: white;
    vertical-align:middle;
    display: flex;
    align-items: center;
    >div{
      span{
        padding-top: 0px;
        text-align: center;
      }
    }



  }
  //侧边栏样式
  .el-aside{
    background-color: #333744;
    .el-menu{
      border-right: none;
    }
  }
  //主体样式
  .el-main{
    background-color: #eaedf1;
    padding: 0px;
  }
  img{
    background-color: #404040;
    width: 198px;
    height: 68px;
  }
  //  |||按钮样式
  .toggle-button{
    background-color: #4A5064;
    font-size: 10px;
    line-height: 24px;
    color: white;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;  //显示小手
  }
  .userInfo{
    font-size: 30px;
    padding-left: 550px;
  }
</style>
