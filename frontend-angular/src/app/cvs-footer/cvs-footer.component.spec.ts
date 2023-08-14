import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvsFooterComponent } from './cvs-footer.component';

describe('CvsFooterComponent', () => {
  let component: CvsFooterComponent;
  let fixture: ComponentFixture<CvsFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CvsFooterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CvsFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
