import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClient } from 'app/shared/model/client.model';
import { ClientService } from './client.service';

@Component({
    selector: 'jhi-client-delete-dialog',
    templateUrl: './client-delete-dialog.component.html'
})
export class ClientDeleteDialogComponent {
    client: IClient;

    constructor(private clientService: ClientService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.clientService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'clientListModification',
                content: 'Deleted an client'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-client-delete-popup',
    template: ''
})
export class ClientDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private route: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.route.data.subscribe(({ client }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ClientDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.client = client.body;
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
