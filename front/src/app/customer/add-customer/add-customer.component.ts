import { Component, OnInit } from '@angular/core';
import { RoomService } from './../../services/room.service';
import { Room } from './../../models/room';
import { Title} from '@angular/platform-browser';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DatasService } from './../../services/datas.service';
import { BookingService } from './../../services/booking.service';
import { Booking } from '../../models/booking';
import { Customer } from '../../models/customer';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  private room: Room[];
  control = new FormControl();
  datasForm: FormGroup;
  private myBooking: Booking;
  private myCustomer: Customer;
  private submitted: Boolean = false;
  private waiting: Boolean = false;

  constructor(private roomService: RoomService,
              private bookingService: BookingService,
              private titleService: Title,
              private formBuilder: FormBuilder,
              private datas: DatasService,
              private router: Router) { }

  ngOnInit() {
    if (this.datas.bookingParam.dateIn === '') {
       this.router.navigateByUrl('/');
    }

    this.titleService.setTitle('Informations Client');
    this.initForm();
    // console.log(this.datas);
  }

  private initForm() {

    this.datasForm = this.formBuilder.group({
      civility  : ['1', [Validators.required, Validators.minLength(1), Validators.maxLength(1)]],
      name      : ['', [Validators.required, Validators.minLength(2)]],
      firstName : ['', [Validators.required, Validators.minLength(2)]],
      email     : ['', [Validators.required, Validators.email]],
      address   : ['', [Validators.required, Validators.minLength(5)]],
      zipCode   : ['', [Validators.required, Validators.minLength(5), Validators.maxLength(5)]],
      city      : ['', [Validators.required, Validators.minLength(2)]],
      country   : ['', [Validators.required, Validators.minLength(2)]],
      phone     : ['', [Validators.required, Validators.minLength(8), Validators.maxLength(15)]]
     }
    );
  }

  public onSubmitForm() {

    this.submitted = true;

    const formValue = this.datasForm.value;

    if ( !this.datasForm.invalid) {

      this.waiting = true;

      this.myCustomer = new Customer(0, formValue['civility'], formValue['name'], formValue['firstName'], '1970-01-01', formValue['email'],
      formValue['address'], formValue['zipCode'], formValue['city'], formValue['country'], formValue['phone'], formValue['newsletter']);

      this.myBooking = new Booking(0, '', this.myCustomer, this.datas.choosingRoom, this.datas.bookingParam.dateIn,
      this.datas.bookingParam.dateOut, this.datas.bookingParam.nbrAdults, this.datas.bookingParam.nbrChildrens, true, 0);

      this.submitNewBooking(this.myBooking);

    } else {
      console.log('Formulaire Invalide');
    }
}

  public get f() {/*console.log(this.datasForm.controls);*/ return this.datasForm.controls; }

  private submitNewBooking(myBooking: Booking) {
    this.bookingService.submitNewBooking(myBooking)
                       .subscribe(
                                    (valideBooking: Booking) => {
                                          this.datas.valideBooking = valideBooking;
                                          this.waiting = false;
                                          this.router.navigateByUrl('/booking/summary');
                                        }
                                  );
  }

  public getSubmitted() {
    return this.submitted;
  }

  public isBookingParam() {
    // If not dateIn value , we don't display the form
    // true, true, only for test

    return this.datas.bookingParam.dateIn ? true : false;
  }

  public getDateIn() {
    return this.datas.bookingParam.dateIn;
  }

  public getDateOut() {
    return this.datas.bookingParam.dateOut;
  }

  public getNbrAdults() {
    return this.datas.bookingParam.nbrAdults;
  }

  public getNbrChildrens() {
    return this.datas.bookingParam.nbrChildrens;
  }

  public getTypeOfRoom() {
    return this.datas.choosingRoom.roomType.type;
  }

  public getRoomPpn() {
    return this.datas.choosingRoom.roomType.ppn;
  }

  public getPriceTTC() {
    return this.datas.choosingRoom.roomType.ppn * this.getNbrNights();
  }

  public getNbrNights() {
    /*
    const dateIn: Date = new Date(this.getDateIn());
    const dateOut: Date = new Date(this.getDateOut());
    */
    const oneDay = 1000 * 60 * 60 * 24;

    return Math.abs( (new Date(this.getDateOut()).getTime() - new Date(this.getDateIn()).getTime()) / oneDay );
  }

  public getWaiting() {
    return this.waiting;
  }

}
