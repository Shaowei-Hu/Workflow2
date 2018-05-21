import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FeedBackService } from '../../entities/feed-back/feed-back.service';
import { FeedBack } from '../../entities/feed-back/feed-back.model';

@Component({
    selector: 'feed-back-detail',
    templateUrl: './feed-back-detail.component.html',
    styleUrls: [
        'feed-back.scss'
    ]
})
export class FeedBackDetailComponent implements OnInit, OnDestroy {

    @Input('feedBack')
    feedBack: FeedBack;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private feedBackService: FeedBackService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // this.subscription = this.route.params.subscribe((params) => {
        //     this.load(params['id']);
        // });
        this.registerChangeInFeedBack();
    }

    load(id) {
        this.feedBackService.find(id).subscribe((feedBack) => {
            this.feedBack = feedBack;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        // this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFeedBack() {
        this.eventSubscriber = this.eventManager.subscribe(
            'feedBackListModification',
            (response) => this.load(this.feedBack.id)
        );
    }
}
