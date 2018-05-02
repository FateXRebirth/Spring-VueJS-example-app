import Vue from 'vue'
import Router from 'vue-router'

import BaseRoot from '@/components/BaseRoot'
import BaseIndex from '@/components/BaseIndex'
import BaseNotFound from '@/components/BaseNotFound'

import TheIntro from '@/components/TheIntro'
import ThePerson from '@/components/ThePerson'
import TheCar from '@/components/TheCar'
import ThePost from '@/components/ThePost'

import FirstName from '@/components/el-firstname.vue'
import LastName from '@/components/el-lastname.vue'
import UserName from '@/components/el-username.vue'
import BrandName from '@/components/el-brandname.vue'
import SeriesName from '@/components/el-seriesname.vue'
import Year from '@/components/el-year.vue'
import Title from '@/components/el-title.vue'
import ContactUs from '@/components/el-contactus.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Root',
      component: BaseRoot
    },
    {
      path: '/index',
      name: 'Index',
      component: BaseIndex,
      children: [
        {
          path: '/',
          component: TheIntro
        },
        {
          path: 'person',
          component: ThePerson,
          children: [
            {
              path: 'firstName',
              component: FirstName
            },
            {
              path: 'lastname',
              component: LastName
            },
            {
              path: 'username',
              component: UserName
            }
          ]
        },
        {
          path: 'car',
          component: TheCar,
          children: [
            {
              path: 'brandname',
              component: BrandName
            },
            {
              path: 'seriesname',
              component: SeriesName
            },
            {
              path: 'year',
              component: Year
            }
          ]
        },
        {
          path: 'post',
          component: ThePost,
          children: [
            {
              path: 'username',
              component: UserName
            },
            {
              path: 'title',
              component: Title
            }
          ]
        },
        {
          path: 'contact',
          component: ContactUs
        }
      ]
    },
    {
      path: '/*',
      name: 'NotFound',
      component: BaseNotFound
    }
  ]
})
