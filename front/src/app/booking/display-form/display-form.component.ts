import { Component, OnInit } from '@angular/core';
import { Title} from '@angular/platform-browser';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Booking } from '../../models/booking';
import { DatasService } from './../../services/datas.service';
import { Router } from '@angular/router';
import { BookingService } from './../../services/booking.service';

@Component({
  selector: 'app-display-form',
  templateUrl: './display-form.component.html',
  styleUrls: ['./display-form.component.css']
})
export class DisplayFormComponent implements OnInit {

  control = new FormControl();
  datasForm: FormGroup;
  private submitted: Boolean = false;
  private notFound: Boolean = false;

  constructor(private titleService: Title,
              private formBuilder: FormBuilder,
              private bookingService: BookingService,
              private router: Router,
              private datas: DatasService) {

              }

  ngOnInit() {
    this.titleService.setTitle('Voir une réservation');
    this.initForm();
    // console.log(this.datas);
  }

  private initForm() {
    this.datasForm = this.formBuilder.group({
      numBooking: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
      name      : ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
     }
    );
  }

  public onSubmitForm() {

    this.submitted = true;

    const formValue = this.datasForm.value;

    // console.log(this.datasForm);

    if ( !this.datasForm.invalid) {

        // console.log('Formulaire Valide');
        this.datas.displayBookingParam.numBooking = formValue['numBooking'];
        this.datas.displayBookingParam.name = formValue['name'];
        this.submitGetBooking();

    } else {

        console.log('Formulaire Invalide');

    }
  }

  private submitGetBooking() {
    this.bookingService.submitGetBooking()
                       .subscribe(
                                  (valideBooking: Booking) => {

                                        this.datas.valideBooking = valideBooking;
                                        
                                        if (valideBooking != null)  {
                                            this.router.navigateByUrl('/booking/display/summary');
                                        } else {
                                            this.notFound = true;
                                        }

                                      }
                                 );
  }

  public getSubmitted() {
    return this.submitted;
  }

  public getNotFound() {
    return this.notFound;
  }

  public get f() {return this.datasForm.controls; }

  public onCloseErrorMsg() {
    this.notFound = false;
  }
}
