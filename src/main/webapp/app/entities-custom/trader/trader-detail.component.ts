import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrader } from 'app/shared/model/trader.model';

@Component({
    selector: 'jhi-trader-detail',
    templateUrl: './trader-detail.component.html'
})
export class TraderDetailComponent implements OnInit {
    trader: ITrader;

    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.data.subscribe(({ trader }) => {
            this.trader = trader.body ? trader.body : trader;
        });
    }

    previousState() {
        window.history.back();
    }
}
