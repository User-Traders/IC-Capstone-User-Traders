export default {
  'user': {
    'list': { task: 'get', url: '/boards/list' },
    'listInfinte': { task: 'get', url: '/boards/listInfinte' },
    'listdetail': { task: 'get', url: '/boards/list/#id' },
    'boardCreate':{task:'post',url:'boards/register'},
    'update': { task: 'put', url: '/users/#id' },
    'delete':{ task: 'delete', url: '/users/#id' },
    'listdepartment':{task:'get',url:'/departments/list'},
    'login': {task:'post',url:'/users/login'},
    'signup': {task:'post',url:'/users/signup'},
  }
}