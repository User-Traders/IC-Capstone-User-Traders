export default {
  'user': {
    'list': { task: 'get', url: '/boards/list' },
    'item': { task: 'get', url: '/users/#id' },
    'add':{task:'post', url:'/users'},
    'update': { task: 'put', url: '/users/#id' },
    'delete':{ task: 'delete', url: '/users/#id' },
    'login':{ task: 'post', url: '/login/#id' },
  }
}