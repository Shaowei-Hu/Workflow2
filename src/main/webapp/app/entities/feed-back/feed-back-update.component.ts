import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IFeedBack } from 'app/shared/model/feed-back.model';
import { FeedBackService } from './feed-back.service';

@Component({
    selector: 'jhi-feed-back-update',
    templateUrl: './feed-back-update.component.html'
})
export class FeedBackUpdateComponent implements OnInit {
    private _feedBack: IFeedBack;
    isSaving: boolean;

    constructor(private feedBackService: FeedBackService, private route: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ feedBack }) => {
            this.feedBack = feedBack.body ? feedBack.body : feedBack;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.feedBack.id !== undefined) {
            this.subscribeToSaveResponse(this.feedBackService.update(this.feedBack));
        } else {
            this.subscribeToSaveResponse(this.feedBackService.create(this.feedBack));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFeedBack>>) {
        result.subscribe((res: HttpResponse<IFeedBack>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get feedBack() {
        return this._feedBack;
    }

    set feedBack(feedBack: IFeedBack) {
        this._feedBack = feedBack;
    }
}
