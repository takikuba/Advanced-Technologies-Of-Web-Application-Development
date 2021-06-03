import { Authority } from "./authority";

export class User {
    id: number;
    username: string;
    firstname: string;
    lastname: string;
    authorities: Authority[];
}
