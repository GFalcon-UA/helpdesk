(function () {
  'use strict';

  angular.module('Tickets')
    .controller('ListCtrl', function (TicketService, $userProvider, $location, $scope) {
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

      /*
       * Ticket actions
       */

      vm.setTicketState = function(oTicket, sState){
        TicketService.setNewState(oTicket, sState);
      };

      vm.showActionButton = function (oTicket) {
        return vm.showSubmit(oTicket) || vm.showApprove(oTicket) || vm.showDecline(oTicket) || vm.showCancel(oTicket) || vm.showAssign(oTicket) || vm.showDone(oTicket) || vm.showLeaveFeedback(oTicket) || vm.showViewFeedback(oTicket);
      };

      vm.showSubmit = function (oTicket) {
        if (vm.oCurrentuser['sRole'] === 'EMPLOYEE' && oTicket['sState'] === 'DRAFT') return true;
        if (vm.oCurrentuser['sRole'] === 'EMPLOYEE' && oTicket['sState'] === 'DECLINED') return true;
        if (vm.oCurrentuser['sRole'] === 'MANAGER' && oTicket['sState'] === 'DRAFT' && oTicket['oOwner']['nId'] === vm.oCurrentuser['nId']) return true;
        if (vm.oCurrentuser['sRole'] === 'MANAGER' && oTicket['sState'] === 'DECLINED' && oTicket['oOwner']['nId'] === vm.oCurrentuser['nId']) return true;
        return false;
      };

      vm.showApprove = function (oTicket) {
        if (vm.oCurrentuser['sRole'] === 'MANAGER' && oTicket['sState'] === 'NEW' && oTicket['oOwner']['sRole'] === 'EMPLOYEE') return true;
        return false
      };

      vm.showDecline = function (oTicket) {
        if (vm.oCurrentuser['sRole'] === 'MANAGER' && oTicket['sState'] === 'NEW' && oTicket['oOwner']['sRole'] === 'EMPLOYEE') return true;
        return false
      };

      vm.showCancel = function (oTicket) {
        if (vm.oCurrentuser['sRole'] === 'EMPLOYEE' && oTicket['sState'] === 'DRAFT') return true;
        if (vm.oCurrentuser['sRole'] === 'EMPLOYEE' && oTicket['sState'] === 'DECLINED') return true;
        if (vm.oCurrentuser['sRole'] === 'MANAGER' && oTicket['sState'] === 'DRAFT') return true;
        if (vm.oCurrentuser['sRole'] === 'MANAGER' && oTicket['sState'] === 'NEW') return true;
        if (vm.oCurrentuser['sRole'] === 'MANAGER' && oTicket['sState'] === 'DECLINED') return true;
        if (vm.oCurrentuser['sRole'] === 'ENGINEER' && oTicket['sState'] === 'APPROVED') return true;
        return false;
      };

      vm.showAssign = function (oTicket) {
        if (vm.oCurrentuser['sRole'] === 'ENGINEER' && oTicket['sState'] === 'APPROVED') return true;
        return false;
      };

      vm.showDone = function (oTicket) {
        if (vm.oCurrentuser['sRole'] === 'ENGINEER' && oTicket['sState'] === 'IN_PROGRESS') return true;
        return false;
      };

      vm.showLeaveFeedback = function (oTicket) {
        if (oTicket['sState'] === 'DONE' && oTicket['oOwner']['nId'] === vm.oCurrentuser['nId'] && !vm.showViewFeedback(oTicket)) return true;
        return false;
      };

      vm.showViewFeedback = function (oTicket) {
        return oTicket.hasOwnProperty("oFeedback") && oTicket.oFeedback.nId;
      };

      //////////////////

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

          // set default action
          angular.forEach(vm.aTickets, function (oTicket) {
            if(!vm.showActionButton(oTicket)){
              oTicket.runDefaultAction = function () {};
              oTicket.sActionTitle = "";
            } else if (vm.showSubmit(oTicket)) {
              oTicket.runDefaultAction = function () {
                vm.setTicketState(oTicket, "NEW");
              };
              oTicket.sActionTitle = "Submit";
            } else if (vm.showApprove(oTicket)){
              oTicket.runDefaultAction = function(){
                vm.setTicketState(oTicket, "APPROVED");
              };
              oTicket.sActionTitle = "Approve";
            } else if (vm.showAssign(oTicket)){
              oTicket.runDefaultAction = function(){
                vm.setTicketState(oTicket, "IN_PROGRESS");
              };
              oTicket.sActionTitle = "Assign to Me";
            } else if (vm.showDone(oTicket)){
              oTicket.runDefaultAction = function(){
                vm.setTicketState(oTicket, "DONE");
              };
              oTicket.sActionTitle = "Done";
            } else if (vm.showLeaveFeedback(oTicket)){
              oTicket.runDefaultAction = function () {};
              oTicket.sActionTitle = "Leave feedback";
            } else if (vm.showViewFeedback(oTicket)){
              oTicket.runDefaultAction = function () {};
              oTicket.sActionTitle = "View feedback";
            } else if (vm.showCancel(oTicket)){
              oTicket.runDefaultAction = function(){
                vm.setTicketState(oTicket, "CANCELED");
              };
              oTicket.sActionTitle = "Cancel";
            }
          })

        });

      }

      fillTicketList();

      var getDateFromString = function (str) {
        if(str){
          var arr = str.split('/');
          return new Date(parseInt(arr[2]), parseInt(arr[1]), parseInt(arr[0]));
        } else {
          return 0;
        }
      }

    })
})();