'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    return {

        fetchAllUsers: function() {
            return $http.get('<a class="vglnk" href="http://localhost:8080/SpringMVC4RestAPI/user/" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>SpringMVC4RestAPI</span><span>/</span><span>user</span><span>/</span></a>')
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
            return $http.post('<a class="vglnk" href="http://localhost:8080/SpringMVC4RestAPI/user/" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>SpringMVC4RestAPI</span><span>/</span><span>user</span><span>/</span></a>', user)
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
            return $http.put('<a class="vglnk" href="http://localhost:8080/SpringMVC4RestAPI/user/'+id" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>SpringMVC4RestAPI</span><span>/</span><span>user</span><span>/'+</span><span>id</span></a>, user)
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
            return $http.delete('<a class="vglnk" href="http://localhost:8080/SpringMVC4RestAPI/user/'+id" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>SpringMVC4RestAPI</span><span>/</span><span>user</span><span>/'+</span><span>id</span></a>)
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