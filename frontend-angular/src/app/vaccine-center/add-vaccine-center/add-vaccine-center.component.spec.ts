import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVaccineCenterComponent } from './add-vaccine-center.component';

describe('AddVaccineCenterComponent', () => {
  let component: AddVaccineCenterComponent;
  let fixture: ComponentFixture<AddVaccineCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddVaccineCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddVaccineCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
