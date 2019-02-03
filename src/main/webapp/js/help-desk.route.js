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

  angular.module('HelpDesk')
    .config(['$routeProvider', '$httpProvider', '$locationProvider',
      function ($routeProvider, $httpProvider, $locationProvider) {

        $locationProvider.html5Mode(true);

        $routeProvider.when('/list', {
          templateUrl: './js/tickets/list/list.html',
          controller: 'ListCtrl',
          controllerAs: 'vm'
        }).when('/editor', {
          templateUrl: './js/tickets/editor/editor.html',
          controller: 'EditorCtrl',
          controllerAs: 'vm'
        }).when('/editor/:id', {
          templateUrl: './js/tickets/editor/editor.html',
          controller: 'EditorCtrl',
          controllerAs: 'vm'
        }).when('/overview/:id', {
          templateUrl: './js/tickets/overview/overview.html',
          controller: 'OverviewCtrl',
          controllerAs: 'vm'
        }).when('/feedback/:id', {
          templateUrl: './js/tickets/feedback/feedback.html',
          controller: 'FeedbackCtrl',
          controllerAs: 'vm'
        }).otherwise('/list');

        // $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

      }]);

})();