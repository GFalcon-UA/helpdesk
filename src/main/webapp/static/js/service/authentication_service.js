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
         service.refreshCredentials = refreshCredentials;

        return service;

        function Login(username, password, callback) {
            $http.post('/authenticate?username=' + username +"&password="+password,{})
             .then( function (response) {
                 callback(response);
             }
             );
        }

       function refreshCredentials(){
               $rootScope.globals = {};
               $cookies.remove('globals');
               $http.defaults.headers.common.Authorization = 'Basic';
       }

        function setCredentials(username, password) {
             var authdata = username + ':' + password;
             var role = '';
             $http.get('/user/role').then( function(response){
                    role = response.data.authority;
                },
                function(errResponse){
                    console.log(errResponse);
                }
             );
             $rootScope.globals = {
                 currentUser: {
                 username: username,
                 authdata: authdata,
                 role: role
                 }
             };

             // set default auth header for http requests
             $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;

             var expires = new Date();
             expires.setDate(expires.getDate() + 7);
             $cookies.putObject('globals', $rootScope.globals, { expires: expires });
        }
     };
})();
