/**
 * Created by Dominic.schuermann on 05.05.2015.
 */

/// <reference path="./dependencies.ts"/>
/// <reference path="./controllers.ts"/>
/// <reference path="./services.ts"/>


var fbs = angular.module('fbs', ['ionic']);

fbs.run(['$ionicPlatform', ($ionicPlatform) => {
    $ionicPlatform.ready( () => {
        // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
        // for form inputs)
        if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
            cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        }
        if (window.StatusBar) {
            // org.apache.cordova.statusbar required
            window.StatusBar.styleLightContent();
        }
    });
}]);

fbs.config(['$stateProvider', '$urlRouterProvider',($stateProvider, $urlRouterProvider) => {

        $urlRouterProvider.otherwise('/login');

        $stateProvider
            .state('login', {
                url: "/login",
                templateUrl: "templates/login.html",
                controller: "LoginCtrl"
            })

            .state('overview', {
                url: "/overview",
                templateUrl: "templates/overview.html",
                controller: "OverviewCtrl"
            })

            .state('order', {
                url: "/order",
                templateUrl: "templates/orderOverview.html",
                controller: "OrderOverviewCtrl"
            })

            .state('orderDetail', {
                url: "/order/:orderId",
                templateUrl: "templates/orderDetail.html",
                controller: "OrderDetailCtrl"
            });
}]);

fbs.config(['$httpProvider', ($httpProvider) => {
    $httpProvider.defaults.headers.patch = {
        'Content-Type': 'application/json;charset=utf-8'
    }
}]);

fbs.run(['$rootScope', 'LoginService', '$state', ($rootScope, LoginService, $state) => {

    $rootScope.$on( '$stateChangeStart', function(e, toState  , toParams
        , fromState, fromParams) {

        var isLogin = toState.name === "login";
        if(isLogin){
            return; // no need to redirect
        }
        // now, redirect only not authenticated

        if(!$rootScope.userPrincipal) {
            e.preventDefault(); // stop current execution
            $state.go('login'); // go to login
        }
    });
}]);

fbs.factory('LoginService', [ () =>
    new services.LoginService()]);

fbs.controller('LoginCtrl',[ '$scope', '$rootScope', '$http', '$state', 'LoginService', 'Base64',($scope, $rootScope, $http, $state, LoginService, Base64) =>
    new login.LoginCtrl($scope, $rootScope, $http, $state, LoginService, Base64)]);

fbs.controller('OrderOverviewCtrl', [ '$scope','$http', ($scope, $http) =>
    new order.OrderOverviewCtrl($scope, $http)]);

fbs.controller('OverviewCtrl', ['$scope', '$state', '$rootScope', ($scope, $state, $rootScope) =>
    new overview.OverviewCtrl($scope, $state, $rootScope)]);

fbs.controller('OrderDetailCtrl', ['$scope', '$http', '$stateParams', ($scope, $http, $stateParams) =>
    new order.OrderDetailCtrl($scope, $http, $stateParams)]);

fbs.factory('Base64', function() {

    // base64 service from http://stackoverflow.com/questions/17959563/how-do-i-get-basic-auth-working-in-angularjs

    var keyStr = 'ABCDEFGHIJKLMNOP' +
        'QRSTUVWXYZabcdef' +
        'ghijklmnopqrstuv' +
        'wxyz0123456789+/' +
        '=';
    return {
        encode: function (input) {
            var output = "";
            var chr1: any, chr2: any, chr3: any = "";
            var enc1: any, enc2: any, enc3: any, enc4: any = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function (input) {
            var output = "";
            var chr1: any, chr2: any, chr3: any = "";
            var enc1: any, enc2: any, enc3: any, enc4: any = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };
});



