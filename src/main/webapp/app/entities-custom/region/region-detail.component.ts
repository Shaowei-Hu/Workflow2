import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { RegionService } from '../../entities/region/region.service';
import { Region } from '../../entities/region/region.model';

@Component({
    selector: 'region-detail',
    templateUrl: './region-detail.component.html',
    styleUrls: [
        'region.scss'
    ]
})
export class RegionDetailComponent implements OnInit, OnDestroy {

    @Input('region')
    region: Region;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private regionService: RegionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // this.subscription = this.route.params.subscribe((params) => {
        //     this.load(params['id']);
        // });
        this.registerChangeInRegion();
    }

    load(id) {
        this.regionService.find(id).subscribe((region) => {
            this.region = region;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        // this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRegion() {
        this.eventSubscriber = this.eventManager.subscribe(
            'regionListModification',
            (response) => this.load(this.region.id)
        );
    }
}
