/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
        if (vm.oTicket['oOwner']) {
          return vm.oTicket['oOwner']['sFirstName'] + ' ' + vm.oTicket['oOwner']['sLastName'];
        } else {
          return '';
        }
      };
      vm.getTicketApprover = function () {
        if (vm.oTicket['oApprover']) {
          return vm.oTicket['oApprover']['sFirstName'] + ' ' + vm.oTicket['oApprover']['sLastName'];
        } else {
          return '';
        }
      };
      vm.getTicketAssignee = function () {
        if (vm.oTicket['oAssignee']) {
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
          if (angular.isArray(vm.oTicket.aoComments)) {
            vm.oTicket.aoComments.push(resp.data);
            fillTable();
          }
        })
      };

      vm.showLeaveFeedback = function () {
        return TicketService.showLeaveFeedbackButton(vm.oTicket)
      };

      vm.leaveFeedback = function (id) {
        $location.path('/feedback/' + id);
      };

      vm.goToEditor = function (id) {
        $location.path('/editor/' + id);
      };

      vm.enableEditorPage = function (oTicket) {
        return oTicket['sState'] === 'DRAFT';
      };

      vm.downloadFile = function (file) {
        TicketService.downloadAttachment(file);
      };

      vm.download2 = function (file) {
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        return function (data, fileName) {
          var blob = new Blob([data], {type: "octet/stream"}),
            url = window.URL.createObjectURL(blob);
          a.href = url;
          a.download = fileName;
          a.click();
          window.URL.revokeObjectURL(url);
        };
      };

      function fillTable() {
        vm.aHistories = [];
        vm.aComments = [];
        if (vm.bHistoryViewMode && vm.bShortList) {
          if (vm.oTicket.aoHistory && vm.oTicket.aoHistory.length <= 5) {
            vm.aHistories = vm.oTicket.aoHistory;
          } else {
            for (var i = 0; i < 5; i++) {
              vm.aHistories.push(vm.oTicket.aoHistory[i]);
            }
          }
        }
        if (vm.bHistoryViewMode && !vm.bShortList) {
          vm.aHistories = vm.oTicket.aoHistory;
        }

        if (!vm.bHistoryViewMode && vm.bShortList) {
          if (vm.oTicket.aoComments && vm.oTicket.aoComments.length <= 5) {
            vm.aComments = vm.oTicket.aoComments;
          } else {
            for (var j = 0; j < 5; j++) {
              vm.aComments.push(vm.oTicket.aoComments[j]);
            }
          }
        }
        if (!vm.bHistoryViewMode && !vm.bShortList) {
          vm.aComments = vm.oTicket.aoComments;
        }
      }


    })
})();