export class RoomType {

    id: number;
    type: String;
    ppn: number;
    nbradults: number;
    nbrchildrens: number;
    surface: number;
    description: String;

    constructor(id: number, type: String, ppn: number, nbradults: number, nbrchildrens: number, surface: number, description: String) {
        this.id = id;
        this.type = type;
        this.ppn = ppn;
        this.nbradults = nbradults;
        this.nbrchildrens = nbrchildrens;
        this.surface = surface;
        this.description = description;
    }

    public getId(): number {
        return this.id;
    }

    public getType(): String {
        return this.type;
    }

    public getPpn(): number {
        return this.ppn;
    }

    public getNbrAdults(): number {
        return this.nbradults;
    }

    public getNbrChildrens(): number {
        return this.nbrchildrens;
    }

    public getSurface(): number {
        return this.surface;
    }

    public getDescription(): String {
        return this.description;
    }
}
