import { Component, OnInit } from '@angular/core';
import { RoomService } from './../../services/room.service';
import { Room } from './../../models/room';
import { Title} from '@angular/platform-browser';
import { DatasService } from './../../services/datas.service';
import { Router } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-list-rooms',
  templateUrl: './list-rooms.component.html',
  styleUrls: ['./list-rooms.component.css']
})

export class ListRoomsComponent implements OnInit {

  private room: Room[];

  constructor(private roomService: RoomService,
              private titleService: Title,
              private router: Router,
              private datas: DatasService) {

              this.room = this.datas.checkRoomForm;
              }

  ngOnInit() {
    this.titleService.setTitle('Liste des chambres');
    this.initForm();
  }

  private initForm() {
  }

  public onSubmitForm() {
  }

  public validateRoom(room: Room)  {
    // console.log(room);
    // console.log(this.datas.bookingParam);
    this.datas.choosingRoom = room;
    this.router.navigateByUrl('/customer/add');
  }

  public getRooms() {
    return this.room;
  }

}
