/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Workflow2TestModule } from '../../../test.module';
import { FeedBackUpdateComponent } from 'app/entities/feed-back/feed-back-update.component';
import { FeedBackService } from 'app/entities/feed-back/feed-back.service';
import { FeedBack } from 'app/shared/model/feed-back.model';

describe('Component Tests', () => {
    describe('FeedBack Management Update Component', () => {
        let comp: FeedBackUpdateComponent;
        let fixture: ComponentFixture<FeedBackUpdateComponent>;
        let service: FeedBackService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Workflow2TestModule],
                declarations: [FeedBackUpdateComponent],
                providers: [FeedBackService]
            })
                .overrideTemplate(FeedBackUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FeedBackUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeedBackService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FeedBack(123);
                    spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({ body: entity })));
                    comp.feedBack = entity;
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
                    const entity = new FeedBack();
                    spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({ body: entity })));
                    comp.feedBack = entity;
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
