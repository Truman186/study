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
         
                 <el-button class="input3" slot="append" type="primary" icon="el-icon-search"   @click="getList()"></el-button>
                <el-button type="primary"   icon="el-icon-circle-plus" @click="addDiaLog=true"  >添加</el-button>
                   <el-button type="primary" class="btn1"   icon="el-icon-date" @click="showmyCourse()">我的课程表</el-button>
       </div>
          <el-table
                :data="list" border stripe
                style="width: 100%">
            <el-table-column aria-disabled="false"
                    prop="cid"
                    label="编号"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="cname"
                    label="课程名称"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="weekday"
                    label="星期"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="time"
                    label="时间"
                    width="130">
            </el-table-column>
             <el-table-column
                    prop="num"
                    label="课余量"
                    width="130">
            </el-table-column>
             <el-table-column
                    prop="lesson"
                    label="课时"
                    width="130">
            </el-table-column>
             <el-table-column
                    prop="address"
                    label="场地"
                    width="130">
            </el-table-column>
             <el-table-column
                    prop="remake"
                    label="备注"
                    width="130">
            </el-table-column>
         <el-table-column label="操作"  width="175">
                <template slot-scope="scope">
                    <el-button type="primary" size="small" @click="show(scope.row.cid)">查看选课名单</el-button>
                    <!--删除-->

                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="添加课程"  :visible.sync="addDiaLog" inline="true" width="50%" @close="addDialogClose">
            <el-form :inline="true" :model="addForm"   :rules="addRules" ref="addFormRef" label-width="70px">
         
                 <el-form-item label="课程名" prop="cname" >
                    <el-input v-model="addForm.cname" ></el-input>
                </el-form-item>
               
                  <el-form-item label="星期数" prop="weekday">
                    <el-select v-model="addForm.weekday" placeholder="请选择" class="slect1">
                    <el-option
                    v-for="item in options"
                   :key="item.value"
                   :label="item.label"
                   :value="item.value">
                  </el-option>
                  </el-select>
                </el-form-item>
             
                  <el-form-item label="时间" prop="time">
                   <el-select v-model="addForm.time" placeholder="请选择" class="slect1">
                    <el-option
                    v-for="item in optiont"
                   :key="item.value"
                   :label="item.label"
                   :value="item.value">
                  </el-option>
                  </el-select>
                </el-form-item>
                 <el-form-item label="课余量" prop="num">
                    <el-input v-model="addForm.num" ></el-input>
                </el-form-item>
                 <el-form-item label="课时" prop="lesson">
                    <el-input v-model="addForm.lesson" ></el-input>
                </el-form-item>
                 <el-form-item label="场地" prop="address">
                   <el-select v-model="addForm.address" placeholder="请选择" class="slect1">
                    <el-option
                    v-for="item in optionad"
                   :key="item.value"
                   :label="item.label"
                   :value="item.value">
                  </el-option>
                  </el-select>
                </el-form-item>
                 <el-form-item label="备注" prop="remake">
                    <el-input v-model="addForm.remake" ></el-input>
                </el-form-item>
            </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click="addDiaLog=false">取消</el-button>
                 <el-button type="primary" @click="addCourse">确定</el-button>
            </span>
        </el-dialog>
          <el-dialog title="我的课程表"  :visible.sync="myCourseDiaLog" inline="true" width="60%" @close="addDialogClose">
               <el-table
                :data="myCourselist" border stripe
                style="width: 100%">
            <el-table-column aria-disabled="false"
                    prop="time"
                    label=""
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="day1"
                    label="星期一"
                    width="100">
            </el-table-column>
               <el-table-column
                    prop="day2"
                    label="星期二"
                    width="100">
            </el-table-column>
              <el-table-column
                    prop="day3"
                    label="星期三"
                    width="100">
            </el-table-column>
                <el-table-column
                    prop="day4"
                    label="星期四"
                    width="100">
            </el-table-column>
                 <el-table-column
                    prop="day5"
                    label="星期五"
                    width="100">
            </el-table-column>
              <el-table-column
                    prop="day6"
                    label="星期六"
                    width="100">
            </el-table-column>
               <el-table-column
                    prop="day7"
                    label="星期天"
                    width="100">
            </el-table-column>
    
        </el-table>
          </el-dialog>
        <el-dialog title="选课名单"  :visible.sync="showDiaLog" inline="true" width="30%" @close="showDialogClose">
           <el-table
                :data="showList" border stripe
                >
           <el-table-column type="index"  label="编号"></el-table-column>
            <el-table-column aria-disabled="false"
                    prop="username"
                    label="姓名"
                    width="160">
            </el-table-column>
             <el-table-column aria-disabled="false"
                    prop="email"
                    label="邮箱"
                    width="160">
            </el-table-column>
           
        </el-table>
        </el-dialog>
   </div>
</template>
<script>
  import Cookies from "js-cookie";
export default {
     name: "course.vue",
        created() {
           this.getList();
        },
         mounted(){
          this.getCookie();
    },
        data(){
            return{
                queryForm:{
                    cname:'',
                    username:'',
                },
                list:[],
                showList:[],
                myCourselist:[],
                addDiaLog:false,
                showDiaLog:false,
                myCourseDiaLog:false,
                addForm:{
                    cname:'',
                    weekday:'',
                    time:'',
                    num:'',
                    lesson:'',
                    address:'',
                    remake:'',
                    username:''
                },
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
            getCookie() {
            const username = Cookies.get("username");
           },
            async getList(){
             this.queryForm.username = Cookies.get("username");
                 const {data:res} = await this.$http.post("courseManage",this.queryForm);
                 this.list =res.list;
               },
              async addCourse(){
                 debugger;
                  this.addForm.username=Cookies.get("username");
                   const {data:res} = await this.$http.post("addCourse",this.addForm);
                   if(res=="Success"){
                      this.addDiaLog=false;
                     this.getList()
                     this.$message({
                    message: '添加成功',
                    type: 'success'
                   });
                    
                   }
                else{
                    this.$message.error('时间或者地点冲突');
                }
                   
               },
            async show(cid){
               this.showDiaLog=true
               const {data:res} = await this.$http.post("sourceList?cid="+cid);
               this.showList=res.list
             },
             async showmyCourse(){
               this.myCourseDiaLog=true
              const {data:res} = await this.$http.post("mycourceList?username="+Cookies.get("username"));
              this.myCourselist=res.list
             }
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