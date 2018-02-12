(function () {
  'use strict';
  angular.module('Tickets').controller('EditorCtrl', function (TicketService, uiUploader, $log, $scope, $location, $userProvider) {

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

    $scope.btn_remove = function(file) {
      $log.info('deleting=' + file);
      uiUploader.removeFile(file);
    };
    $scope.btn_clean = function() {
      uiUploader.removeAll();
    };
    $scope.btn_upload = function() {
      $log.info('uploading...');
      debugger;
      uiUploader.startUpload({
        url: '/api/file/upload?nTicketId=' + vm.ticket.nId + '&nUserId=' + $userProvider.getUserId(),
        concurrency: 2,
        onProgress: function(file) {
          $log.info(file.name + '=' + file.humanSize);
          $scope.$apply();
        },
        onCompleted: function(file, response) {
          $log.info(file + 'response' + response);
        },
        onCompletedAll: function(files) {
          // files is an array of File objects
          $log.log(files);
          goToTicketList();
        }
      });
    };

    $scope.files = [];

    var element = document.getElementById('file1');
    element.addEventListener('change', function(e) {
      var files = e.target.files;
      debugger;

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
        uiUploader.addFiles(files);
        $scope.files = uiUploader.getFiles();
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
      TicketService.createTicket(vm.ticket, isDraft).then(
        function (result) {
          vm.ticket = result.data;
          debugger;
          if($scope.files.length > 0){
            $scope.btn_upload();
          } else {
            goToTicketList()
          }
        }
      )
    }

    function goToTicketList() {
      $location.path('/');
    }

    fillCategoryList();
    fillUrgencyList()

  })
})();