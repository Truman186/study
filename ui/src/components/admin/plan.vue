<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>饮食管理</el-breadcrumb-item>
      <el-breadcrumb-item>饮食计划</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="demo-input-suffix btn" >
       <el-button
       class="btn1"
        type="primary"
        
        @click="intelligent()"
        >智能生成饮食计划</el-button
      >
      <el-button
        type="primary"
        icon="el-icon-circle-plus"
        @click="plan()"
        >添加</el-button
      >
    </div>
    <el-table :data="list" border stripe style="width: 100%">
      <el-table-column aria-disabled="false" prop="id" label="编号" width="150">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="170">
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="170">
      </el-table-column>

      <el-table-column prop="meals" label="餐次" width="170"> </el-table-column>

      <el-table-column prop="plan" label="饮食计划" width="280">
      </el-table-column>
        <el-table-column prop="sumCalories" label="总卡路里" width="170"> </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="deletePlay(scope.row.id)"
          ></el-button>

          <!--删除-->
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="添加"
      :visible.sync="addDiaLog"
      width="80%"
      @close="addDialogClose"
    >
    <el-form :model="addForm" :rules="addRules" ref="addFormRef" label-width="70px">
        <el-row class="fltest">
            <el-col :span="8">
                <el-form-item label="餐次" prop="name" class="form1">
                     <el-select v-model="addForm.meals" placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.label"
                      :label="item.label"
                      :value="item.label">
                    </el-option>
                     </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="8">
                <el-form-item label="备注" prop="remark" class="form2">
                    <el-input v-model="addForm.remark"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="8"></el-col>
        </el-row>
    </el-form>
      <el-table ref="multipleTable" :data="caloriesList" border stripe style="width: 100%"     @selection-change="handleSelectionChange">
       <el-table-column
      type="selection"
      width="55">
    </el-table-column>
        <el-table-column
          aria-disabled="false"
          prop="id"
          label="编号"
          width="180"
          
        >
        </el-table-column>
        <el-table-column prop="name" label="名称" width="180">
        </el-table-column>
        <el-table-column prop="calories" label="卡路里" width="200">
        </el-table-column>
        <el-table-column prop="quantity" label="数量" width="250">
         <template slot-scope="scope">
             
          <el-input v-model="scope.row.quantity"></el-input>
        </template>
        </el-table-column>
        <el-table-column prop="type" label="类型" width="200">
        </el-table-column>

        <el-table-column prop="unit" label="单位" width="180">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDiaLog = false">取消</el-button>
        <el-button type="primary" @click="addCalories">确定</el-button>
      </span>
    </el-dialog>


      <!--智能生成饮食计划-->
     <el-dialog
      title="智能生成饮食计划"
      :visible.sync="intelligentDiaLog"
      width="80%"
      @close="intelligentCaloriesClose"
    >
    <el-form :model="addForm" :rules="addRules" ref="addFormRef"  label-width="70px">
        <el-row class="fltest">
            <el-col :span="8">
                <el-form-item label="餐次" prop="name" class="form1">
                     <el-select v-model="intelligentForm.meals" placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.label"
                      :label="item.label"
                      :value="item.label">
                    </el-option>
                     </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="10">
                <el-row style="display: flex;">
                    <el-col span="6">
                         <p>卡路里范围：</p>
                    </el-col>
                    <el-col span="12">
                         <el-slider
                            v-model="intelligentForm.value"
                            range
                            show-stops
                            :min="100"
                            :max="2000">
                            </el-slider>

                    </el-col>
                    <el-col span="6" style="padding-left:40px">
                        <el-button @click="intelligentCalories"  type="primary" >生成计划</el-button>
                    </el-col>
                </el-row>
              
                    <!-- <div class="block">
                          <p>卡路里范围</p>
                            <el-slider
                            v-model="intelligentForm.value"
                            range
                            show-stops
                            :max="2000">
                            </el-slider>
                    </div> -->
            </el-col>
        </el-row>
    </el-form>
      <el-table ref="multipleTable" :data="intelligentList" border stripe style="width: 100%"     @selection-change="handleSelectionChange">
        <el-table-column
          aria-disabled="false"
          prop="id"
          label="编号"
          width="180"
          
        >
        </el-table-column>
        <el-table-column prop="name" label="名称" width="180">
        </el-table-column>
        <el-table-column prop="calories" label="卡路里" width="200">
        </el-table-column>
        <el-table-column prop="quantity" label="数量" width="250">
        
        </el-table-column>
        <el-table-column prop="type" label="类型" width="200">
        </el-table-column>

        <el-table-column prop="unit" label="单位" width="180">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-row>
           <el-col style="display:flex;align-items: center;">
               总卡路里:<el-input class="input" size="small" disabled style="width:80px;padding-left:15px;" v-model="this.sumCalories"></el-input>
           </el-col>
        </el-row>
        
            <!-- <el-col> -->
                <el-button @click="intelligentDiaLog = false ">取消</el-button>
                <el-button type="primary" @click="addIntelligentCalories">确定</el-button>
            <!-- </el-col> -->
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Cookies from "js-cookie";
export default {
  data() {
    return {
        intelligentForm:{
            value:[0,2000],
            meals:'',
            username:'',
             remark: "智能生成的饮食计划",
            list:[]
        },
        sumCalories:0,
        intelligentDiaLog:false,
    multipleSelection:[],
      username: "",
      editDiaLog: false,
      addDiaLog: false,
      list: [],
      intelligentList:[],
      caloriesList: [],
      queryForm: {
        name: "",
      },
      addForm: {
        meals: "",
        remark: "",
        username: "",
        list:[]
      },
      editForm: {
        id: "",
        name: "",
        type: "",
        calories: "",
        unit: "",
      },
      options: [
        {
          value: "选项1",
          label: "早餐",
        },
        {
          value: "选项2",
          label: "午餐",
        },
        {
          value: "选项3",
          label: "晚餐",
        },
        {
          value: "选项4",
          label: "夜宵",
        },
         {
          value: "选项5",
          label: "其他",
        },
      ],
      addRules: {
        meals: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getSpotrList();
    this.username = Cookies.get("username");
    this.addForm.username=this.username
  },
  methods: {
      intelligent(){
          this.intelligentDiaLog=true
          this.intelligentList=[]
      },
    async getSpotrList() {
      console.log(this.queryForm);
      this.username = Cookies.get("username");
      const { data: res } = await this.$http.get(
        "calories/queryPlan?username=" + this.username
      );
      this.list = res.data;
    },
    async plan(){
        this.addDiaLog=true;
          const {data:res} = await this.$http.get("calories/plan?name=");
                 this.caloriesList =res.data;
    },

   toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
        this.addForm.list=this.multipleSelection
       console.log(JSON.stringify(this.addForm.list))
      },
    

    async deletePlay(id) {
      const confimResust = await this.$confirm("确定删除？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).catch((err) => err);
      if (confimResust != "confirm") {
        return this.$message.info("已取消删除");
      }
      const { data: res } = await this.$http.post("calories/remove?id=" + id);
      if (res.status == 400) {
           this.getSpotrList();
       return this.$message.error("删除失败！");
      }
      this.$message.success("删除成功！");
      this.getSpotrList();
    },

    addDialogClose() {
       this.addDiaLog=false;
    },
    intelligentCaloriesClose(){
        this.intelligentDiaLog=false
        this.intelligentList.resetFields();
        this.intelligentList=[]
    },

   async addCalories() {
       this.$refs.addFormRef.validate(async valid=> {
                console.log("this.addForm",this.addForm)
             const { data: res } = await this.$http.post("calories/addPlan",this.addForm);
              if (res.status == 400) {
                this.$message.error("添加失败！");
                 this.addDiaLog=false;
                this.getSpotrList();
            }
            this.$message.success("添加成功！");
             this.addDiaLog=false;
            this.getSpotrList();
 
       })
      
    },
    async addIntelligentCalories(){
        this.intelligentForm.list=this.intelligentList
             const { data: res } = await this.$http.post("calories/addPlan",this.intelligentForm);
              if (res.status == 400) {
                this.$message.error("添加失败！");
                 this.addDiaLog=false;
                this.getSpotrList();
            }
            this.$message.success("添加成功！");
             this.intelligentDiaLog=false;
             this.intelligentList=[];
            this.getSpotrList();
    },

    async intelligentCalories(){
        this.intelligentList=[];
         this.value=[0,2000],
        this.intelligentForm.username=this.username
        console.log("this.addForm",this.intelligentForm)
          const { data: res } = await this.$http.post("calories/intelligentCalories",this.intelligentForm);
          this.intelligentList=res.data.list;
          this.sumCalories=res.data.sumCalories
    },
    edit(row) {
      this.editForm = row;
      this.editDiaLog = true;
    },
  },
};
</script>
   
<style  scoped lang="less" scoped>
.el-breadcrumb {
  padding-top: 20px;
  margin-bottom: 15px;
  font-size: 17px;
}
.input1 {
  padding-top: 10px;
  padding-right: 10px;
  padding-left: 20px;
  width: 200px;
}
.input2 {
  padding-left: 20px;
  padding-right: 20px;
}
.form1{
    width: 300px;
}
.form2{
    width: 350px;
}
.btn1{
width: 150px;
}
.btn{
    padding-left: 30px;
}
</style>
