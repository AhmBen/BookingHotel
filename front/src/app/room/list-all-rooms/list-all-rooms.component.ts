import { Component, OnInit } from '@angular/core';
import { RoomService } from './../../services/room.service';
import { Room } from './../../models/room';
import { Title} from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-all-rooms',
  templateUrl: './list-all-rooms.component.html',
  styleUrls: ['./list-all-rooms.component.css']
})
export class ListAllRoomsComponent implements OnInit {

  private room: Room[];

  constructor(private roomService: RoomService,
              private titleService: Title,
              private router: Router) {
              }

  ngOnInit() {
    this.titleService.setTitle('Liste des chambres & Suites');
    this.getAllRooms()
  }

  public getAllRooms() {
    this.roomService.getAllRooms().subscribe((room: Room[]) => { this.room = room; } ) ;
  }

  public validateRoom(room: Room)  {
    console.log(room);
    // console.log(this.datas.bookingParam);

     this.router.navigateByUrl('/room/detail/'+room.roomType.id);
   }

  public getRooms() {
    return this.room;
  }

}
