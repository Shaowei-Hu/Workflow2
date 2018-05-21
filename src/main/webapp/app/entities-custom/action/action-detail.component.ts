import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ActionService } from '../../entities/action/action.service';
import { Action } from '../../entities/action/action.model';

@Component({
    selector: 'action-detail',
    templateUrl: './action-detail.component.html',
    styleUrls: [
        'action.scss'
    ]
})
export class ActionDetailComponent implements OnInit, OnDestroy {

    @Input('action')
    action: Action;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private actionService: ActionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // this.subscription = this.route.params.subscribe((params) => {
        //     this.load(params['id']);
        // });
        this.registerChangeInAction();
    }

    load(id) {
        this.actionService.find(id).subscribe((action) => {
            this.action = action;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        // this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAction() {
        this.eventSubscriber = this.eventManager.subscribe(
            'actionListModification',
            (response) => this.load(this.action.id)
        );
    }
}
