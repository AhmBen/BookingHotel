import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { Room } from '../../models/room';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.css']
})
export class RoomDetailComponent implements OnInit {

  private room: Room;
  private id;

  constructor(private roomService: RoomService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
   this.loadAliment();
  }

  private loadAliment() {
    this.id = this.route.snapshot.paramMap.get('id');
    // this.roomService.getRoom(Number(id)).subscribe((room: Room ) => { this.room = room; } );
  }

  public getRoom(): Room {
    return this.room;
  }

  public getId() {
    return this.id;
  }

}
