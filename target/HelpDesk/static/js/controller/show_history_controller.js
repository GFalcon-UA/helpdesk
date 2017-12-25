'use strict';

App.controller('ShowHistoryController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {
    var self = this;
    self.histories = [];
    self.id = $window.localStorage.getItem('id');

    self.showHistory = function(id){
        AppService.showHistory(id)
            .then(
                function(d){
                    self.histories = d;
                },
                function(errResponse){
                    console.error('Error while showing histoty (controller).');
                }
            );
    };

    self.showHistory(self.id);

}]);
