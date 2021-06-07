import { Authority } from "./authority";

export class User {
    id: number;
    username: string;
    firstname: string;
    lastname: string;
    phone: string;
    description: string;
    authorities: Authority[];
}
