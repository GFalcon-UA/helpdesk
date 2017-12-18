'use strict';

App.controller('TicketOverviewController', ['$window', '$scope', 'UserService', function($window, $scope, UserService) {
    var self = this;
    self.ticket = {};

    self.showTicketOverview = function(){
        UserService.showTicketOverview()
            .then(
                function(d){
                    self.ticket = d;
                    //$window.location.assign("/ticketOverview");
                },
                function(errResponse){
                    console.error('Error while submit login (controller).');
                }
            );
    };

    self.showTicketOverview();

}]);
