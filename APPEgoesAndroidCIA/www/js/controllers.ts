/**
 * Created by Dominic.schuermann on 05.05.2015.
 */

/// <reference path="./dependencies.ts"/>
/// <reference path="./model.ts"/>


module login {

    export class LoginCtrl {

        private userInfo: model.UserInfo;

        constructor(private $scope, private $http, private $state){

            this.$scope.serverURL = "http://localhost:8080";
            this.userInfo = {
                user: "",
                password: ""
            };

            this.$scope.login = () => {

                this.$http.post(this.$scope.serverURL + '/login', this.userInfo).
                    success(function(data, status, headers, config) {
                        console.log(data.userPrincipal);
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

        constructor(private $scope, private $state){

            this.$scope.gotoOrders = () => {
                this.$state.go("order");
            };

            this.$scope.gotoArticles = () => {
            };

            this.$scope.gotoUsers = () => {
            };
        }
    }
}


module order {

    export class OrderCtrl {

        constructor(private $scope){
            this.$scope.data = {
                showDelete: false
            };

            this.$scope.cancelOrder = (order) => {
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
    }
}