/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Workflow2TestModule } from '../../../test.module';
import { ActionUpdateComponent } from 'app/entities/action/action-update.component';
import { ActionService } from 'app/entities/action/action.service';
import { Action } from 'app/shared/model/action.model';

import { FeedBackService } from 'app/entities/feed-back';
import { ClientService } from 'app/entities/client';

describe('Component Tests', () => {
    describe('Action Management Update Component', () => {
        let comp: ActionUpdateComponent;
        let fixture: ComponentFixture<ActionUpdateComponent>;
        let service: ActionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Workflow2TestModule],
                declarations: [ActionUpdateComponent],
                providers: [FeedBackService, ClientService, ActionService]
            })
                .overrideTemplate(ActionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ActionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Action(123);
                    spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({ body: entity })));
                    comp.action = entity;
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
                    const entity = new Action();
                    spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({ body: entity })));
                    comp.action = entity;
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
