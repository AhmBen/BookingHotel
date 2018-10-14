import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContactMail } from '../models/contact-mail';


const httpOptions = {
  headers: new HttpHeaders( {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    })
};

@Injectable({
  providedIn: 'root'
})

export class CommonService {

  /**
   * Url to access to the WebService
   */
  private restUrl = 'http://localhost:8090';

  constructor(private http: HttpClient) {}

  public sendContactMail(contactMail: ContactMail): Observable<any> {
    return this.http.post<ContactMail>(this.restUrl + '/sendContactMail', contactMail, httpOptions);
}
}
