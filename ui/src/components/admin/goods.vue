<template>
  <div>
     <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>饮食管理</el-breadcrumb-item>
            <el-breadcrumb-item>卡路里</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="demo-input-suffix">
            <el-input class="input1"
                    placeholder="请输入食品名称"
                     v-model="queryForm.name"  clearable @clear="getSpotrList" >
            </el-input>
            <el-button class="input2" slot="append" type="primary" icon="el-icon-search"  @click="getSpotrList()" ></el-button>
                <el-button type="primary"   icon="el-icon-circle-plus"  @click="addDiaLog=true" >添加</el-button>
        </div>
           <el-table
                :data="list" border stripe
                style="width: 100%">



            <el-table-column aria-disabled="false"
                    prop="id"
                    label="编号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="名称"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="calories"
                    label="卡路里"
                    width="200">
            </el-table-column>

            <el-table-column
                    prop="type"
                    label="类型"
                    width="200">
            </el-table-column>
            
            <el-table-column
                    prop="unit"
                    label="单位"
                    width="180">
            </el-table-column>
            <el-table-column label="操作"  width="280">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row)"></el-button>
                    <el-button  type="danger" icon="el-icon-delete" size="mini" @click="deletePlay(scope.row.id)"></el-button>

                    <!--删除-->

                </template>
            </el-table-column>
        </el-table>
         <el-dialog title="添加" :visible.sync="addDiaLog" width="24%" @close="addDialogClose">
            <el-form :model="addForm" :rules="addRules" ref="addFormRef" label-width="70px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="addForm.name" ></el-input>
                </el-form-item>
                <el-form-item label="卡路里" prop="calories">
                    <el-input v-model="addForm.calories"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type">
                    <el-select v-model="addForm.type" placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.label"
                      :label="item.label"
                      :value="item.label">
                    </el-option>
                 </el-select>
                </el-form-item>
                  <el-form-item label="单位" prop="unit">
                    <el-input v-model="addForm.unit"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDiaLog=false">取消</el-button>
                 <el-button type="primary" @click="addCalories">确定</el-button>
            </span>
        </el-dialog>

         <el-dialog title="修改" :visible.sync="editDiaLog" width="24%" @close="addDialogClose">
            <el-form :model="editForm" :rules="addRules" ref="addFormRef" label-width="70px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="editForm.name" ></el-input>
                </el-form-item>
                <el-form-item label="卡路里" prop="calories">
                    <el-input v-model="editForm.calories"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type">
                    <el-select v-model="editForm.type" placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.label"
                      :label="item.label"
                      :value="item.label">
                    </el-option>
                 </el-select>
                </el-form-item>
                  <el-form-item label="单位" prop="unit">
                    <el-input v-model="editForm.unit"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDiaLog=false">取消</el-button>
                 <el-button type="primary" @click="editCalories">确定</el-button>
            </span>
        </el-dialog>
  </div>
</template>

<script>
export default{
  data() {
      return {
        editDiaLog:false,
        addDiaLog:false,
         list:[],
        queryForm:{
                    name:'',
                },
      addForm:{
              id:'',
              name:'',
              type:'',
              calories:'',
              unit:'',
                },
                editForm:{
              id:'',
              name:'',
              type:'',
              calories:'',
              unit:'',
                },
           options: [{
          value: '选项1',
          label: '肉类'
        }, {
          value: '选项2',
          label: '水果'
        }, {
          value: '选项3',
          label: '蔬菜'
        }, {
          value: '选项4',
          label: '其他'
        }],
         addRules: {
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    calories: [
                        {required: true, message: '请输入卡路里', trigger: 'blur'},
                    ],
                    unit: [
                        {required: true, message: '请输入单位', trigger: 'blur'},
                    ],
                     type: [
                        {required: true, message: '请选择类型', trigger: 'blur'},
                    ],
                },
      }
},
created(){
this.getSpotrList();
},
methods: {
          async getSpotrList(){
            console.log(this.queryForm)
            const {data:res} = await this.$http.get("calories/getCalories?name="+this.queryForm.name);
                 this.list =res.data;
            },



             async   deletePlay(id){
                const confimResust=await this.$confirm('确定删除？','提示',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).catch(err=>err)
                if(confimResust!='confirm'){
                    return this.$message.info("已取消删除");
                }
                const {data:res} = await this.$http.post("calories/delete?id="+id);
                if(res.status==400){
                     this.$message.error("删除失败！");
                    this.getSpotrList();
                }
                this.$message.success("删除成功！");
                this.getSpotrList();
            },



             addDialogClose(){
                this.$refs.addFormRef.resetFields();
            },


            addCalories(){
                this.$refs.addFormRef.validate(async valid=> {
                    if (!valid) return;
                    const {data:res} = await this.$http.post("calories/addCalories",this.addForm);
                    if(res.status==400){
                     this.$message.error("添加失败");
                    this.getSpotrList();
                }
                this.$message.success("添加成功！");
                 this.addDiaLog=false;
                this.getSpotrList();

                })
            },

            edit(row){
                this.editForm=row;
               this.editDiaLog=true;
            },


            editCalories(){
                this.$refs.addFormRef.validate(async valid=> {
                    if (!valid) return;
                    const {data:res} = await this.$http.post("calories/edit",this.editForm);
                    if(res.status==400){
                     this.$message.error("修改失败");
                    this.getSpotrList();
                }
                this.$message.success("修改成功！");
                 this.editDiaLog=false;
                this.getSpotrList();

                })
            },
    },

}
</script>
   
<style  scoped lang="less" scoped>
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
        padding-left: 20px;
        padding-right: 20px;
    }
</style>
