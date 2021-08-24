<template>
    <div>
        <!--面包屑导航-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>

        </el-breadcrumb>

        <el-row :gutter="50">
            <!--搜索区域-->

            <el-col :span="10" >
                <el-input placeholder="请输入搜索内容" v-model="roleInfo.rolename" clearable @clear="getList">
                    <el-button slot="append" type="primary" icon="el-icon-search" @click="getList()"></el-button>
                </el-input>
            </el-col>
            <!--添加区域-->
            <el-col :span="4">
                <el-button type="primary" @click="addDialogVisible=true" >添加角色</el-button>
            </el-col>
        </el-row>
        <el-table
                :data="roleList" border stripe
                style="width: 100%">
            <el-table-column
                    prop="roleId"
                    label="编号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="roleName"
                    label="角色名称"
                    width="180">
            </el-table-column>
             <el-table-column
                    prop="sort"
                    label="排序"
                    width="180">
            </el-table-column>
           
        <el-table-column
                prop="status"
                label="状态"
                width="180">
                
          <template slot-scope="scope">
          <!-- {{scope.row}}-->
            <el-switch v-model="scope.row.status" @change="changeStatus(scope.row.roleId)" >
                
            </el-switch>
          </template>
        </el-table-column>
        <span>
        </span>
            <el-table-column label="操作"  width="280">
                <template slot-scope="scope">
                        <!--删除-->
                    <el-button  type="danger" icon="el-icon-delete" size="mini" @click="deleteRole(scope.row.roleId)"></el-button>
                      <el-button type="primary" icon="el-icon-edit" size="mini" @click="Menulist(scope.row.roleId)"></el-button>
                

                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="添加角色" :visible.sync="addDialogVisible" width="50%" @close="addDialogClose">
            <el-form :model="addRoleForm" :rules="addRules" ref="addFormRef" label-width="70px">
                <el-form-item label="角色ID" prop="roleId">
                    <el-input v-model="addRoleForm.roleId"></el-input>
                </el-form-item>
                <el-form-item label="角色名" prop="roleName">
                    <el-input v-model="addRoleForm.roleName"></el-input>
                </el-form-item>
                  <el-form-item label="排序" prop="sort">
                    <el-input v-model="addRoleForm.sort"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible=false">取消</el-button>
                 <el-button type="primary" @click="addRole">确定</el-button>
            </span>
        </el-dialog>
     
          <el-dialog title="选择菜单" :visible.sync="editRoleDialogVisible" width="30%" @close="addDialogClose">
          <!--  <el-form :model="editForm" ref="editFormRef" label-width="70px">-->
          <!--  <el-tree
                    :data="menulist1"
                    show-checkbox
                    node-key="id"
                    ref="menu"
                    :props="defaultProps">

            </el-tree>
-->
            <el-input
            placeholder="输入关键字进行过滤"
           v-model="filterText">
           </el-input>
             <el-tree
              class="filter-tree"
            :data="list"
              :default-checked-keys="checkList"
            show-checkbox
            node-key="id"
            :props="defaultProps"
            default-expand-all
              :filter-node-method="filterNode"
             ref="tree"
            
             >
            </el-tree>

            <!--  </el-form>-->
            <span slot="footer" class="dialog-footer">
                <el-button @click="editRoleDialogVisible=false">取消</el-button>
                 <el-button type="primary" @click="change()">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
           watch: {
      filterText(val) {
        this.$refs.tree.filter(val);
      }
    },
        name: "rights",
        created() {
            this.getList();
            
        },
         
        data(){
            return{
                filterText:'',
                //查询信息实体
                roleInfo: {
                    rolename: "",
                },
                roleList: [],
                menulist1:[],
                addDialogVisible:false,//对话框状态
                iscollapse:false,
                activePath:'/right',
                addRoleForm:{
                    roleId:'',
                    roleName:''
                },
                editRoleDialogVisible:false,
                editRoleForm:{

                },
                addRules: {
                   roleId: [
                        {required: true, message: '请输入id', trigger: 'blur'},
                    ],
                    roleName: [
                        {required: true, message: '请输入角色名', trigger: 'blur'},
                    ],
                },
                editRoleId:'',
                 defaultProps: {
                 children: 'slist',
                 label: 'title'
                },
                list:[],
                checkList:[],
                SelectedList:[],
                  changeForm:{
                    list:'',
                    roleId:''
                },
            }
        },
        methods:{
            async  getList(){
                const rolename =this.roleInfo.rolename;
                const {data:res} = await this.$http.post("role?rolename="+rolename);
                this.roleList =res.role; //用户列表数据封装
                console.log(rolename)
            },


            addDialogClose(){
                this.$refs.addFormRef.resetFields();
            },
            editRoleDialogClose(){
                this.$refs.editRoleFormRules.resetFields();
            },
            addRole(){
                this.$refs.addFormRef.validate(async valid=>{
                    if(!valid) return ;
                    this.addDialogVisible=true;
                    const {data:res} = await this.$http.post("addRole",this.addRoleForm);
                    console.log(this.addRoleForm.roleId)
                    if(res=="exist"){
                        return this.$message.error("用户名或id重复！");
                    }
                    if(res=="erro"){
                        return this.$message.error("添加失败！");
                    }
                    this.$message.success("添加成功！");
                    this.addDialogVisible=false;
                    this.getList();
                });
            },
            async deleteRole(roleId){
                const confimResust=await this.$confirm('确定删除？','提示',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).catch(err=>err)
                if(confimResust!='confirm'){
                    return this.$message.info("已取消删除");
                }
                const {data:res}=await this.$http.delete("delete?roleId="+roleId);
                /*     const {data:res}=await this.$http.delete("deleteUser");*/
                console.log(res);
                if(res!="success"){
                    return this.$message.error("删除失败！");
                }
                this.$message.success("删除成功！");
                this.getList();
            
            },
             async Menulist(roleId){
                /* const username = Cookies.get("username");*/
                console.log("1111111111111111111")
                const {data:res}=await this.$http.get("menuList");
                const {data:res1}=await this.$http.get("selectRoleMenu?roleId="+roleId);
                this.list =res.menuList;  //数据回填
                this.checkList=res1.list;
                this.editRoleId=roleId;
                this.editRoleDialogVisible=true;
            },
            filterNode(value, list) {
               if (!value) return true;
                return list.title.indexOf(value) !== -1;

                },
            change(){
                this.changeForm.list=this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys());
                this.changeForm.roleId=this.editRoleId;
                this.$http.put("changeRoleMenu",this.changeForm);
                console.log(this.SelectedList)
                   this.$message.success("修改成功！");
                 this.editRoleDialogVisible=false;

            },
            async changeStatus(roleId){
                const {data:res}=await this.$http.get("changeStatus?roleId="+roleId);
                  if(res!="success"){
                    return this.$message.error("更改失败！");
                }
                this.$message.success("更改成功！");
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
