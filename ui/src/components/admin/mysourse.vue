<template>
   <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>运动平台</el-breadcrumb-item>
            <el-breadcrumb-item>课程管理</el-breadcrumb-item>
        </el-breadcrumb>
   
          <el-table
                :data="list" border stripe
                style="width: 100%">
            <el-table-column aria-disabled="false"
                    prop="time"
                    label=""
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="day1"
                    label="星期一"
                    width="120">
            </el-table-column>
               <el-table-column
                    prop="day2"
                    label="星期二"
                    width="120">
            </el-table-column>
              <el-table-column
                    prop="day3"
                    label="星期三"
                    width="120">
            </el-table-column>
                <el-table-column
                    prop="day4"
                    label="星期四"
                    width="120">
            </el-table-column>
                 <el-table-column
                    prop="day5"
                    label="星期五"
                    width="120">
            </el-table-column>
              <el-table-column
                    prop="day6"
                    label="星期六"
                    width="120">
            </el-table-column>
               <el-table-column
                    prop="day7"
                    label="星期天"
                    width="120">
            </el-table-column>
    
        </el-table>
       
   </div>
</template>
<script>
  import Cookies from "js-cookie";
export default {
     name: "mysourse.vue",
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
                 const {data:res} = await this.$http.post("mysourceList?username="+Cookies.get("username"));
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