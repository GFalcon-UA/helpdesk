(function(){
    'use strict';

    angular.module('myApp')
    .controller('TicketListController', TicketListController);

    TicketListController.$inject = ['$window', '$rootScope', '$location', 'AppService','AuthenticationService'];

    function TicketListController($window,$rootScope, $location, AppService,AuthenticationService) {
    var self = this;
    self.tickets=[];

     self.fetchTickets = function(){
        AppService.showTicketList()
            .then(
                function(d){
                    self.tickets = d;
                },
                function(errResponse){
                    console.error(errResponse);
                    console.error('Error while show ticketlist (controller)');
                }
            );
    };
    self.fetchTickets();

     self.createTicket = function(){
      $location.path('/createTicket');
    }


    self.showTicket = function (id) {
        $rootScope.id = id;
        $location.path("/ticket_overview");
    };

    self.edit = function(id){
    $rootScope.id = id;
      $location.path("/ticket/edit/" + id);
    };

    }

})();