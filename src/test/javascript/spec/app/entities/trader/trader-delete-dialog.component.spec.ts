/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { Workflow2TestModule } from '../../../test.module';
import { TraderDeleteDialogComponent } from 'app/entities/trader/trader-delete-dialog.component';
import { TraderService } from 'app/entities/trader/trader.service';

describe('Component Tests', () => {
    describe('Trader Management Delete Component', () => {
        let comp: TraderDeleteDialogComponent;
        let fixture: ComponentFixture<TraderDeleteDialogComponent>;
        let service: TraderService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Workflow2TestModule],
                declarations: [TraderDeleteDialogComponent],
                providers: [TraderService]
            })
                .overrideTemplate(TraderDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TraderDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraderService);
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
