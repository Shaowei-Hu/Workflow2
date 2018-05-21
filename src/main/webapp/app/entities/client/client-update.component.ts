import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { JhiAlertService } from 'ng-jhipster';

import { IClient } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { ITrader } from 'app/shared/model/trader.model';
import { TraderService } from 'app/entities/trader';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region';
import { IType } from 'app/shared/model/type.model';
import { TypeService } from 'app/entities/type';

@Component({
    selector: 'jhi-client-update',
    templateUrl: './client-update.component.html'
})
export class ClientUpdateComponent implements OnInit {
    private _client: IClient;
    isSaving: boolean;

    traders: ITrader[];

    regions: IRegion[];

    types: IType[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private clientService: ClientService,
        private traderService: TraderService,
        private regionService: RegionService,
        private typeService: TypeService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ client }) => {
            this.client = client.body ? client.body : client;
        });
        this.traderService.query().subscribe(
            (res: HttpResponse<ITrader[]>) => {
                this.traders = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.regionService.query().subscribe(
            (res: HttpResponse<IRegion[]>) => {
                this.regions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.typeService.query().subscribe(
            (res: HttpResponse<IType[]>) => {
                this.types = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.client.id !== undefined) {
            this.subscribeToSaveResponse(this.clientService.update(this.client));
        } else {
            this.subscribeToSaveResponse(this.clientService.create(this.client));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>) {
        result.subscribe((res: HttpResponse<IClient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTraderById(index: number, item: ITrader) {
        return item.id;
    }

    trackRegionById(index: number, item: IRegion) {
        return item.id;
    }

    trackTypeById(index: number, item: IType) {
        return item.id;
    }
    get client() {
        return this._client;
    }

    set client(client: IClient) {
        this._client = client;
    }
}
