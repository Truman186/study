<template>
  <div>
    <!--面包屑导航-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path:'/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--用户列表主体部分-->
    <el-card>

      <el-row :gutter="50">
        <!--搜索区域-->
        <el-col :span="10" >
          <el-input  placeholder="请输入用户名称" v-model="queryInfo.query" clearable @clear="getUserList">
            <el-button slot="append" type="primary" icon="el-icon-search" @click="getUserList()"></el-button>
          </el-input>
        </el-col>
        <!--添加区域-->
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加用户</el-button>
         
        </el-col>

          <a href="http://localhost:9000/exportUser">
              <el-col :span="4">
              <el-button  type="primary" class="iconfont icon-export" @click="exp()"></el-button>
          </el-col>
          </a>
           <el-button type="primary" @click="addTest" disabled>批量增加</el-button>
      </el-row>
      <!--用户列表-->
      <el-table
              :data="userList" border stripe
              style="width: 100%">

        <el-table-column type="index"></el-table-column>
        <el-table-column
                prop="username"
                label="姓名"
                width="180">
        </el-table-column>
        <el-table-column
                prop="email"
                label="邮箱"
                width="180">
        </el-table-column>
        <el-table-column
                prop="role"
                label="角色">
        </el-table-column>
    
        <el-table-column
                prop="state"
                label="状态"
                width="180">
          <template slot-scope="scope">
          <!-- {{scope.row}}-->
            <el-switch v-model="scope.row.state" @change="userStateChange(scope.row.username)"></el-switch>
          </template>
        </el-table-column>
        <!--作用域插槽-->

        <el-table-column label="操作"  width="280">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.username)"></el-button>
            <!--删除-->
            <el-button  type="danger" icon="el-icon-delete" size="mini" @click="deleteUser(scope.row.username)"></el-button>
            <!--权限-->

            <el-button type="warning" icon="el-icon-setting" size="mini" @click="changeRole(scope.row.username)">
              <el-tooltip effect="dark" content="更改角色" placement="top-start" :enterable="true" ></el-tooltip>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页-->
      <div>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pageNum"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="queryInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
      </div>
    </el-card>
    <!--//新增区域-->
    <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClose">
      <el-form :model="addForm" :rules="addRules" ref="addFormRef" label-width="70px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible=false">取消</el-button>
                 <el-button type="primary" @click="addUser">确定</el-button>
            </span>
    </el-dialog>
    <!--修改对话框-->
    <el-dialog title="修改用户信息" :visible.sync="editDialogVisible" width="50%" @close="editDialogClose">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="用户名" prop="username" disabled>
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible=false">取消</el-button>
                 <el-button type="primary" @click="editUserInfo">确定</el-button>
            </span>
    </el-dialog>
    <el-dialog title="更改角色" :visible.sync="changeDialogVisible" width="40%" height="220px" @close="editDialogClose">
      <el-form :model="changeForm"  ref="editFormRef" label-width="70px">
        <template>
        <el-select v-model="value" filterable placeholder="请选择角色">
          <el-option
                  v-for="item in roleList"
                  :key="item.roleId"
                  :label="item.roleName"
                  :value="item.roleId">
          </el-option>
        </el-select>
      </template>
      </el-form>
      <span slot="footer" class="dialog-footer">

                <el-button @click="changeDialogVisible=false">取消</el-button>
                 <el-button type="primary" @click="editRole()">确定</el-button>

            </span>
    </el-dialog>
  </div>
