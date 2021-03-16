import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateUserComponent } from './user/user-create/user-create.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UnitListComponent } from './unit/unit-list/unit-list.component';
import { UnitCreateComponent } from './unit/unit-create/unit-create.component';
import { TagListComponent } from './tag/tag-list/tag-list.component';
import { TagCreateComponent } from './tag/tag-create/tag-create.component';
import { IngredientListComponent } from './ingredient/ingredient-list/ingredient-list.component';
import { IngredientCreateComponent } from './ingredient/ingredient-create/ingredient-create.component';
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { UserViewComponent } from './user/user-view/user-view.component';
import { RecipeListComponent } from './recipe/recipe-list/recipe-list.component';
import { RecipeCreateComponent } from './recipe/recipe-create/recipe-create.component';
import { RecipeUpdateComponent } from './recipe/recipe-update/recipe-update.component';
import { RecipeViewComponent } from './recipe/recipe-view/recipe-view.component';

const routes: Routes = [
  {path: 'users', component: UserListComponent},
  {path: 'user-create', component: CreateUserComponent},
  {path: 'units', component: UnitListComponent},
  {path: 'unit-create', component: UnitCreateComponent},
  {path: 'tags', component: TagListComponent},
  {path: 'tag-create', component: TagCreateComponent},
  {path: 'ingredients', component: IngredientListComponent},
  {path: 'ingredient-create', component: IngredientCreateComponent},
  {path: 'recipes', component: RecipeListComponent},
  {path: 'recipe-create', component: RecipeCreateComponent},
  {path: '', redirectTo: 'users', pathMatch: 'full'},
  {path: 'user-update/:id', component: UserUpdateComponent},
  {path: 'user-view/:id', component: UserViewComponent},
  {path: 'recipe-update/:id', component: RecipeUpdateComponent},
  {path: 'recipe-view/:id', component: RecipeViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
