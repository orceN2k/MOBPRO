/**
 * Created by Dominic.schuermann on 05.05.2015.
 */

/// <reference path="./dependencies.ts"/>
/// <reference path="./model.ts"/>


module login {

    export class LoginCtrl {

        constructor(private $scope, private $rootScope, private $http, private $state, private LoginService, private Base64){

            // define http headers
            $http.defaults.headers.common = {"Access-Control-Request-Headers": "accept, origin, authorization"};

            $scope.serverURL = "http://localhost:8080";
            LoginService.serverURL = this.$scope.serverURL;
            $scope.data = {};

            $scope.login = () => {

                var userInfo: model.UserInfo = {
                    user: $scope.data.username,
                    password: $scope.data.password
                };

                this.$http.post(this.$scope.serverURL + '/login', userInfo).
                    success(function(data, status, headers, config) {
                        console.log(data.userPrincipal);
                        $rootScope.userPrincipal = data.userPrincipal;
                        var token = Base64.encode($scope.data.username + ':' + $scope.data.password);
                        $http.defaults.headers.common['Authorization'] = 'Basic ' + token;
                        LoginService.baseAuthToken = token;
                        $state.go("overview");
                    }).
                    error(function(data, status, headers, config) {
                        alert("Login Failed - Error Code: " + status);
                });
            }
        }
    }
}

module overview {

    export class OverviewCtrl {

        constructor(private $scope, private $state, private $rootScope){

            $scope.role = $rootScope.userPrincipal.roles[0];

            $scope.gotoOrders = () => {
                this.$state.go("order");
            };

            $scope.gotoArticles = () => {
            };

            $scope.gotoUsers = () => {
            };
        }
    }
}


module order {

    export class OrderOverviewCtrl {

        private orders: model.Order[];

        constructor(private $scope, private $http){

            $scope.serverURL = "http://localhost:8080";
            $scope.data = {
                showDelete: false
            };

            $http.get(this.$scope.serverURL + '/order').
                success(function(data, status, headers, config) {
                    $scope.orders = data;
                }).
                error(function(data, status, headers, config) {
                    alert("Order search failed: " + status);
                });

            $scope.cancelOrder = (order) => {
                alert("order:" + order.id + " canceled!");
            };
        }
    }

    export class OrderDetailCtrl{

        private order: model.Order;

        constructor(private $scope, private $http, private $stateParams){

            $scope.serverURL = "http://localhost:8080";
            $http.get(this.$scope.serverURL + '/order/' + $stateParams.orderId).
                success(function(data, status, headers, config) {
                    $scope.order = data;
                }).
                error(function(data, status, headers, config) {
                    alert("Order search failed: " + status);
                });

        }



    }
}