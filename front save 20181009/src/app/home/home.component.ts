import { Component, OnInit } from '@angular/core';
import { RoomService } from './../services/room.service';
import { Room } from './../models/room';
import { Title} from '@angular/platform-browser';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DatasService } from './../services/datas.service';

// Installation de Jquery
// https://medium.com/@swarnakishore/how-to-include-and-use-jquery-in-angular-cli-project-592e0fe63176
// declare var $: any;

// Gestion de formulaire
// tslint:disable-next-line:max-line-length
// https://openclassrooms.com/fr/courses/4668271-developpez-des-applications-web-avec-angular/5090131-ecoutez-lutilisateur-avec-les-forms-methode-reactive
// http://jasonwatmore.com/post/2018/05/10/angular-6-reactive-forms-validation-example

// Passer des parametres d'une page a une autre
// https://www.thepolyglotdeveloper.com/2016/10/passing-complex-data-angular-2-router-nativescript/

// DateTimePicker Example
// https://stackblitz.com/github/DanielYKPan/owl-examples/tree/date-time-picker

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private room: Room[];
  control = new FormControl();
  datasForm: FormGroup;
  submitted = false;
  private today = '';
  private tomorrow = '';
  private todayPlusOneYear = '';
  private tomorrowPlusOneYear = '';

  constructor(private roomService: RoomService,
              private titleService: Title,
              private formBuilder: FormBuilder,
              private datas: DatasService,
              private router: Router) { }

  ngOnInit() {
    this.titleService.setTitle('Hotel');
    this.initForm();
  }

  private initForm() {

    this.buildDates();

    this.datasForm = this.formBuilder.group({
      nbradults   : [2, [Validators.required, Validators.min(1)]],
      nbrchildrens: [0, [Validators.required, Validators.min(0)]],
      datein      : [this.today, Validators.required, ],
      dateout     : [this.tomorrow, Validators.required]
     },  {
       validator: this.dateLessThan('datein', 'dateout')
     }
    );
  }

  public onSubmitForm() {
    this.submitted = true;

    const formValue = this.datasForm.value;
    /*
    console.log('-- DEBUT --');
    console.log(this.datasForm);
    console.log('-- FIN --');
    */
    if ( !this.datasForm.invalid) {
      this.checkFreeRoom(formValue['datein'],
                         formValue['dateout'],
                         formValue['nbradults'],
                         formValue['nbrchildrens']);
    }
  }

  // convenience getter for easy access to form fields from html file
  public get f() {return this.datasForm.controls; }

  private checkFreeRoom(dateIn: string, dateOut: string, nbrAdults: number, nbrChildrens: number) {
    this.roomService.checkFreeRoom(dateIn, dateOut, nbrAdults, nbrChildrens)
                    .subscribe((room: Room[]) => {  // console.log(room);
                                                    this.datas.checkRoomForm = room;
                                                    this.datas.bookingParam.dateIn =  dateIn;
                                                    this.datas.bookingParam.dateOut = dateOut;
                                                    this.datas.bookingParam.nbrAdults = nbrAdults;
                                                    this.datas.bookingParam.nbrChildrens = nbrChildrens;
                                                    this.router.navigateByUrl('/room/list');
                                                  } ) ;
                      }

  private buildDates() {
    let myDate = new Date();

    // Today format yyyy-mm-dd
    this.today = myDate.toISOString().substring(0, 10);

    // Tomorrow format yyyy-mm-dd
    myDate.setDate(myDate.getDate() + 1);
    this.tomorrow =  myDate.toISOString().substring(0, 10);

    myDate = new Date();

    myDate.setFullYear(myDate.getFullYear() + 1);
    this.todayPlusOneYear = myDate.toISOString().substring(0, 10);

    myDate.setDate(myDate.getDate() + 1);
    this.tomorrowPlusOneYear = myDate.toISOString().substring(0, 10);
  }

  private dateLessThan(dateIn: string, dateOut: string) {
    return (group: FormGroup): {[key: string]: any} => {
      const dIn = group.controls[dateIn];
      const dOut = group.controls[dateOut];
      if ((dIn.value >= dOut.value && dIn.value !== '' && dOut.value !== '') ) {
        return {
          datesMsg: 'La date d\'arrivée doit être plus petite que la date de départ.',
        };
      }
      return {};
    };
  }

  public getAllRooms() {
    this.roomService.getAllRooms().subscribe((room: Room[]) => { console.log(room); this.room = room; } ) ;
  }

  public getRooms() {
    return this.room;
  }

  public getToday() {
    return this.today;
  }

  public getTomorrow() {
    return this.tomorrow;
  }

  public getSubmitted() {
    return this.submitted;
  }

  public getTodayPlusOneYear() {
    return this.todayPlusOneYear;
  }

  public getTomorrowPlusOneYear() {
    return this.tomorrowPlusOneYear;
  }
}
