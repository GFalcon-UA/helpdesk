
(function () {
  'use strict';

  angular
    .module('auth')
    .factory('$userProvider', function ($cookieStore) {

      var rolesEnum = {
        Admin: 0,
        User: 1
      };

      // principal
      //   accountNonExpired
      // accountNonLocked
      // address
      // approveTickets
      // assignTickets
      // authorities
      // comments
      // credentialsNonExpired
      // email
      // enabled
      // feedbacks
      // firstName
      // histories
      // lastName
      // nID
      // ownTickets
      // password
      // phone
      // role
      // username

      var setUser = function (u) {
        if (u.bAuthenticated) {
          debugger;
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