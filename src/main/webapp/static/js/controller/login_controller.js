'use strict';

App.controller('LoginController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {
    var self = this;
    self.email = '';

    self.submitLogin = function(email){
        UserService.submitLogin(email)
            .then(
                //self.showTicketList()
                function(d){
                    //self.user = d;
                    $window.location.assign("/ticketList");
                },
                function(errResponse){
                    console.error('Error while submit login (controller).');
                }
            );
    };

    self.submit = function () {
        console.log('Login User', self.email);
        $window.localStorage['email'] = self.email;
        self.submitLogin(self.email);
    };
}]);
