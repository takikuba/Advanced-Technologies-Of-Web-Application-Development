import { Ingredient } from "./ingredient";
import { Tag } from "./tag";

export class Recipe {
    id: number;
    name: string;
    description: string;
    likeB: number;
    dislikeB: number;
    kcal: number;
    time: number;
    image: string;
    link: string;
    ingredients: Ingredient[];
    tags: Tag[];
}
