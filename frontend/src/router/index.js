import Vue from 'vue'
import Router from 'vue-router'

import Root from '@/components/Root'
import Index from '@/components/Index'
import NotFound from '@/components/NotFound'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Root',
      component: Root
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/*',
      name: 'NotFound',
      component: NotFound
    }
  ]
})
