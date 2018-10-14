import { Component, OnInit } from '@angular/core';
import { Title} from '@angular/platform-browser';

@Component({
  selector: 'app-equipements',
  templateUrl: './equipements.component.html',
  styleUrls: ['./equipements.component.css']
})
export class EquipementsComponent implements OnInit {

  constructor(private titleService: Title) { }

  ngOnInit() {
    this.titleService.setTitle('Equipements - Hotel Fictif');
  }

}
