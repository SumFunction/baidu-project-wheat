
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index","pages/searchPage/searchPage","pages/myPage/myPage","pages/component/searchlist/searchlist","pages/component/login/login","pages/component/register/register","pages/component/member/member","pages/problem/problem","pages/component/discuss/discuss","pages/component/addproblem/addproblem","pages/component/middleuser/middleuser","pages/component/footercomment/footercomment","pages/component/smallimage/smallimage","pages/plantIdentify/plantIdentify","pages/questionDetails/questionDetails","pages/component/detailMoudle/detailMoudle","pages/component/headerDetail/headerDetail"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"稻橞识麦","navigationBarBackgroundColor":"#FFFFFF"},"tabBar":{"position":"bottom","selectedColor":"#75C142","color":"#13B8CE","list":[{"pagePath":"pages/index/index","text":"首页","selectedIconPath":"static/home-active.png","iconPath":"static/home.png"},{"pagePath":"pages/searchPage/searchPage","text":"搜索","selectedIconPath":"static/search-active.png","iconPath":"static/search.png"},{"pagePath":"pages/myPage/myPage","text":"我的","selectedIconPath":"static/member-active.png","iconPath":"static/member.png"}]},"nvueCompiler":"uni-app","nvueStyleCompiler":"weex","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"identifyrice","compilerVersion":"3.2.9","entryPagePath":"pages/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/searchPage/searchPage","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/myPage/myPage","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/component/searchlist/searchlist","meta":{},"window":{}},{"path":"/pages/component/login/login","meta":{},"window":{}},{"path":"/pages/component/register/register","meta":{},"window":{}},{"path":"/pages/component/member/member","meta":{},"window":{}},{"path":"/pages/problem/problem","meta":{},"window":{}},{"path":"/pages/component/discuss/discuss","meta":{},"window":{}},{"path":"/pages/component/addproblem/addproblem","meta":{},"window":{}},{"path":"/pages/component/middleuser/middleuser","meta":{},"window":{}},{"path":"/pages/component/footercomment/footercomment","meta":{},"window":{}},{"path":"/pages/component/smallimage/smallimage","meta":{},"window":{}},{"path":"/pages/plantIdentify/plantIdentify","meta":{},"window":{}},{"path":"/pages/questionDetails/questionDetails","meta":{},"window":{}},{"path":"/pages/component/detailMoudle/detailMoudle","meta":{},"window":{}},{"path":"/pages/component/headerDetail/headerDetail","meta":{},"window":{}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});