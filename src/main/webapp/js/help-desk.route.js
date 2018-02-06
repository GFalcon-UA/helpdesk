
(function () {
  'use strict';

  angular.module('HelpDesk')
    .config(['$routeProvider', '$httpProvider', '$locationProvider',
      function ($routeProvider, $httpProvider, $locationProvider) {

        $locationProvider.html5Mode(true);

        $routeProvider.when('/', {
          templateUrl: './js/tickets/list/list.html',
          controller: 'ListCtrl',
          controllerAs: 'vm'
        }).when('/editor', {
          templateUrl: './js/tickets/editor/editor.html',
          controller: 'EditorCtrl',
          controllerAs: 'vm'
        }).when('/overview', {
          templateUrl: './js/tickets/overview/overview.html',
          controller: 'OverviewCtrl',
          controllerAs: 'vm'
        }).when('/feedback', {
          templateUrl: './js/tickets/feedback/feedback.html',
          controller: 'FeedbackCtrl',
          controllerAs: 'vm'
        }).otherwise('/');

        // $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

      }]);

})();