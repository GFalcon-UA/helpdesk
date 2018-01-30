'use strict';

App.controller('TicketOverviewController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {
    var self = this;
    self.ticket = {};

    self.showTicketOverview = function(){
        AppService.showTicketOverview()
            .then(
                function(d){
                    self.ticket = d;
                },
                function(errResponse){
                    console.error('Error while submit login (controller).');
                }
            );
    };

    self.showTicketOverview();

}]);
