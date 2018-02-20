(function () {
  'use strict';

  angular.module('Tickets')
    .controller('ListCtrl', function (TicketService, $userProvider, $location, $scope) {
      var vm = this;

      vm.oCurrentuser = $userProvider.getUser();

      vm.oState = {};
      vm.aTickets = [];
      vm.aMyTickets = [];
      vm.aShowedTickets = [];
      vm.aUrgency = [];
      vm.sFilterText = '';
      vm.sSortedBy = '';
      vm.oSorting = {};

      vm.init = function () {
        vm.oCurrentuser = $userProvider.getUser();

        vm.oState = {
          bAll: true,
          bMine: false
        };

        vm.aUrgency = ['CRITICAL', 'HIGH', 'MEDIUM', 'LOW'];

        vm.oSorting = {
          sByField: '',
          bDescending: {
            nId: true,
            sName: true,
            dDesiredDate: true,
            sUrgency: true,
            sState: true
          }
        };

        fillTicketList();
      };

      vm.onFilterChange = function (text) {
        var arr = [];
        if(vm.oState.bMine){
          arr = vm.aTickets.filter(function (obj) {
            if(vm.oCurrentuser.sRole === 'EMPLOYEE'){
              return vm.oCurrentuser.nId == obj.oOwner.nId;
            }
            if(vm.oCurrentuser.sRole === 'MANAGER'){
              return (obj.oOwner && obj.oOwner.nId == vm.oCurrentuser.nId) || (obj.oApprover && obj.oApprover.nId == vm.oCurrentuser.nId);
            }
            if(vm.oCurrentuser.sRole === 'ENGINEER'){
              return obj.oAssignee && obj.oAssignee.nId == vm.oCurrentuser.nId
            }
            return false;
          });
        } else {
          arr = vm.aTickets;
        }
        if(!text || text === ''){
          vm.aShowedTickets = angular.copy(arr);
        } else {
          vm.aShowedTickets = angular.copy(arr.filter(function (obj) {
            var id = obj.nId + '';
            if(id.toLowerCase().match(text.toLowerCase())){
              return true;
            }
            if(obj.sName && obj.sName.toLowerCase().match(text.toLowerCase())){
              return true;
            }
            if(obj.dDesiredDate && obj.dDesiredDate.toLowerCase().match(text.toLowerCase())){
              return true;
            }
            if(obj.sUrgency && obj.sUrgency.toLowerCase().match(text.toLowerCase())){
              return true;
            }
            if(obj.sState && obj.sState.toLowerCase().match(text.toLowerCase())){
              return true;
            }
            return false;
          }));
        }
      };

      vm.sortTicketByField = function (sFieldName) {
        vm.oSorting.sByField = sFieldName;
        vm.aShowedTickets = angular.copy(sortedObjectsArrayByField(vm.aShowedTickets, vm.oSorting));
      };


      vm.showAllTickets = function () {
        vm.oState = {
          bAll: true,
          bMine: false
        };
        vm.onFilterChange(vm.sFilterText);
      };
      vm.showMyTickets = function () {
        vm.oState = {
          bAll: false,
          bMine: true
        };
        vm.onFilterChange(vm.sFilterText);
      };

      vm.openTicketOverview = function (oTicket) {
        $location.path('/overview/' + oTicket['nId'])
      };

      vm.createNewTicket = function () {
        $location.path('/editor');
      };

      vm.openFeedbackPage = function (oTicket) {
        $location.path('/feedback/' + oTicket['nId'])
      };

      /*
       * Ticket actions
       */

      vm.setTicketState = function(oTicket, sState){
        TicketService.setNewState(oTicket, sState).then(function () {
          fillTicketList();
        });
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
        return TicketService.showLeaveFeedbackButton(oTicket)
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
          });
          vm.onFilterChange();
        });

      }

      var getDateFromString = function (str) {
        if(str){
          var arr = str.split('/');
          return new Date(parseInt(arr[2]), parseInt(arr[1]), parseInt(arr[0]));
        } else {
          return 0;
        }
      };


      function sortedObjectsArrayByField(array, config){
        var sFieldId = config['sByField'];
        var bAsc = config.bDescending[sFieldId];
        config.bDescending[sFieldId] = !config.bDescending[sFieldId];

        function compare(a, b) {
          if(sFieldId === 'dDesiredDate'){
            if(getDateFromString(a) < getDateFromString(b)) return -1;
            if(getDateFromString(a) > getDateFromString(b)) return 1;
            return 0
          } else if(sFieldId === 'sUrgency'){
            if(vm.aUrgency.indexOf(a) < vm.aUrgency.indexOf(b)) return -1;
            if(vm.aUrgency.indexOf(a) > vm.aUrgency.indexOf(b)) return 1;
            return 0
          } else {
            if(a < b) return -1;
            if(a > b) return 1;
            return 0
          }
        }

        return array.sort(function (first, second) {
          return bAsc ? compare(first[sFieldId], second[sFieldId]) : compare(second[sFieldId], first[sFieldId]);
        });
      }

    })
})();