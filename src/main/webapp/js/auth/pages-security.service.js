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