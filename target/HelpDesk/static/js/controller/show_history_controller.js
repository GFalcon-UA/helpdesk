'use strict';

App.controller('ShowHistoryController', ['$window', '$scope', 'UserService', function($window, $scope, UserService) {
    var self = this;
    self.histories = [];

    self.showHistory = function(){
        UserService.showHistory()
            .then(
                function(d){
                    self.histories = d;
                },
                function(errResponse){
                    console.error('Error while showing history (controller).');
                }
            );
    };

    self.showHistory();

}]);
