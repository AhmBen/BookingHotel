export class ContactMail {

    name: string;
    firstName: string;
    email: string;
    room: string;
    subject: string;
    message: string;


    constructor(name: string, firstName: string, email: string, subject: string, message: string) {
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public getName(): String {
        return this.name;
    }
    
    public getFirstName(): String {
        return this.firstName;
    }

    public getEmail(): String {
        return this.email;
    }

    public getSubject(): String {
        return this.subject;
    }

    public getMessage(): String {
        return this.message;
    }
}
