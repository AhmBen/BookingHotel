import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../models/booking';
import { DatasService } from './datas.service';

const httpOptions = {
  headers: new HttpHeaders( {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    })
};

@Injectable({
  providedIn: 'root'
})

export class BookingService {

  /**
   * Url to access to the WebService
   */
  private restUrl = 'http://localhost:8090/booking';

  constructor(private http: HttpClient, private datas: DatasService) {}

  public submitNewBooking(myBooking: Booking): Observable<Booking> {
      return this.http.post<Booking>(this.restUrl + '/create', myBooking, httpOptions);
  }

  public submitGetBooking(): Observable<Booking> {
      const myBooking = this.datas.displayBookingParam;
      return this.http.get<Booking>(this.restUrl + '/display/' + myBooking.numBooking + '/' + myBooking.name);
  }

  public submitCancelBooking(): Observable<Boolean> {
      const myBooking = this.datas.valideBooking;
      //return this.http.delete<Aliment>(this.restUrl + '/' + id);
      return this.http.delete<Boolean>(this.restUrl + '/delete/' + myBooking.id + '/' + myBooking.customer.name.toUpperCase());
  }

}
