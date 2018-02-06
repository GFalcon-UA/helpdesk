
(function () {
  'use strict';

  angular.module('auth')
    .service('AuthService', ['$http', '$cookies', function ($http, $cookies) {

      this.login = function (headers) {
        return $http({
          method: 'GET',
          url: '/api/auth/getCurrentUser',
          headers: headers
        }).then(function (resp) {
          return resp;
        }, function (err) {
          return err;
        });
      };

      this.logout = function (logoutUrl) {
        return $http({
          method: 'GET',
          url: logoutUrl
        }).then(function (resp) {
          return resp;
        }, function (err) {
          return err;
        });
      }

    }]);

})();