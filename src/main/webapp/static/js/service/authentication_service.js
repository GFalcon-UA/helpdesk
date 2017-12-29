(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$cookies', '$rootScope', '$timeout'];

    function AuthenticationService($http, $cookies, $rootScope, $timeout) {
         var service = {};

         service.Login = Login;
         service.setCredentials = setCredentials;
         service.clearCredentials = clearCredentials;

        return service;

        function Login(username, password, callback) {
            $http.post('/authenticate?username=' + username +"&password="+password,{})
             .then(function (response) {
                 callback(response);
             });
        }

        function setCredentials(username, password) {
             var authdata = username + ':' + password;

             $rootScope.globals = {
                 currentUser: {
                 username: username,
                 authdata: authdata
                 }
             };

             // set default auth header for http requests
             $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;

             var expires = new Date();
             expires.setDate(expires.getDate() + 7);
             $cookies.putObject('globals', $rootScope.globals, { expires: expires });
        }

        function clearCredentials() {
        console.log("Clear dat fuckin credentials");
             $rootScope.globals = {};
             $cookies.remove('globals');
             $http.defaults.headers.common.Authorization = 'Basic';
        }
     };
})();
