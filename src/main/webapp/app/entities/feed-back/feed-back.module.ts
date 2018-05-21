import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Workflow2SharedModule } from 'app/shared';
import {
    FeedBackService,
    FeedBackComponent,
    FeedBackDetailComponent,
    FeedBackUpdateComponent,
    FeedBackDeletePopupComponent,
    FeedBackDeleteDialogComponent,
    feedBackRoute,
    feedBackPopupRoute,
    FeedBackResolve,
    FeedBackResolvePagingParams
} from './';

const ENTITY_STATES = [...feedBackRoute, ...feedBackPopupRoute];

@NgModule({
    imports: [Workflow2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FeedBackComponent,
        FeedBackDetailComponent,
        FeedBackUpdateComponent,
        FeedBackDeleteDialogComponent,
        FeedBackDeletePopupComponent
    ],
    entryComponents: [FeedBackComponent, FeedBackUpdateComponent, FeedBackDeleteDialogComponent, FeedBackDeletePopupComponent],
    providers: [FeedBackService, FeedBackResolve, FeedBackResolvePagingParams],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Workflow2FeedBackModule {}
