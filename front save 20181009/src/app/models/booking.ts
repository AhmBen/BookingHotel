import { Customer } from './customer';
import { Room } from './room';

export class Booking {

    id: number;
    numBooking: String;
    customer: Customer;
    room: Room;
    dateIn: string;
    dateOut: string;
    nbrAdults: number;
    nbrChildrens: number;
    breakfast: Boolean;
    nbrNights: number;

    constructor(id: number, numBooking: String, customer: Customer, room: Room, dateIn: string, dateOut: string,
                nbrAdults: number, nbrChildrens: number, breakfast: Boolean, nbrNights: number) {
        this.id = id;
        this.numBooking = numBooking;
        this.customer = customer;
        this.room = room;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.nbrAdults = nbrAdults;
        this.nbrChildrens = nbrChildrens;
        this.breakfast = breakfast;
        this.nbrNights = nbrNights;
    }

    public getId(): number {
        return this.id;
    }

    public getNumBooking(): String {
        return this.numBooking;
    }

    public getCustomer(): Customer {
        return this.customer;
    }

    public getRoom(): Room {
        return this.room;
    }

    public getDateIn(): string {
        return this.dateIn;
    }

    public getDateOut(): string {
        return this.dateOut;
    }

    public getNbrAdults(): number {
        return this.nbrAdults;
    }

    public getNbrChildrens(): number {
        return this.nbrChildrens;
    }

    public getBreakfast(): Boolean {
        return this.breakfast;
    }

    public getNbrNights(): number {
        return this.nbrNights;
    }
}
