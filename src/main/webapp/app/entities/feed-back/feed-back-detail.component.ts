import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFeedBack } from 'app/shared/model/feed-back.model';

@Component({
    selector: 'jhi-feed-back-detail',
    templateUrl: './feed-back-detail.component.html'
})
export class FeedBackDetailComponent implements OnInit {
    feedBack: IFeedBack;

    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.data.subscribe(({ feedBack }) => {
            this.feedBack = feedBack.body ? feedBack.body : feedBack;
        });
    }

    previousState() {
        window.history.back();
    }
}
