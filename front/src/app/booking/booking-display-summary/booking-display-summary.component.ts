import { Component, OnInit } from '@angular/core';
import { Title} from '@angular/platform-browser';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Booking } from '../../models/booking';
import { DatasService } from './../../services/datas.service';
import { Router } from '@angular/router';
import { BookingService } from './../../services/booking.service';

@Component({
  selector: 'app-booking-display-summary',
  templateUrl: './booking-display-summary.component.html',
  styleUrls: ['./booking-display-summary.component.css']
})
export class BookingDisplaySummaryComponent implements OnInit {

  private myBooking: Booking = this.datas.valideBooking;
  private displayModal: Boolean = false;
  private deleteStatus: Boolean = false;
  private displayDeleteMsg: Boolean = false;

  constructor(private titleService: Title,
              private bookingService: BookingService,
              private router: Router,
              private datas: DatasService) { }

  ngOnInit() {
    if (this.myBooking === undefined) {
      this.router.navigateByUrl('/booking/display/form');
    }

    this.titleService.setTitle('Voir réservation ' + this.getNumBooking());

    // console.log(this.myBooking);
  }

  public onCancelBooking() {
    // URL /booking/delete/ID/NAME
    this.displayModal = false;
    this.bookingService.submitCancelBooking()
                       .subscribe(
                                  (deleteStatus: Boolean) => {                                        
                                        this.deleteStatus = deleteStatus;
                                        
                                        if (this.deleteStatus)  {

                                            new Promise((res) => {
                                              setTimeout(() => {
                                                this.displayDeleteMsg = true;
                                                // this.router.navigateByUrl('/');
                                                res();
                                              }, 500); // Attendre 0,5 sec
                                            })
                                        } 
                                        
                                      }
                                 );
  }

  public displayWarning() {
    this.displayModal = true;
  }

  public redirectHome(){
    this.router.navigateByUrl('/'); 
  }

  public isDisplayDeleteMsg(){
    return this.displayDeleteMsg;
  }

  public getMyBooking() {
    return this.myBooking === undefined ? false : true;
  }
  
  public getName(){
    return this.myBooking.customer.name;
  }

  public getNumBooking() {
    return this.myBooking.numBooking;
  }

  public getCivility() {
    return this.myBooking.customer.civility;
  }

  public getDateIn() {
    return this.myBooking.dateIn;
  }

  public getDateOut() {
    return this.myBooking.dateOut;
  }

  public getNbrAdults() {
    return this.myBooking.nbrAdults;
  }

  public getNbrChildrens() {
    return this.myBooking.nbrChildrens;
  }

  public getTypeOfRoom() {
    return this.myBooking.room.roomType.type;
  }

  public getNbrOfNights() {
    return this.myBooking.nbrNights;
  }

  public getTotalPrice() {
    return this.getNbrOfNights() * this.myBooking.room.roomType.ppn;
  }

  public getDisplayModal() {
    return this.displayModal;
  }
}
