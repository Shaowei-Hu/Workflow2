import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClient } from 'app/shared/model/client.model';

@Component({
    selector: 'jhi-client-detail',
    templateUrl: './client-detail.component.html'
})
export class ClientDetailComponent implements OnInit {
    client: IClient;

    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.data.subscribe(({ client }) => {
            this.client = client.body ? client.body : client;
        });
    }

    previousState() {
        window.history.back();
    }
}
