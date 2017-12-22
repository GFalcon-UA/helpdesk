'use strict';

App.controller('ShowCommentsController', ['$window', '$scope', 'AppService', function($window, $scope, AppService) {
    var self = this;
    self.comments = [];

    self.showComments = function(){
        AppService.showComments()
            .then(
                function(d){
                    self.comments = d;
                },
                function(errResponse){
                    console.error('Error while showing comments (controller).');
                }
            );
    };

    self.showComments();

}]);
