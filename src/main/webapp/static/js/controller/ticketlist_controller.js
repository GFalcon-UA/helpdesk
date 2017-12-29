(function(){
    'use strict';

    angular.module('myApp')
    .controller('TicketListController', TicketListController);

    TicketListController.$inject = ['$window', '$rootScope', 'AppService'];

    function TicketListController($window,$rootScope, AppService) {
        var self = this;
        self.loggedInUser = $rootScope.globals.currentUser;
        self.email = '';
        self.tickets=[];

        self.showTicketList = function(){
            AppService.showTicketList()
                .then(
                    function(d){
                        self.tickets = d;
                    },
                    function(errResponse){
                        console.error('Error while show ticketlist (controller)');
                    }
                );
        };

        self.submitLogin = function(email){
            AppService.submitLogin(email)
                .then(
                    //self.showTicketList(),
                    //$window.location.assign("/ticketList")
                    function(){
                        //self.user = d;
                        self.showTicketList();
                        $window.location.assign("/ticketList");
                    },
                    function(errResponse){
                        console.error('Error while submit login (controller).');
                    }
                );
        };

        self.showTicketList();


        self.submit = function () {
            console.log('Login User', self.email);
            $window.localStorage['email'] = self.email;
            $window.location.assign("/ticketList");
            //self.submitLogin(self.email);
            //self.showTicketList(self.email);
        };

        self.goToTicket = function (id) {
            $window.localStorage.setItem('id', id);
            $window.location.assign("/ticketOverview/" + id);
        };


        self.fetchAllUsers = function(){
            AppService.fetchAllUsers()
                .then(
                    function(d) {
                        self.users = d;
                    },
                    function(errResponse){
                        console.error('Error while fetching Currencies');
                    }
                );
        };

        self.createUser = function(user){
            AppService.createUser(user)
                .then(
                    self.fetchAllUsers,
                    function(errResponse){
                        console.error('Error while creating User.');
                    }
                );
        };

        self.updateUser = function(user, id){
            AppService.updateUser(user, id)
                .then(
                    self.fetchAllUsers,
                    function(errResponse){
                        console.error('Error while updating User.');
                    }
                );
        };

        self.deleteUser = function(id){
            AppService.deleteUser(id)
                .then(
                    self.fetchAllUsers,
                    function(errResponse){
                        console.error('Error while deleting User.');
                    }
                );
        };

        self.edit = function(id){
            console.log('id to be edited', id);
            for(var i = 0; i < self.users.length; i++){
                if(self.users[i].id === id) {
                    self.user = angular.copy(self.users[i]);
                    break;
                }
            }
        };

        self.remove = function(id){
            console.log('id to be deleted', id);
            if(self.user.id === id) {//clean the form if the user to be deleted is shown there.
                self.reset();
            }
            self.deleteUser(id);
        };

        self.reset = function(){
            self.user={id:null,username:'',address:'',email:''};
            $scope.myForm.$setPristine(); //reset Form
        }

    }
})();

