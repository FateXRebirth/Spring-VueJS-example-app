<template>
    <div class="aside">
        <el-row>
            <el-col>
                <el-menu
                default-active="2"
                class="el-menu-vertical-demo menu"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
                <el-submenu index="1">
                    <template slot="title">
                    <i class="el-icon-info"></i>
                    <span>Search Person</span>
                    </template>  
                    <router-link to="/index/person/firstname" class="menu-link"><el-menu-item index="1-1">Using First Name</el-menu-item></router-link>
                    <router-link to="/index/person/lastname" class="menu-link"> <el-menu-item index="1-2">Using Last Name</el-menu-item></router-link>
                    <router-link to="/index/person/username" class="menu-link"> <el-menu-item index="1-3">Using Username</el-menu-item></router-link>        
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">
                    <i class="el-icon-search"></i>
                    <span>Search Cars</span>
                    </template>    
                    <router-link to="/index/car/brandname" class="menu-link"><el-menu-item index="2-1">Using Brand Name</el-menu-item></router-link>
                    <router-link to="/index/car/seriesname" class="menu-link"><el-menu-item index="2-2">Using Series Name</el-menu-item></router-link>
                    <router-link to="/index/car/year" class="menu-link"><el-menu-item index="2-3">Using Year</el-menu-item></router-link>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title">
                    <i class="el-icon-document"></i>
                    <span>Search Posts</span>
                    </template>
                    <router-link to="/index/post/username" class="menu-link"><el-menu-item index="3-1">Using Username</el-menu-item></router-link>
                    <router-link to="/index/post/title" class="menu-link"><el-menu-item index="3-2">Using Title</el-menu-item></router-link>
                </el-submenu>
                <router-link to="/index/contact" class="menu-link">
                <el-menu-item index="4">
                    <i class="el-icon-edit"></i>
                    <span>Contact Us</span>
                </el-menu-item>
                </router-link>
                </el-menu>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
  name: 'AppSidebar'
}
</script>

<style>

.aside {
    background-color: #545c64;
    height: 689px;
    width: 100%;
}

.menu {
    width: 100%;
}

.menu-link {
    text-decoration: unset;
}

</style>

