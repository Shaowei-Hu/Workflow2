import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from 'app/core';
import { Trader } from 'app/shared/model/trader.model';
import { TraderService } from './trader.service';
import { TraderComponent } from './trader.component';
import { TraderDetailComponent } from './trader-detail.component';
import { TraderUpdateComponent } from './trader-update.component';
import { TraderDeletePopupComponent } from './trader-delete-dialog.component';

@Injectable()
export class TraderResolvePagingParams implements Resolve<any> {
    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
        };
    }
}

@Injectable()
export class TraderResolve implements Resolve<any> {
    constructor(private service: TraderService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new Trader();
    }
}

export const traderRoute: Routes = [
    {
        path: 'trader-custom',
        component: TraderComponent,
        resolve: {
            pagingParams: TraderResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.trader.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'trader-custom/:id/view',
        component: TraderDetailComponent,
        resolve: {
            trader: TraderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.trader.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'trader-custom/new',
        component: TraderUpdateComponent,
        resolve: {
            trader: TraderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.trader.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'trader-custom/:id/edit',
        component: TraderUpdateComponent,
        resolve: {
            trader: TraderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.trader.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const traderPopupRoute: Routes = [
    {
        path: 'trader-custom/:id/delete',
        component: TraderDeletePopupComponent,
        resolve: {
            trader: TraderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.trader.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
