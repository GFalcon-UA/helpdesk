(function () {
  'use strict';

  angular.module('Tickets').service('TicketService', ['$http', '$cookieStore', '$userProvider', function ($http, $cookieStore, $userProvider) {

    this.getCurrentUser = function () {
      var result;
      if($cookieStore.get('HelpDeskUser')){
        return angular.fromJson($cookieStore.get('HelpDeskUser'));
      } else {

      }
    };

    this.getTicketsList = function () {
      return $http({
        method: 'GET',
        url: '/api/tickets/list',
        params: {
          userId: $userProvider.getUserId()
        }
      }).then(function (resp) {
        return resp;
      }, function (err) {
        return err;
      });
    };

    this.createTicket = function (newTicket, isDraft) {
      //     private Long id;
      // private CategoryDTO category;
      // private String name;
      // private String description;
      // private String urgency;
      // private String desiredDate;
      // private List<CommentDTO> comments;
      // private String state;
      // private UserDTO owner;
      // private UserDTO assignee;
      // private UserDTO approver;
      // private String createdOn;
      var json = angular.toJson({
        category: newTicket.category,
        name: newTicket.name,
        description: newTicket.description,
        urgency: newTicket.urgency,
        desiredDate: newTicket.desiredDate,
        comments: newTicket.comments,
        state: isDraft ? 'DRAFT' : 'NEW'
      });
      return $http({
        method: 'POST',
        url: '/api/tickets/create',
        params: {
          userId: $userProvider.getUserId()
        },
        data: json
      }).then(function (resp) {
        return resp;
      }, function (err) {
        console.error(angular.toJson(err));
        return err;
      })
    };

    this.getCategories = function () {
      var self = this;
      return $http({
        method: 'GET',
        url: '/api/enums/categories'
      }).then(function (responce) {
        var result = [];
        angular.forEach(angular.fromJson(responce.data), function (obj) {
          result.push({
            id: obj.id,
            name: obj.name
          })
        });
        // return self.sortObjectArrayByField(result, 'id');
        return result;
      }, function (err) {
        console.error(angular.toJson(err))
      });
    };

    this.getUrgencyList = function () {
      return $http({
        method: 'GET',
        url: '/api/enums/urgency'
      }).then(function (resp) {
        return angular.fromJson(resp.data);
      }, function (err) {
        console.error(angular.toJson(err))
      })
    };

    this.sortObjectArrayByField = function (array, field) {
      if(!((angular.isArray(array) && angular.isString(field)) && angular.isObject(array[0]))){
        return array;
      }
      return array.sort(function (o1, o2) {
        return o1[field] - o2[field];
      })
    }


  }])
})();