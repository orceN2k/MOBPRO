/**
 * Created by Dominic.schuermann on 05.05.2015.
 */
/// <reference path="./dependencies.ts"/>
/// <reference path="./controllers.ts"/>
var fbs = angular.module('fbs', ['ionic']);
fbs.run(['$ionicPlatform', function ($ionicPlatform) {
    $ionicPlatform.ready(function () {
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
fbs.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider.state('login', {
        url: "/login",
        templateUrl: "templates/login.html",
        controller: "LoginCtrl"
    }).state('overview', {
        url: "/overview",
        templateUrl: "templates/overview.html",
        controller: "OverviewCtrl"
    }).state('order', {
        url: "/order",
        templateUrl: "templates/order.html",
        controller: "OrderCtrl"
    }).state('orderDetail', {
        url: "/order/:id",
        templateUrl: ""
    });
}]);
fbs.controller('LoginCtrl', ['$scope', '$http', '$state', function ($scope, $http, $state) { return new login.LoginCtrl($scope, $http, $state); }]);
fbs.controller('OrderCtrl', ['$scope', function ($scope) { return new order.OrderCtrl($scope); }]);
fbs.controller('OverviewCtrl', ['$scope', '$state', function ($scope, $state) { return new overview.OverviewCtrl($scope, $state); }]);
//# sourceMappingURL=app.js.map