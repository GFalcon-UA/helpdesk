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
          $cookieStore.put('HelpDeskUser', angular.toJson(this.user));
        } else {
          if ($cookieStore.get('HelpDeskUser')) {
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