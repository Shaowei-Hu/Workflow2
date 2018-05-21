import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from 'app/core';
import { Region } from 'app/shared/model/region.model';
import { RegionService } from './region.service';
import { RegionComponent } from './region.component';
import { RegionDetailComponent } from './region-detail.component';
import { RegionUpdateComponent } from './region-update.component';
import { RegionDeletePopupComponent } from './region-delete-dialog.component';

@Injectable()
export class RegionResolvePagingParams implements Resolve<any> {
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
export class RegionResolve implements Resolve<any> {
    constructor(private service: RegionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new Region();
    }
}

export const regionRoute: Routes = [
    {
        path: 'region',
        component: RegionComponent,
        resolve: {
            pagingParams: RegionResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.region.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'region/:id/view',
        component: RegionDetailComponent,
        resolve: {
            region: RegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.region.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'region/new',
        component: RegionUpdateComponent,
        resolve: {
            region: RegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.region.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'region/:id/edit',
        component: RegionUpdateComponent,
        resolve: {
            region: RegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.region.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const regionPopupRoute: Routes = [
    {
        path: 'region/:id/delete',
        component: RegionDeletePopupComponent,
        resolve: {
            region: RegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.region.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
