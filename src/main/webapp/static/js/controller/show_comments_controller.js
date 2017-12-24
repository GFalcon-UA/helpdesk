'use strict';

App.controller('ShowCommentsController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {
    var self = this;
    self.comments = [];
    self.id = $window.localStorage.getItem('id');

    self.showComments = function(id){
        AppService.showComments(id)
            .then(
                function(d){
                    self.comments = d;
                },
                function(errResponse){
                    console.error('Error while showing comments (controller).');
                }
            );
    };

    self.showComments(self.id);

}]);
