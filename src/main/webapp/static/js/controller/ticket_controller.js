(function(){
    'use strict';

    angular.module('myApp')
    .controller('TicketController', TicketController);

    TicketController.$inject = ['$window', '$rootScope', '$location', 'AppService','AuthenticationService'];

    function TicketController($window,$rootScope, $location, AppService,AuthenticationService) {
        var self = this;
        self.loggedInUser = $rootScope.globals.currentUser;
        self.email = '';

        self.ticket = {};
        self.histories = [];
        self.comment='';
        self.attachment = {};


        self.showHomePage = function(){
          $location.path('/');
        }

        self.showTicket = function (id) {
            $rootScope.id = id;
            $location.path("/ticket_overview");
        };

        self.edit = function(id){
        $rootScope.id = id;
          $location.path("/ticket/edit/" + id);
        };

        self.remove = function(id){
            console.log('id to be deleted', id);1
            if(self.user.id === id) {//clean the form if the user to be deleted is shown there.
                self.reset();
            }
            self.deleteUser(id);
        };

        self.reset = function(){
            self.user={id:null,username:'',address:'',email:''};
            $scope.myForm.$setPristine(); //reset Form
        }

        self.logout = function(){
           AppService.logout();
        }

         self.getTicket = function(){
            AppService.showTicketOverview($rootScope.id)
                .then(
                    function(d){
                        self.ticket = d;
                         $rootScope.id = d.id;
                    },
                    function(errResponse){
                        console.error('Error while fetching ticket.');
                    }
                );
        }

        self.discardChanges = function(){
           self.showTicket($rootScope.id);
        }

     }
})();

