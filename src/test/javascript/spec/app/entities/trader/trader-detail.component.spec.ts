/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs/observable/of';

import { Workflow2TestModule } from '../../../test.module';
import { TraderDetailComponent } from 'app/entities/trader/trader-detail.component';
import { Trader } from 'app/shared/model/trader.model';

describe('Component Tests', () => {
    describe('Trader Management Detail Component', () => {
        let comp: TraderDetailComponent;
        let fixture: ComponentFixture<TraderDetailComponent>;
        const route = ({ data: of({ trader: new Trader(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Workflow2TestModule],
                declarations: [TraderDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TraderDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TraderDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.trader).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
