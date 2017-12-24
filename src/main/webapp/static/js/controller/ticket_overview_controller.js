'use strict';

App.controller('TicketOverviewController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {
    var self = this;
    self.id = $window.localStorage.getItem('id');
    self.ticket = {};

    self.showTicketOverview = function(id){
        AppService.showTicketOverview(id)
            .then(
                function(d){
                    self.ticket = d;
                },
                function(errResponse){
                    console.error('Error while submit login (controller).');
                }
            );
    };



    self.edit = function(id){
        $window.location.assign("/edit/" + id);
    };

    self.showTicketOverview(self.id);

}]);
