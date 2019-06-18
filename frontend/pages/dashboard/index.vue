<template>
  <section class="container">
    <hr class="hr-30">
      <Breadcrumb />
    <hr class="hr-30">
    <el-container>
      <el-aside width="200px">
        <SideMenu />
      </el-aside>
      <el-main>   
        <Header title="News" />
        <hr class="hr-30">
        <table>
          <thead>
            <tr>
              <td class="type">分類</td>
              <td class="date">日期</td>
              <td class="title">標題</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="news in News" :key="news.date">
              <td class="type">{{ news.type == 1 ? "新聞" : "其他" }}</td>
              <td class="date">{{ news.date }}</td>
              <td class="title">
                <a href="#">{{ news.title}}</a>
              </td>
            </tr>
          </tbody>
        </table>
      </el-main>
    </el-container>
  </section>
</template>

<script>
import SideMenu from '~/components/SideMenu.vue';
import Breadcrumb from '~/components/Breadcrumb.vue';
import Header from '~/components/Header.vue';

export default {
  components: {
    SideMenu,
    Breadcrumb,
    Header
  },
  middleware: 'auth',
  async asyncData({ app }) {
    let Result = await app.$axios.get('/api/news');
    if(Result.data.returnCode == 0) {
      return {
        News: Result.data.returnData.news
      }
    } else {
        throw new Error(Result.data.returnMessage)
    }
  },
  data() {
    return {
      News: [],
    }
  }
}
</script>

<style lang="scss" scoped>
table {
  width: 100%;
  text-align: center;
  thead {
    color: #909399;
  }
  tbody {
    font-size: 14px;
    color: #606266;
  }
  thead, tbody {
    tr {
      &:nth-child(even) {
        background-color: rgb(250, 250, 250);
      }
    }
    td {
      padding: 15px 0px;
      border: 1px solid #ebeef5;
    }
    td.type {
      width: 100px;
    }
    td.date {
      width: 100px;
    }
    td.title {
      text-align: left;
      padding-left: 20px;
      a {
        text-decoration: none;
      }
    }
  }
}
</style>
