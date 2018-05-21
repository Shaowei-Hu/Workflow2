import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegion } from 'app/shared/model/region.model';

@Component({
    selector: 'jhi-region-detail',
    templateUrl: './region-detail.component.html'
})
export class RegionDetailComponent implements OnInit {
    region: IRegion;

    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.data.subscribe(({ region }) => {
            this.region = region.body ? region.body : region;
        });
    }

    previousState() {
        window.history.back();
    }
}
