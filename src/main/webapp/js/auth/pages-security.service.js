
(function () {
  'use strict';
  angular.module('auth')
    .factory('$pagesSecurityService', ['$userProvider', '$location',
      function ($userProvider, $location) {

        var checkAuthorize = function (path) {
          if (!$userProvider.getUser()) {
            //$location.path('/login');
            return false;
          }
          // todo свитчер с атрибутами доступа: true - пускаем, false - отказываем
          switch (path) {
            case '/protectef-path-for-admin':
              return checkPageSecurity({
                UserRoles: $userProvider.getUser().aRoles,
                AvailableRoles: [
                  $userProvider.rolesEnum.Admin
                ]
              });
            default:
              return true;
          }
        };

        var checkPageSecurity = function (config) {
          var authorize = false;
          //debugger;
          // todo алгоритм включения/отключения страниц
          for (var i in config.UserRoles) {
            if ($.inArray(config.UserRoles[i], config.AvailableRoles) === -1) {
              authorize = false;
            } else {
              authorize = true;
              break;
            }
          }
          //debugger;
          return authorize;
        };

        return {
          checkAuthorize: checkAuthorize
        };
      }])
})();