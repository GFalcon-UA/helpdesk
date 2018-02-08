(function () {
  'use strict';

  angular.module('Tickets').controller('ListCtrl', function (TicketService, $userProvider, $location) {
    var vm = this;

    vm.state = {
      all: true,
      my: false
    };

    vm.ticketsArray = [];
    vm.itemsPerPage = 5;

    vm.filterText = '';
    vm.onFilterChange = function () {

    };

    vm.sortedBy = '';
    vm.sortTicketByField = function (field) {

    };


    vm.showAllTickets = function () {
      vm.state = {
        all: true,
        my: false
      };

    };
    vm.showMyTickets = function () {
      vm.state = {
        all: false,
        my: true
      };

    };

    vm.createNewTicket = function () {
      $location.path('/editor');
    };

    function fillTicketList() {
      var result;
      TicketService.getTicketsList().then(function (resp) {
        result = resp.data;
      });

    }
    fillTicketList();

  })
})();