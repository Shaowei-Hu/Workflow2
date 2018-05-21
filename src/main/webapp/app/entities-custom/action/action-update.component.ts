import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IAction } from 'app/shared/model/action.model';
import { ActionService } from './action.service';
import { IFeedBack } from 'app/shared/model/feed-back.model';
import { FeedBackService } from 'app/entities/feed-back';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client';

@Component({
    selector: 'jhi-action-update',
    templateUrl: './action-update.component.html'
})
export class ActionUpdateComponent implements OnInit {
    private _action: IAction;
    isSaving: boolean;

    feedbacks: IFeedBack[];

    clients: IClient[];
    date: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private actionService: ActionService,
        private feedBackService: FeedBackService,
        private clientService: ClientService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ action }) => {
            this.action = action.body ? action.body : action;
        });
        this.feedBackService.query({ filter: 'action-is-null' }).subscribe(
            (res: HttpResponse<IFeedBack[]>) => {
                if (!this.action.feedBackId) {
                    this.feedbacks = res.body;
                } else {
                    this.feedBackService.find(this.action.feedBackId).subscribe(
                        (subRes: HttpResponse<IFeedBack>) => {
                            this.feedbacks = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.clientService.query().subscribe(
            (res: HttpResponse<IClient[]>) => {
                this.clients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.action.date = moment(this.date, DATE_TIME_FORMAT);
        if (this.action.id !== undefined) {
            this.subscribeToSaveResponse(this.actionService.update(this.action));
        } else {
            this.subscribeToSaveResponse(this.actionService.create(this.action));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAction>>) {
        result.subscribe((res: HttpResponse<IAction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackFeedBackById(index: number, item: IFeedBack) {
        return item.id;
    }

    trackClientById(index: number, item: IClient) {
        return item.id;
    }
    get action() {
        return this._action;
    }

    set action(action: IAction) {
        this._action = action;
        this.date = moment(action.date).format();
    }
}
