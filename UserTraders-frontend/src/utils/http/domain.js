export default {
  'user': {
    'list': { task: 'get', url: '/boards/list' },
    'listInfinte': { task: 'get', url: '/boards/listInfinte' },
    'listdetail': { task: 'get', url: '/boards/list/#id' },
    'boardCreate': { task: 'post', url: '/boards/register' },
    'update': { task: 'put', url: '/users/#id' },
    'delete': { task: 'delete', url: '/users/#id' },
    'listdepartment': { task: 'get', url: '/departments/list' },
    'login': { task: 'post', url: '/users/login' },
    'signup': { task: 'post', url: '/users/signup' },
    'logout': { task: 'get', url: '/users/logout' },
    'userinfo': { task: 'get', url: '/users/mypage' },
    'userValid': { task: 'get', url: '/users/valid' },
    'categoryAll': { task: 'get', url: '/categories/list/board' },
    'userBoard': { task: 'get', url: '/boards/list/user/board' },
    'categorySearch': { task: 'get', url: '/boards/category/search/#id' },
    'keywordSearch': { task: 'get', url: '/boards/keword/search' },
  },
  'like': {
    'register': { task: 'post', url: '/boards/likes/register' },
  },
  'cart': {
    'register': { task: 'post', url: '/carts/register' },
    'list': { task: 'get', url: '/carts/list' },
    'remove': { task: 'delete', url: '/carts/list/delete/#id' },
  },
  'mail': {
    'send': { task: 'post', url: '/messages/send' },
    'sent': { task: 'get', url: '/messages/list/user/sent' },
    'recv': { task: 'get', url: '/messages/list/user/recv' },
    'sentid': { task: 'get', url: '/messages/list/user/sent' },
    'recvid': { task: 'get', url: '/messages/list/user/recv' },
    'count': { task: 'get', url: '/messages//list/user/recv/count' },
  }
}