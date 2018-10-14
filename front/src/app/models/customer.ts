export class Customer {

    id: number;
    civility: number;
    name: String;
    firstName: String;
    dob: string;
    mail: String;
    address: String;
    zipcode: number;
    city: String;
    country: String;
    phone: String;
    newsletter: Boolean;

    constructor(id: number, civility: number, name: String, firstName: String, dob: string, mail: String, address: String,
                zipcode: number, city: String, country: String, phone: String, newsletter: Boolean) {
        this.id = id;
        this.civility = civility;
        this.name = name;
        this.firstName = firstName;
        this.dob = dob;
        this.mail = mail;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.newsletter = newsletter;
    }

    public getId(): number {
        return this.id;
    }

    public getCivility(): number {
        return this.civility;
    }

    public getName(): String {
        return this.name;
    }

    public getFirstName(): String {
        return this.firstName;
    }

    public getDob(): string {
        return this.dob;
    }

    public getMail(): String {
        return this.mail;
    }

    public getAddress(): String {
        return this.address;
    }

    public getZipcode(): number {
        return this.zipcode;
    }

    public getCity(): String {
        return this.city;
    }

    public getCountry(): String {
        return this.country;
    }

    public getPhone(): String {
        return this.phone;
    }

    public getNewsletter(): Boolean {
        return this.newsletter;
    }
}
