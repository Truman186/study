<template>
   <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>运动平台</el-breadcrumb-item>
            <el-breadcrumb-item>课程管理</el-breadcrumb-item>
        </el-breadcrumb>
       <div class="demo-input-suffix">
             <span class="demonstration"></span>
            <el-input class="input1"
                    placeholder="请输入课程名称"
                    v-model="queryForm.cname"  clearable @clear="getList()">
            </el-input>
              <el-input class="input2"
                    placeholder="请输入教练名称"
                    v-model="queryForm.username"  clearable @clear="getList()">
            </el-input>
         
                 <el-button class="input3" slot="append" type="primary" icon="el-icon-search"   @click="getList()"></el-button>
       </div>
          <el-table
                :data="list" border stripe
                style="width: 100%">
            <el-table-column aria-disabled="false"
                    prop="cid"
                    label="编号"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="cname"
                    label="课程名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="教练名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="weekday"
                    label="星期"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="time"
                    label="时间"
                    width="120">
            </el-table-column>
             <el-table-column
                    prop="num"
                    label="课余量"
                    width="120">
            </el-table-column>
             <el-table-column
                    prop="lesson"
                    label="课时"
                    width="120">
            </el-table-column>
             <el-table-column
                    prop="address"
                    label="场地"
                    width="120">
            </el-table-column>
             <el-table-column
                    prop="remake"
                    label="备注"
                    width="120">
            </el-table-column>
        <el-table-column label="操作"  width="150">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-delete" size="mini" @click="dropCourse(scope.row)">退课</el-button>
                    <!--删除-->

                </template>
            </el-table-column>
        </el-table>
       
   </div>
</template>
<script>
  import Cookies from "js-cookie";
export default {
     name: "mycourse.vue",
        created() {
           this.getList();
        },
         mounted(){
          this.getCookie();
    },
        data(){
            return{
                username:'',
                queryForm:{
                    cname:'',
                    username:'',
                    stuName:'',
                },
                list:[],
                addDiaLog:false,
                addForm:{
                  cid:'',
                    cname:'',
                    username:'',
                    weekday:'',
                    time:'',
                    num:'',
                    lesson:'',
                    address:'',
                    remake:'',
                    stuName:''
                },
                username:'',
                 options: [{
                  value: '星期一',
                  label: '星期一'
                  }, {
                  value: '星期二',
                  label: '星期二'
                  }, {
                  value: '星期三',
                  label: '星期三'
                  }, {
                  value: '星期四',
                  label: '星期四'
                  }, {
                  value: '星期五',
                  label: '星期五'
                 },
                 {
                  value: '星期六',
                  label: '星期六'
                 },
                 {
                  value: '星期天',
                  label: '星期天'
                 },
                 ],
                 optionad:[
                     { value: '训练室1',
                      label: '训练室1'},
                        { value: '训练室2',
                      label: '训练室2'},
                        { value: '训练室3',
                      label: '训练室3'},
                 ],
                 optiont: [{
                  value: '08:00-09:30',
                  label: '08:00-09:30'
                  }, {
                  value: '10:00-11:30',
                  label: '10:00-11:30'
                  }, {
                  value: '14:00-15:30',
                  label: '14:00-15:30'
                  }, {
                  value: '16:00-17:30',
                  label: '16:00-17:30'
                  }
                 ],
                    addRules: {
                    cname: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    weekday: [
                        {required: true,message: '请选择星期数',trigger: 'blur'},
                    ],
                    time: [
                        {required: true,message: '请选择时间',trigger: 'blur'},
                    ],
                      num: [
                        {required: true, message: '请输入课余量', trigger: 'blur'},
                    ],
                    lesson: [
                        {required: true,message: '请输入课时',trigger: 'blur'},
                    ],
                    address: [
                        {required: true,message: '请输入场地',trigger: 'blur'},
                    ],
                },
            }
        },
          methods:{
              created(){
                 this.getList();
                 this.username=Cookies.get("username");
                 console.log(this.username)
              },
            getCookie() {
            const username = Cookies.get("username");
           },
            async getList(){
                this.queryForm.stuName=Cookies.get("username")
                 const {data:res} = await this.$http.post("myCourse",this.queryForm);
                 this.list =res.list;
               },
              async dropCourse(row){
                    row.stuName= Cookies.get("username");
                        const confimResust=await this.$confirm('确定退课？','提示',{
                confirmButtonText:'确定',
                cancelButtonText:'取消',
                 type:'warning'
                }).catch(err=>err)
                 if(confimResust!='confirm'){
              return this.$message.info("已取消退课");
            }
                     const {data:res} = await this.$http.post("dropCourse",row);
                   if(res=="Success"){
                      this.addDiaLog=false;
                     this.getList()
                     this.$message({
                    message: '退课成功！',
                    type: 'success'
                   });
                    
                   }
                else{
                    this.$message.error('退课失败！');
                }
                   
               },
            
    
          }
}
</script>
<style scoped lang="less" scoped>
  .el-breadcrumb{
        padding-top: 20px;
        margin-bottom: 15px;
        font-size: 17px;
    }
      .input1{
        padding-top: 10px;
        padding-right: 10px;
        padding-left: 20px;
        width: 200px;
    }
      .input2{
        padding-top: 10px;
        padding-right: 10px;
        padding-left: 20px;
        width: 200px;
    }
    .slect1{
      width: 200px;
    }
</style>