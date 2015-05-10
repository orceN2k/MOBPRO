/**
 * Created by Dominic.schuermann on 05.05.2015.
 */
/// <reference path="./dependencies.ts"/>
/// <reference path="./model.ts"/>
var login;
(function (login) {
    var LoginCtrl = (function () {
        function LoginCtrl($scope, $rootScope, $http, $state, LoginService, Base64) {
            var _this = this;
            this.$scope = $scope;
            this.$rootScope = $rootScope;
            this.$http = $http;
            this.$state = $state;
            this.LoginService = LoginService;
            this.Base64 = Base64;
            // define http headers
            $http.defaults.headers.common = { "Access-Control-Request-Headers": "accept, origin, authorization" };
            LoginService.serverURL = $scope.serverURL;
            $scope.data = {};
            $scope.data.serverURL = "http://localhost:8080";
            $scope.login = function () {
                var userInfo = {
                    user: $scope.data.username,
                    password: $scope.data.password
                };
                _this.$http.post($scope.data.serverURL + '/login', userInfo).success(function (data, status, headers, config) {
                    console.log(data.userPrincipal);
                    $rootScope.userPrincipal = data.userPrincipal;
                    var token = Base64.encode(userInfo.user + ':' + userInfo.password);
                    $http.defaults.headers.common['Authorization'] = 'Basic ' + token;
                    LoginService.baseAuthToken = token;
                    $state.go("overview");
                }).error(function (data, status, headers, config) {
                    //alert("Login Failed with serverUrl: " + $scope.data.serverURL +"- Error Code: " + status);
                    alert(status + " // " + data + "//" + config);
                });
            };
        }
        return LoginCtrl;
    })();
    login.LoginCtrl = LoginCtrl;
})(login || (login = {}));
var overview;
(function (overview) {
    var OverviewCtrl = (function () {
        function OverviewCtrl($scope, $state, $rootScope) {
            var _this = this;
            this.$scope = $scope;
            this.$state = $state;
            this.$rootScope = $rootScope;
            $scope.role = $rootScope.userPrincipal.roles[0];
            $scope.gotoOrders = function () {
                _this.$state.go("order");
            };
            $scope.gotoArticles = function () {
            };
            $scope.gotoUsers = function () {
            };
        }
        return OverviewCtrl;
    })();
    overview.OverviewCtrl = OverviewCtrl;
})(overview || (overview = {}));
var order;
(function (_order) {
    var OrderOverviewCtrl = (function () {
        function OrderOverviewCtrl($scope, $http) {
            this.$scope = $scope;
            this.$http = $http;
            $scope.serverURL = "http://localhost:8080";
            $scope.data = {
                showDelete: false
            };
            $http.get(this.$scope.serverURL + '/order').success(function (data, status, headers, config) {
                $scope.orders = data;
            }).error(function (data, status, headers, config) {
                alert("Order search failed: " + status);
            });
            $scope.cancelOrder = function (order) {
                alert("order:" + order.id + " canceled!");
            };
        }
        return OrderOverviewCtrl;
    })();
    _order.OrderOverviewCtrl = OrderOverviewCtrl;
    var OrderDetailCtrl = (function () {
        function OrderDetailCtrl($scope, $http, $stateParams) {
            this.$scope = $scope;
            this.$http = $http;
            this.$stateParams = $stateParams;
            $scope.serverURL = "http://localhost:8080";
            $http.get(this.$scope.serverURL + '/order/' + $stateParams.orderId).success(function (data, status, headers, config) {
                $scope.order = data;
            }).error(function (data, status, headers, config) {
                alert("Order search failed: " + status);
            });
        }
        return OrderDetailCtrl;
    })();
    _order.OrderDetailCtrl = OrderDetailCtrl;
})(order || (order = {}));
//# sourceMappingURL=controllers.js.map