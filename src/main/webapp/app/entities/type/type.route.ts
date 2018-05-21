import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from 'app/core';
import { Type } from 'app/shared/model/type.model';
import { TypeService } from './type.service';
import { TypeComponent } from './type.component';
import { TypeDetailComponent } from './type-detail.component';
import { TypeUpdateComponent } from './type-update.component';
import { TypeDeletePopupComponent } from './type-delete-dialog.component';

@Injectable()
export class TypeResolvePagingParams implements Resolve<any> {
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
export class TypeResolve implements Resolve<any> {
    constructor(private service: TypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new Type();
    }
}

export const typeRoute: Routes = [
    {
        path: 'type',
        component: TypeComponent,
        resolve: {
            pagingParams: TypeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.type.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type/:id/view',
        component: TypeDetailComponent,
        resolve: {
            type: TypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.type.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type/new',
        component: TypeUpdateComponent,
        resolve: {
            type: TypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.type.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type/:id/edit',
        component: TypeUpdateComponent,
        resolve: {
            type: TypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.type.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typePopupRoute: Routes = [
    {
        path: 'type/:id/delete',
        component: TypeDeletePopupComponent,
        resolve: {
            type: TypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.type.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
