import { Injectable, OnInit  } from '@angular/core';
import { Room } from '../models/room';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders( {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    })
};

@Injectable({
  providedIn: 'root'
})

export class RoomService {

  /**
   * Url to access to the WebService
   */
  private restUrl = 'http://localhost:8090/room';

  constructor(private http: HttpClient) {
  }

/**
 * Call WS to get the list of Rooms
 *
 * @return Observable<Room>
 */
  public getAllRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(this.restUrl + '/list');
  }

/**
 * Call WS to get the list of free rooms for asked period
 *
 * @return Observable<Room>
 */
  public checkFreeRoom(dateIn: String, dateOut: String, nbrAdults: number, nbrChildrens: number): Observable<Room[]> {
    return this.http.get<Room[]>(this.restUrl + '/' + dateIn + '/' + dateOut + '/' + nbrAdults + '/' + nbrChildrens);
  }

}
