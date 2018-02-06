
(function () {
  'use strict';

  angular.module('auth')
    .factory('auth', ['$rootScope', '$location', '$pagesSecurityService', '$userProvider', 'AuthService',
      function ($rootScope, $location, $pagesSecurityService, $userProvider, AuthService) {

        var enter = function (prevPath) {
          if ($location.path() !== auth.loginPath) {
            auth.path = $location.path();
            if (!auth.authenticated) {
              $location.path(auth.loginPath);
            } else if (!$pagesSecurityService.checkAuthorize($location.path())) {
              alert('Доступ запрещен!');
              $location.path(prevPath);
            }
          }
        };

        var auth = {

          authenticated: false,

          loginPath: '/login',
          logoutPath: '/logout',
          homePath: '/',
          path: $location.path(),

          authenticate: function (credentials, callback) {

            var headers = credentials && credentials.username ? {
              authorization: "Basic "
              + btoa(credentials.username + ":"
                + credentials.password)
            } : {};

            AuthService.login(headers).then(function (response) {
              if (response.data && response.data.name) {
                $userProvider.setUser(response.data);
                auth.authenticated = true;
              } else {
                auth.authenticated = false;
              }
              callback && callback(auth.authenticated);
              $location.path(auth.path === auth.loginPath ? auth.homePath : auth.path);
            }, function () {
              auth.authenticated = false;
              callback && callback(false);
            });

          },

          clear: function () {
            $location.path(auth.loginPath);
            auth.authenticated = false;
            AuthService.logout(auth.logoutPath).then(function () {
              $userProvider.setUser(null);
              console.log("Logout succeeded");
            }, function () {
              console.log("Logout failed");
            });
          },

          init: function (homePath, loginPath, logoutPath) {

            auth.homePath = homePath;
            auth.loginPath = loginPath;
            auth.logoutPath = logoutPath;

            auth.authenticate({}, function (authenticated) {
              if (authenticated) {
                $location.path(auth.path);
              }
            });

            angular.extend($rootScope, $userProvider, true);

            // Guard route changes and switch to login page if unauthenticated
            $rootScope.$on('$routeChangeStart', function (event, nextUrl, currUrl) {
              var prevPath = '/';
              if (currUrl && currUrl.$$route && currUrl.$$route.originalPath) {
                prevPath = currUrl.$$route.originalPath;
              }
              enter(prevPath);
            });

          }

        };

        return auth;

      }]);
})();