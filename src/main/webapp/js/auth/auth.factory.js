/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

(function () {
  'use strict';

  angular.module('auth')
    .factory('auth', ['$rootScope', '$location', '$pagesSecurityService', '$userProvider', 'AuthService',
      function ($rootScope, $location, $pagesSecurityService, $userProvider, AuthService) {

        var enter = function (prevPath) {
          if ($location.path() !== auth.sLoginPath) {
            auth.path = $location.path();
            if (!auth.bAuthenticated) {
              $location.path(auth.sLoginPath);
            } else if (!$pagesSecurityService.checkAuthorize($location.path())) {
              alert('Доступ запрещен!');
              $location.path(prevPath);
            }
          }
        };

        var auth = {

          bAuthenticated: false,

          sLoginPath: '/login',
          sLogoutPath: '/logout',
          sHomePath: '/',
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
                auth.bAuthenticated = true;
              } else {
                auth.bAuthenticated = false;
              }
              callback && callback(auth.bAuthenticated);
              $location.path(auth.path === auth.sLoginPath ? auth.sHomePath : auth.path);
            }, function () {
              auth.bAuthenticated = false;
              callback && callback(false);
            });

          },

          clear: function () {
            $location.path(auth.sLoginPath);
            auth.bAuthenticated = false;
            AuthService.logout(auth.sLogoutPath).then(function () {
              $userProvider.setUser(null);
              console.log("Logout succeeded");
            }, function () {
              console.log("Logout failed");
            });
          },

          init: function (homePath, loginPath, logoutPath) {

            auth.sHomePath = homePath;
            auth.sLoginPath = loginPath;
            auth.sLogoutPath = logoutPath;

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