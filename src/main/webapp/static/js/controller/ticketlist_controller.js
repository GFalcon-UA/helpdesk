'use strict';

App.controller('AppController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {
    var self = this;
    self.loggedInUser = $window.localStorage['email'];
    self.email = '';
    /*self.user={id:null, firstName:'', lastName:'', phone:'', roleId:'', email:'',
                addres:'', ownTickets:[], assignTickets:[], approveTickets:[],
                userHistory:[], userComments:[], userFeedback:[]};*/
    self.tickets=[];

    self.showTicketList = function(){
        AppService.showTicketList()
            .then(
                function(d){
                    self.tickets = d;
                    //$window.location.assign("/ticketList");
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
        self.submitLogin(self.email);
        //self.showTicketList(self.email);
    };

    self.goToTicket = function () {
        $window.location.assign("/ticketOverview");
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

    //self.fetchAllUsers();

    /*self.submit = function() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            self.createUser(self.user);
        }else{
            self.updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        self.reset();
    };*/

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

}]);