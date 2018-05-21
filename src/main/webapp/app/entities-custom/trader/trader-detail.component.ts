import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TraderService } from '../../entities/trader/trader.service';
import { Trader } from '../../entities/trader/trader.model';

@Component({
    selector: 'trader-detail',
    templateUrl: './trader-detail.component.html',
    styleUrls: [
        'trader.scss'
    ]
})
export class TraderDetailComponent implements OnInit, OnDestroy {

    @Input('trader')
    trader: Trader;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private traderService: TraderService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // this.subscription = this.route.params.subscribe((params) => {
        //     this.load(params['id']);
        // });
        this.registerChangeInTrader();
    }

    load(id) {
        this.traderService.find(id).subscribe((trader) => {
            this.trader = trader;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        // this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTrader() {
        this.eventSubscriber = this.eventManager.subscribe(
            'traderListModification',
            (response) => this.load(this.trader.id)
        );
    }
}
