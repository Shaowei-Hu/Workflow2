import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Workflow2SharedModule } from 'app/shared';
import {
    TraderService,
    TraderComponent,
    TraderDetailComponent,
    TraderUpdateComponent,
    TraderDeletePopupComponent,
    TraderDeleteDialogComponent,
    traderRoute,
    traderPopupRoute,
    TraderResolve,
    TraderResolvePagingParams
} from './';

const ENTITY_STATES = [...traderRoute, ...traderPopupRoute];

@NgModule({
    imports: [Workflow2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TraderComponent, TraderDetailComponent, TraderUpdateComponent, TraderDeleteDialogComponent, TraderDeletePopupComponent],
    entryComponents: [TraderComponent, TraderUpdateComponent, TraderDeleteDialogComponent, TraderDeletePopupComponent],
    providers: [TraderService, TraderResolve, TraderResolvePagingParams],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Workflow2TraderModule {}
