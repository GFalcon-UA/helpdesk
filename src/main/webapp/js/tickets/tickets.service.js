(function () {
  'use strict';

  angular.module('Tickets')
    .service('TicketService', ['$http', '$cookieStore', '$userProvider', '$q', '$base64'
      , function ($http, $cookieStore, $userProvider, $q, $base64) {

        this.getCurrentUser = function () {
          var result;
          if ($cookieStore.get('HelpDeskUser')) {
            return angular.fromJson($cookieStore.get('HelpDeskUser'));
          } else {

          }
        };

        this.getTicketsList = function () {
          var nUserId = $userProvider.getUserId();
          return $http({
            method: 'GET',
            url: '/api/tickets/list',
            params: {
              nUserId: nUserId
            }
          }).then(function (resp) {
            return resp;
          }, function (err) {
            return err;
          });
        };

        this.getTicket = function (nId) {
          return $http({
            method: 'GET',
            url: '/api/tickets/get',
            params: {
              nTicketId: nId,
              nUserId: $userProvider.getUserId()
            }
          }).then(function (resp) {
            return angular.fromJson(resp.data);
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.createTicket = function (newTicket, isDraft) {
          var json = angular.toJson({
            nCategory: newTicket.nCategory,
            sName: newTicket.sName,
            sDescription: newTicket.sDescription,
            sUrgency: newTicket.sUrgency,
            dDesiredDate: newTicket.dDesiredDate,
            sComment: newTicket.sComment,
            sState: isDraft ? 'DRAFT' : 'NEW'
          });
          return $http({
            method: 'POST',
            url: '/api/tickets/create',
            params: {
              nUserId: $userProvider.getUserId()
            },
            data: json
          }).then(function (resp) {
            return resp;
          }, function (err) {
            console.error(angular.toJson(err));
            alert(err.data);
            return err;
          })
        };

        this.updateTicket = function (newTicket, isDraft) {
          var json = angular.toJson({
            nId: newTicket.nId,
            nCategory: newTicket.nCategory,
            sName: newTicket.sName,
            sDescription: newTicket.sDescription,
            sUrgency: newTicket.sUrgency,
            dDesiredDate: newTicket.dDesiredDate,
            sComment: newTicket.sComment,
            sState: isDraft ? 'DRAFT' : 'NEW'
          });
          return $http({
            method: 'PUT',
            url: '/api/tickets/update',
            params: {
              nUserId: $userProvider.getUserId()
            },
            data: json
          }).then(function (resp) {
            return resp;
          }, function (err) {
            console.error(angular.toJson(err));
            alert(err.data);
            return err;
          })
        };

        this.addComment = function (nTicketId, sCommentText) {
          var json = angular.toJson({
            sComment: sCommentText
          });
          return $http({
            method: 'POST',
            url: '/api/tickets/comment',
            params: {
              nUserId: $userProvider.getUserId(),
              nTicketId: nTicketId
            },
            data: json
          }).then(function (resp) {
            return resp;
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.setNewState = function (oTicket, sState) {
          return $http({
            method: 'PUT',
            url: '/api/tickets/state',
            params: {
              nTicketId: oTicket['nId'],
              nUserId: $userProvider.getUserId(),
              sNewState: sState
            }
          }).then(function (resp) {
            return resp;
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.getFeedBack = function (nTicketId) {
          return $http({
            method: 'GET',
            url: '/api/tickets/getFeedback',
            params: {
              nTicketId: nTicketId
            }
          }).then(function (resp) {
            try {
              return angular.fromJson(resp.data);
            } catch (e){
              return resp.data;
            }
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.setFeedBack = function (nTicketId, oFeedback) {
          return $http({
            method: 'POST',
            url: '/api/tickets/setFeedback',
            params: {
              nUserId: $userProvider.getUserId(),
              nTicketId: nTicketId
            },
            data: angular.toJson({
              nRate: oFeedback.nRate,
              sText: oFeedback.sText
            })
          }).then(function (resp) {
            try {
              return angular.fromJson(resp.data);
            } catch (e){
              return resp.data;
            }
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.getCategories = function () {
          var self = this;
          return $http({
            method: 'GET',
            url: '/api/enums/categories'
          }).then(function (responce) {
            return angular.fromJson(responce.data);
          }, function (err) {
            console.error(angular.toJson(err))
          });
        };

        this.getUrgencyList = function () {
          return $http({
            method: 'GET',
            url: '/api/enums/urgency'
          }).then(function (resp) {
            return angular.fromJson(resp.data);
          }, function (err) {
            console.error(angular.toJson(err))
          })
        };

        this.uploadUttachments = function (nTicketId, aContents) {
          var deferred = $q.defer();
          var self = this;

          var uploadPromises = [],
            contents = [],
            documentPromises = [],
            fileDefer = [],
            counter = 0;

          angular.forEach(aContents, function (oContent, key) {
            fileDefer[key] = $q.defer();
            contents[key] = oContent;
            documentPromises[key] = fileDefer[key].promise;
          });

          var uploadingResult = [];

          var asyncUpload = function (i, files, defs) {
            if (i < files.length) {
              return $http({
                method: 'POST',
                url: '/api/file/upload',
                params: {
                  nUserId: $userProvider.getUserId(),
                  nTicketId: nTicketId,
                  sFileName: files[i].name,
                  sFileType: files[i].type
                },
                data: $base64.encode(files[i])
              }).then(function (resp) {
                uploadingResult.push(resp);
                defs[i].resolve(resp);
                return asyncUpload(i + 1, files, defs);
              }, function (err) {
                uploadingResult.push({error : err});
                defs[i].reject(err);
                return asyncUpload(i + 1, files, defs);
              });

            }
          };

          var first = $q.all(uploadPromises).then(function () {
            return asyncUpload(counter, contents, fileDefer);
          });

          $q.all([first, documentPromises]).then(function () {
            deferred.resolve(uploadingResult);
          });

          return deferred.promise;

        };

        this.downloadAttachment = function (oFile) {
          $http({
            method: 'GET',
            url: '/api/file/download',
            params: {
              nAttachmentId: oFile.nId
            }
          }).then(function (resp) {

            // var byteCharacters = btoa(resp.data);
            // var byteNumbers = new Array(byteCharacters.length);
            // for (var i = 0; i < byteCharacters.length; i++) {
            //   byteNumbers[i] = byteCharacters.charCodeAt(i);
            // }
            //
            // var byteArray = new Uint8Array(byteNumbers);
            // var blob = new Blob([byteArray], {type: oFile.sMIMEtype});
            // var url = (window.URL || window.webkitURL).createObjectURL(blob);
            // var link = document.createElement("a");
            // link.download = oFile.sFileName;
            // link.href = url;
            // link.click();

            base64ToBlob(resp.data, function (blob) {
              var url = (window.URL || window.webkitURL).createObjectURL(blob);
              var link = document.createElement("a");
              link.download = oFile.sFileName;
              link.href = url;
              link.click();
            }, oFile.sMIMEtype)
          })
        };

        this.removeAttachmentById = function (nId) {
          return $http({
            method: 'DELETE',
            url: '/api/file/remove',
            params: {
              nAttachmentId: nId,
              nUserId: $userProvider.getUserId()
            }
          }).then(function (resp) {
            return resp;
          }, function (err) {
            console.error(angular.toJson(err))
          })
        };

        this.showLeaveFeedbackButton = function (oTicket) {
          return oTicket['sState'] === 'DONE' && oTicket['oOwner']['nId'] === vm.oCurrentuser['nId'] && !vm.showViewFeedback(oTicket);
        };

        this.sortObjectArrayByField = function (array, field) {
          if (!((angular.isArray(array) && angular.isString(field)) && angular.isObject(array[0]))) {
            return array;
          }
          return array.sort(function (o1, o2) {
            return o1[field] - o2[field];
          })
        };

        var blobToBase64 = function(blob, cb) {
          var reader = new FileReader();
          reader.onload = function() {
            var dataUrl = reader.result;
            var base64 = dataUrl.split(',')[1];
            cb(base64);
          };
          reader.readAsDataURL(blob);
        };

        var base64ToBlob = function(base64, cb, mimeType) {
          var binary = atob(base64);
          var len = binary.length;
          var buffer = new ArrayBuffer(len);
          var view = new Uint8Array(buffer);
          for (var i = 0; i < len; i++) {
            view[i] = binary.charCodeAt(i);
          }
          if(mimeType){
            cb(new Blob([view], {type: mimeType}));
          } else {
            cb(new Blob([view]));
          }

        };

      }])
})();