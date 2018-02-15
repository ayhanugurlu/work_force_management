angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    

      .state('worklog', {
    url: '/worklog',
    templateUrl: 'templates/worklog.html',
    controller: 'worklogCtrl'
  })

  .state('addlocation', {
    url: '/addlocation',
    templateUrl: 'templates/addlocation.html',
    controller: 'addlocationCtrl'
  })

  .state('login', {
    url: '/login',
    templateUrl: 'templates/login.html',
    controller: 'loginCtrl'
  })

  .state('home', {
    url: '/home',
    templateUrl: 'templates/home.html',
    controller: 'homeCtrl',
	   params: {
          obj: null,
          token:null
      }
  })

$urlRouterProvider.otherwise('/login')


});