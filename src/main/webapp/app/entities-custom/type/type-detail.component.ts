import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IType } from 'app/shared/model/type.model';

@Component({
    selector: 'jhi-type-detail',
    templateUrl: './type-detail.component.html'
})
export class TypeDetailComponent implements OnInit {
    type: IType;

    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.data.subscribe(({ type }) => {
            this.type = type.body ? type.body : type;
        });
    }

    previousState() {
        window.history.back();
    }
}
