import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Title} from '@angular/platform-browser';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DatasService } from './../services/datas.service';
import { CommonService } from './../services/common.service';
import { ContactMail } from '../models/contact-mail';


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  control = new FormControl();
  datasForm: FormGroup;
  private submitted: Boolean = false;
  private sendMail: Boolean = false;
  private waiting: Boolean = false;

 // @ViewChild('content') modalContent: TemplateRef<any>;

  constructor(
    private titleService: Title,
    private formBuilder: FormBuilder,
    private datas: DatasService,
    private commonService: CommonService,
    private router: Router) { }
    

  ngOnInit() {
    this.titleService.setTitle('Formulaire de contact - Hotel Fictif');
    this.initForm();
  }

  private initForm() {

    this.datasForm = this.formBuilder.group({
      name      : ['', [Validators.required, Validators.minLength(2)]],
      firstName : ['', [Validators.required, Validators.minLength(2)]],
      email     : ['', [Validators.required, Validators.email]],
      subject   : ['', [Validators.required, Validators.minLength(5)]],
      message   : ['', [Validators.required, Validators.minLength(10)]],
     }
    );
  }

  public onSubmitForm() {

    this.submitted = true;

    const formValue = this.datasForm.value;

    // console.log(this.datasForm);

    if ( !this.datasForm.invalid) {

      this.waiting = true;

      // console.log('Formulaire Valide');

      this.commonService.sendContactMail(new ContactMail(formValue['name'], formValue['firstName'], formValue['email'], formValue['subject'], formValue['message']))
                        .subscribe(
                                    (sendMail: Boolean) => {
                                            this.sendMail = sendMail;
                                            this.waiting = false;
                                        }
                                  );

    } else {
      // console.log('Formulaire Invalide');
    }
}

public get f() {
  // console.log(this.datasForm.controls); 
  return this.datasForm.controls; 
}

public getSubmitted() {
  return this.submitted;
}

public getSendMail() {
  return this.sendMail;
}

public getWaiting() {
  return this.waiting;
}

public redirectHome() {
  this.router.navigateByUrl('/');
}

}
