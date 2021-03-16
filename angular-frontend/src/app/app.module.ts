import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { CreateUserComponent } from './user/user-create/user-create.component';
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
import { ModalComponent } from './modal/modal/modal.component';
import { UserListRecipesComponent } from './user/user-list-recipes/user-list-recipes.component';


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    CreateUserComponent,
    UnitListComponent,
    UnitCreateComponent,
    TagListComponent,
    TagCreateComponent,
    IngredientListComponent,
    IngredientCreateComponent,
    UserUpdateComponent,
    UserViewComponent,
    RecipeListComponent,
    RecipeCreateComponent,
    RecipeUpdateComponent,
    RecipeViewComponent,
    ModalComponent,
    UserListRecipesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
