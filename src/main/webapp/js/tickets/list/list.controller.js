(function () {
  'use strict';

  angular.module('Tickets')
    .controller('ListCtrl', function (TicketService, $userProvider, $location) {
      var vm = this;

      vm.oCurrentuser = $userProvider.getUser();

      vm.oState = {
        bAll: true,
        bMine: false
      };

      vm.aTickets = [];
      vm.nItemsPerPage = 5;

      vm.aUrgency = ['CRITICAL', 'HIGH', 'MEDIUM', 'LOW'];

      vm.sFilterText = '';
      vm.onFilterChange = function () {

      };

      vm.sSortedBy = '';
      vm.sortTicketByField = function (field) {

      };


      vm.showAllTickets = function () {
        vm.oState = {
          bAll: true,
          bMine: false
        };

      };
      vm.showMyTickets = function () {
        vm.oState = {
          bAll: false,
          bMine: true
        };

      };

      vm.openTicketOverview = function (oTicket) {
        $location.path('/overview/' + oTicket['nId'])
      };

      vm.createNewTicket = function () {
        $location.path('/editor');
      };

      function fillTicketList() {
        var result = [];
        TicketService.getTicketsList().then(function (resp) {

          function filterByUrgency(sUrgency) {
            return resp.data.filter(function (oTicket) {
              return oTicket.sUrgency === sUrgency;
            })
          }

          function sortByDesiredDate(array) {
            return array.sort(function (obj1, obj2) {
              var date1 = getDateFromString(obj1.dDesiredDate);
              var date2 = getDateFromString(obj2.dDesiredDate);
              return date1 - date2;
            });
          }

          angular.forEach(vm.aUrgency, function (sCurrentUrgency) {
            var aSubarray = sortByDesiredDate(filterByUrgency(sCurrentUrgency));
            angular.forEach(aSubarray, function (oItem) {
              result.push(oItem);
            });
          });

          vm.aTickets = result;

        });

      }

      fillTicketList();

      var getDateFromString = function (str) {
        var arr = str.split('/');
        return new Date(parseInt(arr[2]), parseInt(arr[1]), parseInt(arr[0]));
      }

    })
})();