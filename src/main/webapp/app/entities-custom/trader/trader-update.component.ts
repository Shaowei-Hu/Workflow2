import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { JhiAlertService } from 'ng-jhipster';

import { ITrader } from 'app/shared/model/trader.model';
import { TraderService } from './trader.service';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team';

@Component({
    selector: 'jhi-trader-update',
    templateUrl: './trader-update.component.html'
})
export class TraderUpdateComponent implements OnInit {
    private _trader: ITrader;
    isSaving: boolean;

    teams: ITeam[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private traderService: TraderService,
        private teamService: TeamService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ trader }) => {
            this.trader = trader.body ? trader.body : trader;
        });
        this.teamService.query().subscribe(
            (res: HttpResponse<ITeam[]>) => {
                this.teams = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.trader.id !== undefined) {
            this.subscribeToSaveResponse(this.traderService.update(this.trader));
        } else {
            this.subscribeToSaveResponse(this.traderService.create(this.trader));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITrader>>) {
        result.subscribe((res: HttpResponse<ITrader>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTeamById(index: number, item: ITeam) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get trader() {
        return this._trader;
    }

    set trader(trader: ITrader) {
        this._trader = trader;
    }
}
