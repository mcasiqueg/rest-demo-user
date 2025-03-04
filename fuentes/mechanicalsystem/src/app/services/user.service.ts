import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  //private serviceUrl = 'http://localhost:8081/user';
  private serviceUrl = 'https://jsonplaceholder.typicode.com/users';

  constructor(private http : HttpClient) {}

  getUser(): Observable<User[]> {
    return this.http.get<User[]>(this.serviceUrl);
  }
}
