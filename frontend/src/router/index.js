import Vue from 'vue'
import Router from 'vue-router'

import BaseRoot from '@/components/BaseRoot'
import BaseIndex from '@/components/BaseIndex'
import BaseNotFound from '@/components/BaseNotFound'

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
      component: BaseIndex
    },
    {
      path: '/*',
      name: 'NotFound',
      component: BaseNotFound
    }
  ]
})
