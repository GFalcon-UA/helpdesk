
(function () {
  'use strict';

  angular.module('HelpDesk', [
    'auth',
    'Tickets'

  ])
      .run(['auth', function (auth) {
      // Initialize auth module with the home page and login/logout path
      // respectively
      auth.init('/', '/login', '/logout');
    }]);

})();

