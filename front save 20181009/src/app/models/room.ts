import { RoomType } from './roomType';

export class Room {

    id: number;
    roomType: RoomType;

    constructor(id: number, roomType: RoomType) {
        this.id = id;
        this.roomType = roomType;
    }

    public getId(): number {
        return this.id;
    }

    public getRoomType(): RoomType {
        return this.roomType;
    }
}
