import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home';
import {LoginComponent} from './login';
import {AdminComponent} from './admin';
import {AdminGuard, GuestGuard, LoginGuard} from './guard';
import {NotFoundComponent} from './not-found';
import {ChangePasswordComponent} from './change-password';
import {ForbiddenComponent} from './forbidden';
import {SignupComponent} from './signup';
import { RecipeViewComponent } from './recipe-view/recipe-view/recipe-view.component';
import { AdminUsersComponent } from './admin/admin-users/admin-users.component';
import { AdminTagsComponent } from './admin/admin-tags/admin-tags.component';
import { AdminIngredientsComponent } from './admin/admin-ingredients/admin-ingredients.component';
import { AdminUnitComponent } from './admin/admin-unit/admin-unit.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent,
    canActivate: [GuestGuard],
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'admin/tags',
    component: AdminTagsComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'admin/ingredients',
    component: AdminIngredientsComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'admin/users',
    component: AdminUsersComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'admin/units',
    component: AdminUnitComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'recipes/:id',
    component: RecipeViewComponent
  },
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '403',
    component: ForbiddenComponent
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {
}
