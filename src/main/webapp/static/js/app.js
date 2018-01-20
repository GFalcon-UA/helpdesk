(function () {
    'use strict';

         angular
        .module('myApp', ['ngRoute', 'ngCookies'])
        .config(config).run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/',{
                controller: 'TicketListController',
                templateUrl: '/ticket_list',
                controllerAs: 'ticketListController'
             })
            .when('/login', {
                controller: 'LoginController',
                templateUrl: '/login.html',
                controllerAs: 'vm'
            })
            .when('/createTicket', {
                controller: 'TicketController',
                templateUrl: '/ticket/create',
                controllerAs: 'ctrl'
            })
            .when('/ticket_overview',{
                controller: 'TicketController',
                templateUrl: '/ticket_overview',
                controllerAs: 'ctrl'
            })
            .when('/ticket/edit/:param1',{
                controller: 'TicketController',
                templateUrl: '/ticket/edit',
                controllerAs: 'ctrl'
            });
          }



           run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
              function run($rootScope, $location, $cookieStore, $http) {
                  // keep user logged in after page refresh
                  $rootScope.globals = $cookieStore.get('globals') || {};
                  if ($rootScope.globals.currentUser) {
                      $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
                  }

                  $rootScope.$on('$locationChangeStart', function (event, next, current) {
                      // redirect to login page if not logged in and trying to access a restricted page
                      var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
                      var loggedIn = $rootScope.globals.currentUser;
                      if (restrictedPage && !loggedIn) {
                          $location.path('/login');
                      }
                  });
              }

          function refreshCredentials($rootScope,$cookieStore,$http){
             $rootScope.globals = {};
             $cookieStore.remove('globals');
             $http.defaults.headers.common.Authorization = 'Basic';
          }


})();