import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TypeService } from '../../entities/type/type.service';
import { Type } from '../../entities/type/type.model';

@Component({
    selector: 'type-detail',
    templateUrl: './type-detail.component.html',
    styleUrls: [
        'type.scss'
    ]
})
export class TypeDetailComponent implements OnInit, OnDestroy {

    @Input('type')
    type: Type;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private typeService: TypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // this.subscription = this.route.params.subscribe((params) => {
        //     this.load(params['id']);
        // });
        this.registerChangeInType();
    }

    load(id) {
        this.typeService.find(id).subscribe((type) => {
            this.type = type;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        // this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInType() {
        this.eventSubscriber = this.eventManager.subscribe(
            'typeListModification',
            (response) => this.load(this.type.id)
        );
    }
}
