import { Injectable } from "@angular/core";
import {Headers,Http} from "@angular/http";

@Injectable()
export class ServerService{
constructor(private http:Http){}
stoteServers(servers:any[]){
    const headers = new Headers({'Content-type':'application/json'})
    return this.http.post('http://localhost:8081/user',servers,
    {headers:headers});
}
getServers(){
    return this.http.get('http://localhost:8081/user/data.json')
}
}