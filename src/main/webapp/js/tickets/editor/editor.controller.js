(function () {
  'use strict';
  angular.module('Tickets').controller('EditorCtrl', function (TicketService) {

    var vm = this;

    vm.ticket = {};
    vm.categories = {
      list: [],
      selected: vm.ticket.urgency ? vm.ticket.urgency : undefined
    };
    vm.urgency = {
      list: [],
      selected: undefined
    };
    vm.attachment = {};

    vm.saveDraft = function () {
      submitTicket(true)
    };

    vm.submit = function () {
      submitTicket(false)
    };

    function fillCategoryList() {
      TicketService.getCategories().then(function (data) {
        vm.categories.list = data;
        if(vm.categories.list.length > 0){
          vm.categories.selected = vm.categories.list[0];
        }
      })
    }
    function fillUrgencyList() {
      TicketService.getUrgencyList().then(function (data) {
        vm.urgency.list = data;
        if(angular.isArray(data) && data.length > 0){
          vm.urgency.selected = data[parseInt(data.length/2 + '')];
        }
      })

    }

    function mergeEnums() {
      vm.ticket.urgency = vm.urgency.selected;
      vm.ticket.category = vm.categories.selected['id'];
    }

    function submitTicket(isDraft) {
      mergeEnums();
      TicketService.createTicket(vm.ticket, isDraft).then(
        function (result) {
          debugger;
        }, function (err) {
          debugger;
        }
      )
    }

    fillCategoryList();
    fillUrgencyList()

  })
})();