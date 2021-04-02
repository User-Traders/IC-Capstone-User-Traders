export default {
  'user': {
    'list': { task: 'get', url: '/boards/list' },
    'listInfinte': { task: 'get', url: '/boards/listInfinte' },
    'listdetail': { task: 'get', url: '/boards/list/#id' },
    'boardCreate':{task:'post',url:'boards/register'},
    'update': { task: 'put', url: '/users/#id' },
    'delete':{ task: 'delete', url: '/users/#id' },
    'login':{ task: 'post', url: '/login/#id' },
    'listdepartment':{task:'get',url:'/departments/list'},
    'signup': {task:'post',url:'/users/signup'},
  }
}