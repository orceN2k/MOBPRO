/**
 * Created by orceN on 07.05.2015.
 */
var services;
(function (services) {
    var LoginService = (function () {
        function LoginService() {
        }
        Object.defineProperty(LoginService.prototype, "baseAuthToken", {
            get: function () {
                return this._baseAuthToken;
            },
            set: function (baseAuthToken) {
                this._baseAuthToken = baseAuthToken;
            },
            enumerable: true,
            configurable: true
        });
        Object.defineProperty(LoginService.prototype, "userPrincipal", {
            get: function () {
                return this._userPrincipal;
            },
            set: function (userPrincipal) {
                this._userPrincipal = userPrincipal;
            },
            enumerable: true,
            configurable: true
        });
        Object.defineProperty(LoginService.prototype, "serverUrl", {
            get: function () {
                return this._serverUrl;
            },
            set: function (serverUrl) {
                this._serverUrl = serverUrl;
            },
            enumerable: true,
            configurable: true
        });
        return LoginService;
    })();
    services.LoginService = LoginService;
})(services || (services = {}));
//# sourceMappingURL=services.js.map