(function () {
  'use strict';

  angular.module('Tickets')
    .controller('OverviewCtrl', function (TicketService, $location, $routeParams) {

      var vm = this;

      vm.bHistoryViewMode = true;
      vm.bShortList = true;
      vm.oTicket = {};
      vm.aHistories = [];
      vm.aComments = [];
      vm.sNewComment = '';

      vm.getTicketOwner = function () {
        if(vm.oTicket['oOwner']){
          return vm.oTicket['oOwner']['sFirstName'] + ' ' + vm.oTicket['oOwner']['sLastName'];
        } else {
          return '';
        }
      };
      vm.getTicketApprover = function () {
        if(vm.oTicket['oApprover']){
          return vm.oTicket['oApprover']['sFirstName'] + ' ' + vm.oTicket['oApprover']['sLastName'];
        } else {
          return '';
        }
      };
      vm.getTicketAssignee = function () {
        if(vm.oTicket['oAssignee']){
          return vm.oTicket['oAssignee']['sFirstName'] + ' ' + vm.oTicket['oAssignee']['sLastName'];
        } else {
          return '';
        }
      };

      vm.changeViewMode = function () {
        vm.bShortList = true;
        vm.bHistoryViewMode = !vm.bHistoryViewMode;
        fillTable();
      };

      vm.showFullList = function () {
        vm.bShortList = false;
        fillTable();
      };

      vm.getTicketInfo = function () {
        var nId = parseInt($routeParams.id);
        TicketService.getTicket(nId).then(function (resp) {
          vm.oTicket = resp;
          fillTable();
        })
      };

      vm.addComment = function (nTicketId, sComment) {
        vm.sNewComment = "";
        TicketService.addComment(nTicketId, sComment).then(function (resp) {
          if(angular.isArray(vm.oTicket.aoComments)){
            vm.oTicket.aoComments.push(resp.data);
            fillTable();
          }
        })
      };

      function fillTable() {
        vm.aHistories = [];
        vm.aComments = [];
        if(vm.bHistoryViewMode && vm.bShortList){
          if(vm.oTicket.aoHistory && vm.oTicket.aoHistory.length <= 5){
            vm.aHistories = vm.oTicket.aoHistory;
          } else {
            for(var i = 0; i < 5; i++){
              vm.aHistories.push(vm.oTicket.aoHistory[i]);
            }
          }
        }
        if(vm.bHistoryViewMode && !vm.bShortList){
          vm.aHistories = vm.oTicket.aoHistory;
        }

        if(!vm.bHistoryViewMode && vm.bShortList){
          if(vm.oTicket.aoComments && vm.oTicket.aoComments.length <= 5){
            vm.aComments = vm.oTicket.aoComments;
          } else {
            for(var j = 0; j < 5; j++){
              vm.aComments.push(vm.oTicket.aoComments[j]);
            }
          }
        }
        if(!vm.bHistoryViewMode && !vm.bShortList){
          vm.aComments = vm.oTicket.aoComments;
        }
      }


  })
})();