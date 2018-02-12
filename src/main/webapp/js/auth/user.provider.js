
(function () {
  'use strict';

  angular
    .module('auth')
    .factory('$userProvider', function ($cookieStore) {

      var rolesEnum = {
        Admin: 0,
        User: 1
      };

      var setUser = function (u) {
        if (u.authenticated) {
          this.user = u.principal;
          this.user.sLogin = u.name;
          this.user.aRoles = u.authorities;
          $cookieStore.put('HelpDeskUser' , angular.toJson(this.user));
        } else {
          if($cookieStore.get('HelpDeskUser')){
            $cookieStore.remove('HelpDeskUser');
          }
          this.user = {};
        }
      };

      var getUser = function () {
        return this.user;
      };

      var getUserId = function () {
        return this.user ? this.user['nId'] : undefined;
      };

      return {
        getUser: getUser,
        setUser: setUser,
        getUserId: getUserId,
        rolesEnum: rolesEnum
      }
    });
})();