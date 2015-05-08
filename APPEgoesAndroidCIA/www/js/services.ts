/**
 * Created by orceN on 07.05.2015.
 */

module services{

    export class LoginService {

        private _baseAuthToken: string;
        private _userPrincipal: string;
        private _serverUrl: string;

        constructor(){
        }

        set baseAuthToken(baseAuthToken: string){
            this._baseAuthToken = baseAuthToken;
        }

        get baseAuthToken(){
            return this._baseAuthToken;
        }

        set userPrincipal(userPrincipal: any){
            this._userPrincipal = userPrincipal;
        }

        get userPrincipal(){
            return this._userPrincipal;
        }

        set serverUrl(serverUrl: string){
            this._serverUrl = serverUrl;
        }

        get serverUrl(){
            return this._serverUrl;
        }
    }
}

