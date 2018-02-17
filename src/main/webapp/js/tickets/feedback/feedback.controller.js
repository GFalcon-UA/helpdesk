(function () {
  'use strict';

  angular.module('Tickets').controller('FeedbackCtrl', function (TicketService, $routeParams, $window) {

    var vm = this;

    vm.oFeedBack = {};

    vm.init = function () {
      vm.nTicketId = parseInt($routeParams.id);
      vm.getFeedBack();
    };

    vm.setRate = function (nRate) {
      vm.oFeedBack.nRate = nRate;
    };

    vm.isSelected = function (nRate) {
      return nRate === vm.oFeedBack.nRate;
    };

    vm.getFeedBack = function () {
      TicketService.getFeedBack(vm.nTicketId).then(function (resp) {
        if(resp.id){
          vm.oFeedBack = resp;
        } else {
          vm.oFeedBack = {
            nRate: undefined,
            sText: ''
          }
        }
      })
    };

    vm.goToBack = function () {
      $window.history.back();
    };

    vm.submit = function () {
      TicketService.setFeedBack(vm.nTicketId, vm.oFeedBack).then(function (resp) {
        vm.goToBack();
      })
    }

  })
});