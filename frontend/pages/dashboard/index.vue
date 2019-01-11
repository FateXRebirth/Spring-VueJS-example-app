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
            <tr v-for="news in Data" :key="news.date">
              <td class="type">{{ news.type}}</td>
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
  data() {
    return {
      Data: [{
        date: '2018/09/07',
        type: '新聞',
        title: '8月份★abc網路好店成交抽萬元購車金★得獎名單'
      }, {
        date: '2018/09/07',
        type: '新聞',
        title: '【全新微電影 劇情你決定 】得獎名單'
      }, {
        date: '	2018/08/31	',
        type: '新聞',
        title: '【成交贈千點】你的舊愛 一堆人搶著要！成交贈UUPON 1千點唷！'
      }, {
        date: '2018/08/31',
        type: '新聞',
        title: '慶祝abc好車網勇奪中古車網站三冠王，好店成交月月抽UUPON 1萬點！'
      }, {
        date: '	2018/08/28',
        type: '新聞',
        title: '[收費公告] abc好車網 刊登車格機制 9/3(一) 正式收費'
      }, {
        date: '	2018/06/22',
        type: '新聞',
        title: 'abc好車網 全新改版 正式上線！'
      }]
    }
  }
}
</script>

<style lang="scss" scoped>
.el-container {
  min-height: 750px;
}
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
