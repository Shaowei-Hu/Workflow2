/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { Workflow2TestModule } from '../../../test.module';
import { FeedBackDeleteDialogComponent } from 'app/entities/feed-back/feed-back-delete-dialog.component';
import { FeedBackService } from 'app/entities/feed-back/feed-back.service';

describe('Component Tests', () => {
    describe('FeedBack Management Delete Component', () => {
        let comp: FeedBackDeleteDialogComponent;
        let fixture: ComponentFixture<FeedBackDeleteDialogComponent>;
        let service: FeedBackService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Workflow2TestModule],
                declarations: [FeedBackDeleteDialogComponent],
                providers: [FeedBackService]
            })
                .overrideTemplate(FeedBackDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FeedBackDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeedBackService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
