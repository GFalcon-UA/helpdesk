(function () {

    'use strict';

     angular
            .module('myApp')
            .factory('AppService', AppService);

     AppService.$inject = ['$http', '$q', '$rootScope','$cookies'];

     function AppService($http, $q, $rootScope,$cookies){
         var service = {};
         service.showTicketOverview = showTicketOverview;
         service.showTicketList = showTicketList;
         service.fetchCategories = fetchCategories;
         return service;

         function showTicketList(){
             return $http.get('/ticket/all')
                          .then(
                              function(response){
                                  return response.data;
                              },
                              function(errResponse){
                                  console.error('Error fetching tickets');
                                  return $q.reject(errResponse);
                              }
                          );
         }

         function showTicketOverview(id){
             return $http.get('/ticket/'+id)
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

         function fetchCategories(){
            return $http.get('/ticket/categories').then(
                   function(response){
                       return response.data;
                   },
                   function(errResponse){
                       console.error('Error while ticket overview (Service)');
                       return $q.reject(errResponse);
                   }
               );
         }

       }
})();