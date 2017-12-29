(function () {
    'use strict';

    angular
        .module('myApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService'];

    function LoginController($location, AuthenticationService) {
       var vm = this;
       vm.login = login;
       vm.initController = initController;
        function initController() {
            AuthenticationService.clearCredentials();
        };

        function login() {
            this.dataLoading = true;
            AuthenticationService.Login(this.username, this.password, function (response) {
                if (response.status = "OK") {
                console.log("USer has been authenticated");
                    AuthenticationService.setCredentials(vm.username, vm.password);
                    $location.path('/');
                } else {
                    this.dataLoading = false;
                }
            });
        };
    }

})();