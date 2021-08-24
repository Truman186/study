<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>运动平台</el-breadcrumb-item>
            <el-breadcrumb-item>运动计划</el-breadcrumb-item>
        </el-breadcrumb>


        <div class="demo-input-suffix">

                <span class="demonstration"></span>
                <el-date-picker class="timeInput"
                        v-model="queryForm.date"
                        type="date"
                        placeholder="选择日期">
                </el-date-picker>

            <el-input class="input1"
                    placeholder="请输入运动名称"
                    v-model="queryForm.name"  clearable @clear="getSpotrList">
            </el-input>
            <el-button class="input2" slot="append" type="primary" icon="el-icon-search"  @click="getSpotrList()" ></el-button>
                <el-button type="primary"   icon="el-icon-circle-plus" @click="addDiaLog=true" >添加</el-button>
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
                    prop="time"
                    label="时间"
                    width="200">
            </el-table-column>

            <el-table-column
                    prop="site"
                    label="场地"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="remake"
                    label="备注"
                    width="180">
            </el-table-column>
            <el-table-column label="操作"  width="280">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)"></el-button>
                    <el-button  type="danger" icon="el-icon-delete" size="mini" @click="deletePlay(scope.row.id)"></el-button>

                    <!--删除-->

                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="添加运动计划" :visible.sync="addDiaLog" width="24%" @close="addDialogClose">
            <el-form :model="addForm" :rules="addRules" ref="addFormRef" label-width="70px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="addForm.name" ></el-input>
                </el-form-item>
                <el-form-item label="时间" prop="date">
                    <el-date-picker
                            v-model="addForm.date"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="场地" prop="site">
                    <el-input v-model="addForm.site"></el-input>
                </el-form-item>
                <el-form-item label="备注" prop="remake">
                    <el-input v-model="addForm.remake"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDiaLog=false">取消</el-button>
                 <el-button type="primary" @click="addSport">确定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="更改运动计划" :visible.sync="editDiaLog" width="24%" @close="addDialogClose">
            <el-form :model="editForm" :rules="editRules" ref="addFormRef" label-width="70px">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="editForm.name" ></el-input>
                </el-form-item>
                <el-form-item label="时间" prop="time">
                    <el-date-picker
                            v-model="editForm.time"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="场地" prop="site">
                    <el-input v-model="editForm.site"></el-input>
                </el-form-item>
                <el-form-item label="备注" prop="remake">
                    <el-input v-model="editForm.remake"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDiaLog=false">取消</el-button>
                 <el-button type="primary" @click="editPlan" >确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import Cookies from "js-cookie";
    export default {
        name: "sport.vue",
        created() {
            this.getSpotrList();
        },
        mounted(){
            this.getCookie();
        },
        data(){
            return{
                addDiaLog:false,
                list:[],
                input1: '',
                input2: '',
                queryForm:{
                    time:'',
                    date:'',
                    username:'',
                },
                addForm:{
                    name:'',
                    username:'',
                    date:'',
                    site:'',
                    remake:'',
                },
                editForm:{
                    name:'',
                    time:'',
                    site:'',
                    remake:'',
                    date:''
                },
                editDiaLog:false,
                addRules: {
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    date: [
                        {required: true, message: '请输入时间', trigger: 'blur'},
                    ],
                    site: [
                        {required: true, message: '请输入场地', trigger: 'blur'},
                    ],
                },
                editRules: {
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    date: [
                        {required: true, message: '请输入时间', trigger: 'blur'},
                    ],
                    site: [
                        {required: true, message: '请输入场地', trigger: 'blur'},
                    ],
                },
            }
        },
        methods:{
            getCookie() {
                const username = Cookies.get("username");
            },
            async getSpotrList(){
                this.queryForm.username =Cookies.get("username");
                console.log(this.queryForm.name)
            const {data:res} = await this.$http.post("getSportList",this.queryForm);
                 this.list =res.list;
                console.log("22222222222222222222222")
                console.log(list)
            },
            addSport(){
                this.$refs.addFormRef.validate(async valid=> {
                    if (!valid) return;
                    this.addForm.username = Cookies.get("username")
                    console.log(this.addForm.username)
                    console.log(this.addForm.name)
                    const {data:res} = await this.$http.post("addSportPlay",this.addForm);
                    if(res=="erro"){
                        return this.$message.error("添加失败！");}
                    else if(res=="defaut"){
                        return this.$message.error("时间不可为空！")
                    }
                        this.$message.success("添加成功！");
                        this.addDiaLog=false;
                    this.$refs.addFormRef.resetFields();
                        this.getSpotrList();

                })
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
                const {data:res} = await this.$http.post("deletePlay?id="+id);
                if(res!="success"){
                    return this.$message.error("删除失败！");
                }
                this.$message.success("删除成功！");
                this.getSpotrList();
            },
            async edit(id){
                const {data:res} = await this.$http.post("queryById?id="+id);
                this.editForm=res;//查询出来的用户信息反添到表单里
                console.log(res);
                this.editDiaLog=true;list
            },
            addDialogClose(){
                this.$refs.addFormRef.resetFields();
            },
            async editPlan(){
                this.editForm.date=this.editForm.time
                const {data:res} = await this.$http.post("edit",this.editForm)
                if(res!="success"){
                    return this.$message.error("更新失败！");
                }
                this.editDiaLog=false;
                this.$message.success("更新成功！");
                this.getSpotrList();
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
        padding-left: 20px;
        padding-right: 20px;
    }
    .timeInput{
        padding-left: 5px;
    }

</style>
