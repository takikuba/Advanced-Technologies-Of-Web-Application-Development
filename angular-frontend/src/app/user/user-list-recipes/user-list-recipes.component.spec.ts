import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserListRecipesComponent } from './user-list-recipes.component';

describe('UserListRecipesComponent', () => {
  let component: UserListRecipesComponent;
  let fixture: ComponentFixture<UserListRecipesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserListRecipesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserListRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
