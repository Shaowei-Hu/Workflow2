import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ClientService } from '../../entities/client/client.service';
import { Client } from '../../entities/client/client.model';

@Component({
    selector: 'client-detail',
    templateUrl: './client-detail.component.html',
    styleUrls: [
        'client.scss'
    ]
})
export class ClientDetailComponent implements OnInit, OnDestroy {

    @Input('client')
    client: Client;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private clientService: ClientService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // this.subscription = this.route.params.subscribe((params) => {
        //     this.load(params['id']);
        // });
        this.registerChangeInClient();
    }

    load(id) {
        this.clientService.find(id).subscribe((client) => {
            this.client = client;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        // this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInClient() {
        this.eventSubscriber = this.eventManager.subscribe(
            'clientListModification',
            (response) => this.load(this.client.id)
        );
    }
}
