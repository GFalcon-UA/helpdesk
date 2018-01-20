(function () {
    'use strict';

    angular
        .module('myApp')
        .controller('MainController', MainController);

    MainController.$inject = ['$location','$rootScope','$cookies','$http','$window'];

    function MainController($location,$rootScope,$cookies,$http,$window) {
       var self = this;
       self.logout = logout;

       return self;

       function logout(){
          $rootScope.globals = {};
          $cookies.remove('globals');
          $http.defaults.headers.common.Authorization = 'Basic';
          $window.location.reload();
       }
     }

})();