</template>
<script >

  export default{
    created() {
      this.getUserList();
      this.getList();
      console.log("aaddddd:"+this.roleList)
    },
    mounted(){
      this.getCookie();
    },
    data(){
      return{
        //查询信息实体
        queryInfo:{
          query:"",
          pageNum:1,
          pageSize:10,
        },
        userList:[], //用户列表
        total:0,//总记录数
        addDialogVisible:false,//对话框状态
        //添加
        addForm:{
          username:'',
          password:'',
          email:''
        },
        /*修改用户信息*/
        editForm:{
          username:'',
          password:'',
          email:''},
        //显示/隐藏
        editDialogVisible:false,
        //修改表单验证
        editFormRules:{
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 3, max: 8, message: '长度在 3 到 88 个字符', trigger: 'blur'}
          ],
          email: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
          ],
        },
        //表单验证
        addRules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 3, max: 8, message: '长度在 3 到 88 个字符', trigger: 'blur'}
          ],
          email: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
          ],
        },
        changeDialogVisible:false,
        changeForm:{
          roleId:'',
          username:''
        },
        roleList:[],
        value:'',
        editUser:''
      }
    },
    methods:{
      //获取所有用户
      getCookie() {
        const username = Cookies.get("username");
      },
      addTest(){
         this.$http.post("addUserTest")
      },
      async getUserList(){
        const {data:res} = await this.$http.get("alluser",{params:this.queryInfo});
        this.userList =res.data; //用户列表数据封装
        this.total =res.numbers;
      },
      handleSizeChange(newSize){
        this.queryInfo.pageSize=newSize;
        this.getUserList();
      },
      handleCurrentChange(newPage){
        this.queryInfo.pageNum=newPage;
        this.getUserList();
      },
      async userStateChange(username){
        /*  const {date:res} =await this.$http.put('updateUserState?id=userInfo.id&state=userInfo.state');*/
        const {data:res} = await this.$http.post("updateUserState?username="+username);
        console.log(res);
        if(res!="success"){

          return this.$message.error("操作失败！！");
        }
        this.$message.success("操作成功！");
        this.getUserList();
      },
      //监听添加用户操作
      addDialogClose(){
        this.$refs.addFormRef.resetFields();
      },
      addUser(){
        this.$refs.addFormRef.validate(async valid=>{
          if(!valid) return ;
          const {data:res} = await this.$http.post("addUser",this.addForm);
          if(res=="erro"){
            return this.$message.error("添加失败！");
          }
          if(res=="exist"){
              return this.$message.error("用户名重复！");
          }

          this.$message.success("添加成功！");
          this.addDialogVisible=false;
          this.getUserList();
        });
      },
      /*根据id删除用户*/
      async deleteUser(username){
        const confimResust=await this.$confirm('确定删除？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).catch(err=>err)
        if(confimResust!='confirm'){
          return this.$message.info("已取消删除");
        }
        const {data:res}=await this.$http.delete("deleteUser?username="+username);
        /*     const {data:res}=await this.$http.delete("deleteUser");*/
        console.log(res);
        if(res!="success"){
          return this.$message.error("删除失败！");
        }
        this.$message.success("删除成功！");
        this.getUserList();
      },
      //显示对话框
      async showEditDialog(username){
        // const {data:res} = await this.$http.get("alluser",{params:this.queryInfo});
        const {data:res} =await this.$http.get("getUpdateUser?username="+username);
        this.editForm=res;//查询出来的用户信息反添到表单里
        console.log(res);
        this.editDialogVisible=true;
      },
      //关闭窗口
      editDialogClose(){
        this.$refs.editFormRef.resetFields();
      },
      editUserInfo(){
        this.$refs.editFormRef.validate(async  valid=>{
          if(!valid) return ;
          //发起修改请求
          const {data:res} =  await this.$http.post("editUser",this.editForm);
          console.log(res);
          if(res!="success")return this.$message.error("操作失败");
          this.$message.success("操作成功");
          this.editDialogVisible=false;
          this.getUserList();
        })
      },
       exp(){
        this.$message.success("导出成功");
      },
      changeRole(username){
        this.changeDialogVisible=true;
        this.editUser =username
      },
      async  getList(){

        const {data:res} = await this.$http.get("getAllRole");
        this.roleList =res.role; //用户列表数据封装
        console.log(roleList)
      },
      async editRole(){
        const roleId =this.value;
        const username =this.editUser;
        this.changeForm.roleId =roleId;
        this.changeForm.username =username;
 /*       console.log(this.changeForm.username)
        console.log(this.editUser);*/
        const {data:res} = await this.$http.put("editRole",this.changeForm);
        if(res!="success")return this.$message.error("操作失败");
        this.$message.success("操作成功");
       this.changeDialogVisible=false;
        this.getUserList();
      }
    }
  }
</script>
<style lang="less" scoped>
  .el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
  }
</style>
