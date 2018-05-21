import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFeedBack } from 'app/shared/model/feed-back.model';
import { FeedBackService } from './feed-back.service';

@Component({
    selector: 'jhi-feed-back-delete-dialog',
    templateUrl: './feed-back-delete-dialog.component.html'
})
export class FeedBackDeleteDialogComponent {
    feedBack: IFeedBack;

    constructor(private feedBackService: FeedBackService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.feedBackService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'feedBackListModification',
                content: 'Deleted an feedBack'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-feed-back-delete-popup',
    template: ''
})
export class FeedBackDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private route: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.route.data.subscribe(({ feedBack }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FeedBackDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.feedBack = feedBack.body;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
