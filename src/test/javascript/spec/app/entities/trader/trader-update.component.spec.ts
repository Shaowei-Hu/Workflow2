/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Workflow2TestModule } from '../../../test.module';
import { TraderUpdateComponent } from 'app/entities/trader/trader-update.component';
import { TraderService } from 'app/entities/trader/trader.service';
import { Trader } from 'app/shared/model/trader.model';

import { TeamService } from 'app/entities/team';

describe('Component Tests', () => {
    describe('Trader Management Update Component', () => {
        let comp: TraderUpdateComponent;
        let fixture: ComponentFixture<TraderUpdateComponent>;
        let service: TraderService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Workflow2TestModule],
                declarations: [TraderUpdateComponent],
                providers: [TeamService, TraderService]
            })
                .overrideTemplate(TraderUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TraderUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraderService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Trader(123);
                    spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({ body: entity })));
                    comp.trader = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Trader();
                    spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({ body: entity })));
                    comp.trader = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
