(function () {
  'use strict';
  angular.module('Tickets').controller('EditorCtrl', function (TicketService, $log, $scope, $location, $userProvider, $routeParams) {

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
    vm.files = [];

    vm.saveDraft = function () {
      submitTicket(true)
    };

    vm.submit = function () {
      submitTicket(false)
    };

    vm.goToTicketList = goToTicketList;

    vm.init = function () {
      if($routeParams.id){
        TicketService.getTicket(parseInt($routeParams.id)).then(function (resp) {
          vm.ticket = resp;
        })
      }

      fillCategoryList();
      fillUrgencyList()

    };

    vm.removeFile = function (file) {
      TicketService.removeAttachmentById(file.nId).then(function () {
        vm.files.splice(vm.files.indexOf(file), 1);
      });
    };

    vm.btn_remove = function(file) {
      // $log.info('deleting=' + file);
      // uiUploader.removeFile(file);
      vm.files.splice(vm.files.indexOf(file), 1);
    };
    vm.btn_clean = function() {
      // uiUploader.removeAll();
      vm.files.splice(0, vm.files.length)
    };

    var upload = function(overviewTicktId) {
      TicketService.uploadUttachments(vm.ticket.nId, vm.files).then(function (resp) {
        if(overviewTicktId){
          goToOverview(overviewTicktId);
        } else {
          goToTicketList();
        }
      })
    };

    var element = document.getElementById('fileuploader');
    element.addEventListener('change', function(e) {
      var files = e.target.files;

      var aAvailableFileExtensions = ['pdf', 'png', 'doc', 'docx', 'jpeg', 'jpg'];
      var invalidFiles = [];
      for(var i = 0; i < files.length; i++){
        var bOversize = false;
        var bUnsuportedExtension = false;
        if(files[i].size > 5 * 1024 * 1024){
          bOversize = true;
          alert("The size of attached file should not be greater than 5 Mb. Please select another file.")
        }
        if(!verifyExtension(files[i].name)){
          bUnsuportedExtension = true;
          alert("The selected file type is not allowed. Please select a file of one of the following types: pdf, png, doc, docx, jpg, jpeg.");
        }
        if(bOversize || bUnsuportedExtension){
          invalidFiles.push(i);
        }
      }

      if(invalidFiles.length > 0){
        for(var j = invalidFiles.length - 1; j >= 0; j--){
          files.splice(invalidFiles[j], 1);
        }
      }

      function verifyExtension (sFileNameForCheck){
        var ext = sFileNameForCheck.split('.').pop().toLowerCase();
        for (var i = 0; i < aAvailableFileExtensions.length; i++){
          if (ext === aAvailableFileExtensions[i]){
            return true;
          }
        }
        return false;
      }

      if(files.length > 0){
        angular.forEach(files, function (file) {
          vm.files.push(file);
        });
        $scope.$apply();
      }
    });

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
      vm.ticket.sUrgency = vm.urgency.selected;
      vm.ticket.nCategory = vm.categories.selected['nId'];
    }

    function submitTicket(isDraft) {
      mergeEnums();
      if(vm.ticket.hasOwnProperty('nId')){
        TicketService.updateTicket(vm.ticket, isDraft).then(
          function (result) {
            vm.ticket = result.data;
            if(vm.files.length > 0){
              upload(vm.ticket['nId']);
            } else {
              goToOverview(vm.ticket['nId'])
            }
          }
        )
      } else {
        TicketService.createTicket(vm.ticket, isDraft).then(
          function (result) {
            vm.ticket = result.data;
            if(vm.files.length > 0){
              upload();
            } else {
              goToTicketList()
            }
          }
        )
      }

    }

    function goToOverview(nId) {
      $location.path('/overview/' + nId);
    }

    function goToTicketList() {
      $location.path('/list');
    }

  })
})();