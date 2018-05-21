import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from 'app/core';
import { Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { ClientComponent } from './client.component';
import { ClientDetailComponent } from './client-detail.component';
import { ClientUpdateComponent } from './client-update.component';
import { ClientDeletePopupComponent } from './client-delete-dialog.component';

@Injectable()
export class ClientResolvePagingParams implements Resolve<any> {
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
export class ClientResolve implements Resolve<any> {
    constructor(private service: ClientService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new Client();
    }
}

export const clientRoute: Routes = [
    {
        path: 'client-custom',
        component: ClientComponent,
        resolve: {
            pagingParams: ClientResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.client.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'client-custom/:id/view',
        component: ClientDetailComponent,
        resolve: {
            client: ClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.client.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'client-custom/new',
        component: ClientUpdateComponent,
        resolve: {
            client: ClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.client.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'client-custom/:id/edit',
        component: ClientUpdateComponent,
        resolve: {
            client: ClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.client.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const clientPopupRoute: Routes = [
    {
        path: 'client-custom/:id/delete',
        component: ClientDeletePopupComponent,
        resolve: {
            client: ClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.client.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
