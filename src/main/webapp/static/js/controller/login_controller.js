'use strict';

App.controller('LoginController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {

    var self = this;
    //self.email = '';

    self.submit = function () {
        $window.location.assign("/ticketList");
    };
}]);
