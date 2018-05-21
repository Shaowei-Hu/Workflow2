import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from 'app/core';
import { Action } from 'app/shared/model/action.model';
import { ActionService } from './action.service';
import { ActionComponent } from './action.component';
import { ActionDetailComponent } from './action-detail.component';
import { ActionUpdateComponent } from './action-update.component';
import { ActionDeletePopupComponent } from './action-delete-dialog.component';

@Injectable()
export class ActionResolvePagingParams implements Resolve<any> {
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
export class ActionResolve implements Resolve<any> {
    constructor(private service: ActionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new Action();
    }
}

export const actionRoute: Routes = [
    {
        path: 'action-custom',
        component: ActionComponent,
        resolve: {
            pagingParams: ActionResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.action.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'action-custom/:id/view',
        component: ActionDetailComponent,
        resolve: {
            action: ActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.action.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'action-custom/new',
        component: ActionUpdateComponent,
        resolve: {
            action: ActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.action.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'action-custom/:id/edit',
        component: ActionUpdateComponent,
        resolve: {
            action: ActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.action.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const actionPopupRoute: Routes = [
    {
        path: 'action-custom/:id/delete',
        component: ActionDeletePopupComponent,
        resolve: {
            action: ActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.action.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
