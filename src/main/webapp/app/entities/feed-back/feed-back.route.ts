import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from 'app/core';
import { FeedBack } from 'app/shared/model/feed-back.model';
import { FeedBackService } from './feed-back.service';
import { FeedBackComponent } from './feed-back.component';
import { FeedBackDetailComponent } from './feed-back-detail.component';
import { FeedBackUpdateComponent } from './feed-back-update.component';
import { FeedBackDeletePopupComponent } from './feed-back-delete-dialog.component';

@Injectable()
export class FeedBackResolvePagingParams implements Resolve<any> {
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
export class FeedBackResolve implements Resolve<any> {
    constructor(private service: FeedBackService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new FeedBack();
    }
}

export const feedBackRoute: Routes = [
    {
        path: 'feed-back',
        component: FeedBackComponent,
        resolve: {
            pagingParams: FeedBackResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.feedBack.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feed-back/:id/view',
        component: FeedBackDetailComponent,
        resolve: {
            feedBack: FeedBackResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.feedBack.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feed-back/new',
        component: FeedBackUpdateComponent,
        resolve: {
            feedBack: FeedBackResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.feedBack.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feed-back/:id/edit',
        component: FeedBackUpdateComponent,
        resolve: {
            feedBack: FeedBackResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.feedBack.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const feedBackPopupRoute: Routes = [
    {
        path: 'feed-back/:id/delete',
        component: FeedBackDeletePopupComponent,
        resolve: {
            feedBack: FeedBackResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'workflow2App.feedBack.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
