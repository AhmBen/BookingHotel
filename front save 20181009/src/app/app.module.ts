// Pour avoir la date en Français
// https://stackoverflow.com/questions/46419026/missing-locale-data-for-the-locale-xxx-with-angular
// https://angular.io/guide/i18n#setting-up-the-locale-of-your-app
// https://github.com/angular/angular/issues/20286

import { LOCALE_ID} from '@angular/core';  

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

import { RouterModule, Routes } from '@angular/router';
import { BookingComponent } from './booking/booking.component';
import { DisplayBookingComponent } from './booking/display-booking/display-booking.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RoomComponent } from './room/room.component';
import { ListRoomsComponent } from './room/list-rooms/list-rooms.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CustomerComponent } from './customer/customer.component';
import { AddCustomerComponent } from './customer/add-customer/add-customer.component';
import { AddBookingComponent } from './booking/add-booking/add-booking.component';
import { BookingSummaryComponent } from './booking/booking-summary/booking-summary.component';
import { DisplayFormComponent } from './booking/display-form/display-form.component';
import { BookingDisplaySummaryComponent } from './booking/booking-display-summary/booking-display-summary.component';

registerLocaleData(localeFr);

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'room/list', component: ListRoomsComponent },
  { path: 'booking/summary', component: BookingSummaryComponent },
  { path: 'booking/display/form', component: DisplayFormComponent },
  { path: 'booking/display/summary', component: BookingDisplaySummaryComponent },
  { path: 'customer/add', component: AddCustomerComponent },

];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookingComponent,
    DisplayBookingComponent,
    HeaderComponent,
    FooterComponent,
    RoomComponent,
    ListRoomsComponent,
    CustomerComponent,
    AddCustomerComponent,
    AddBookingComponent,
    BookingSummaryComponent,
    DisplayFormComponent,
    BookingDisplaySummaryComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'fr' }],
  bootstrap: [AppComponent]
})

export class AppModule { }
