(function () {
    'use strict';

    angular
        .module('myApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService'];

    function LoginController($location, AuthenticationService) {
       var vm = this;
       vm.login = login;
       AuthenticationService.refreshCredentials();

        function login() {
            this.dataLoading = true;
            AuthenticationService.Login(this.username, this.password, function (response) {
                if (response.status = "OK") {
                    AuthenticationService.setCredentials(vm.username, vm.password);
                    $location.path('/');
                } else {
                    $location.path('/login');
                    this.dataLoading = false;
                }
            });
        };
    }

})();