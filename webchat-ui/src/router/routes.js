const routes = [
    {
        name: 'main',
        path: '/main',
        component: () => import('@/views/main/index.vue')
    },
    {
        name: 'login',
        path: '/',
        component: () => import('@/components/login.vue')
    },

];

export default routes;