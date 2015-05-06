/**
 * Created by Dominic.schuermann on 05.05.2015.
 */
/// <reference path="./dependencies.ts"/>
/// <reference path="./model.ts"/>
var login;
(function (login) {
    var LoginCtrl = (function () {
        function LoginCtrl($scope, $http, $state) {
            var _this = this;
            this.$scope = $scope;
            this.$http = $http;
            this.$state = $state;
            this.$scope.serverURL = "http://localhost:8080";
            this.userInfo = {
                user: "",
                password: ""
            };
            this.$scope.login = function () {
                _this.$http.post(_this.$scope.serverURL + '/login', _this.userInfo).success(function (data, status, headers, config) {
                    console.log(data.userPrincipal);
                    $state.go("overview");
                }).error(function (data, status, headers, config) {
                    alert("Login Failed - Error Code: " + status);
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
        function OverviewCtrl($scope, $state) {
            var _this = this;
            this.$scope = $scope;
            this.$state = $state;
            this.$scope.gotoOrders = function () {
                _this.$state.go("order");
            };
            this.$scope.gotoArticles = function () {
            };
            this.$scope.gotoUsers = function () {
            };
        }
        return OverviewCtrl;
    })();
    overview.OverviewCtrl = OverviewCtrl;
})(overview || (overview = {}));
var order;
(function (_order) {
    var OrderCtrl = (function () {
        function OrderCtrl($scope) {
            this.$scope = $scope;
            this.$scope.data = {
                showDelete: false
            };
            this.$scope.cancelOrder = function (order) {
                alert("order:" + order.id + " canceled!");
            };
            this.$scope.orders = [
                {
                    id: 1,
                    customer: {
                        id: 1,
                        name: "Sabrina Sauber"
                    },
                    orderItems: [
                        {
                            id: 1,
                            article: {
                                id: 5,
                                name: "Hot Dog",
                                price: 5,
                                availableStock: 50,
                                minimalStock: 20
                            },
                            quantity: 20,
                            price: 100.00,
                        }
                    ],
                    deliveryDate: new Date(),
                    status: 1,
                    salesPerson: "Volker Verkauf",
                    totalAmount: 100.00,
                },
                {
                    id: 2,
                    customer: {
                        id: 2,
                        name: "Larissa Laus"
                    },
                    orderItems: [
                        {
                            id: 1,
                            article: {
                                id: 6,
                                name: "Hamburger",
                                price: 2,
                                availableStock: 100,
                                minimalStock: 20
                            },
                            quantity: 20,
                            price: 40.00,
                        }
                    ],
                    deliveryDate: new Date(),
                    status: 1,
                    salesPerson: "Volker Verkauf",
                    totalAmount: 40.00,
                }
            ];
        }
        return OrderCtrl;
    })();
    _order.OrderCtrl = OrderCtrl;
})(order || (order = {}));
//# sourceMappingURL=controllers.js.map