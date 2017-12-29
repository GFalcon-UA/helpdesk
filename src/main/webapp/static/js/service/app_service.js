(function () {

    'use strict';

     angular
            .module('myApp')
            .factory('AppService', AppService);

     AppService.$inject = ['$http', '$q', '$rootScope'];

     function AppService($http, $q, $rootScope){
         var service = {};
         service.showTicketList = showTicketList;
         service.submitLogin = submitLogin;
         service.showTicketOverview = showTicketOverview;
         service.showHistory = showHistory;
         service.showComments = showComments;
         service.fetchAllUsers = fetchAllUsers;
         service.createUser = createUser;
         service.updateUser = updateUser;
         service.deleteUser = deleteUser;
         return service;

          function showTicketList() {
                     return $http.get('http://localhost:8080/ticket-list/')
                         .then(
                             function(response){
                                 return response.data;
                             },
                             function(errResponse){
                                 console.error('Error while show ticketlist (Service)');
                                 return $q.reject(errResponse);
                             }
                         );
                 };

         function submitLogin(email){
             return $http.post('http://localhost:8080/ticket-list/', email)
                 .then(
                     function(response){
                         return response;
                     },
                     function(errResponse){
                         console.error('Error while login user (Service)');
                         return $q.reject(errResponse);
                     }
                 );
         };

         function showTicketOverview(id){
             return $http.get('http://localhost:8080/ticket-overview/'+id)
                 .then(
                     function(response){
                         return response.data;
                     },
                     function(errResponse){
                         console.error('Error while ticket overview (Service)');
                         return $q.reject(errResponse);
                     }
                 );
         };

         function showHistory(id){
             return $http.get('http://localhost:8080/showHistory/' + id)
                 .then(
                     function(response){
                         return response.data;
                     },
                     function(errResponse){
                         console.error('Error while showing history (Service)');
                         return $q.reject(errResponse);
                     }
                 );
         };

         function showComments(id){
             return $http.get('http://localhost:8080/showComments/' + id)
                 .then(
                     function(response){
                         return response.data;
                     },
                     function(errResponse){
                         console.error('Error while showing comments (Service)');
                         return $q.reject(errResponse);
                     }
                 );
         };

         function fetchAllUsers() {
             return $http.get('http://localhost:8080/SpringMVC4RestAPI/user/')
                 .then(
                     function(response){
                         return response.data;
                     },
                     function(errResponse){
                         console.error('Error while fetching users');
                         return $q.reject(errResponse);
                     }
                 );
         };

         function createUser(user){
             return $http.post('http://localhost:8080/SpringMVC4RestAPI/user/', user)
                 .then(
                     function(response){
                         return response.data;
                     },
                     function(errResponse){
                         console.error('Error while creating user');
                         return $q.reject(errResponse);
                     }
                 );
         };

         function updateUser(user, id){
             return $http.put('http://localhost:8080/SpringMVC4RestAPI/user/'+id, user)
                 .then(
                     function(response){
                         return response.data;
                     },
                     function(errResponse){
                         console.error('Error while updating user');
                         return $q.reject(errResponse);
                     }
                 );
         };

         function deleteUser(id){
             return $http.delete('http://localhost:8080/SpringMVC4RestAPI/user/'+id)
                 .then(
                     function(response){
                         return response.data;
                     },
                     function(errResponse){
                         console.error('Error while deleting user');
                         return $q.reject(errResponse);
                     }
                 );
         };

     }

})();