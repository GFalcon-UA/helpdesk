'use strict';

App.factory('AppService', ['$http', '$q', function($http, $q){

    return {

        showTicketList: function () {
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
        },

        submitLogin: function(email){
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
        },

        showTicketOverview: function(id){
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
        },

        showHistory: function(id){
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
        },

        showComments: function(id){
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
        },



        fetchAllUsers: function() {
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
        },

        createUser: function(user){
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
        },

        updateUser: function(user, id){
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
        },

        deleteUser: function(id){
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
        }

    };

}]);