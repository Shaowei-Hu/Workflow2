import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Workflow2SharedModule } from 'app/shared';
import {
    TeamService,
    TeamComponent,
    TeamDetailComponent,
    TeamUpdateComponent,
    TeamDeletePopupComponent,
    TeamDeleteDialogComponent,
    teamRoute,
    teamPopupRoute,
    TeamResolve,
    TeamResolvePagingParams
} from './';

const ENTITY_STATES = [...teamRoute, ...teamPopupRoute];

@NgModule({
    imports: [Workflow2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TeamComponent, TeamDetailComponent, TeamUpdateComponent, TeamDeleteDialogComponent, TeamDeletePopupComponent],
    entryComponents: [TeamComponent, TeamUpdateComponent, TeamDeleteDialogComponent, TeamDeletePopupComponent],
    providers: [TeamService, TeamResolve, TeamResolvePagingParams],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Workflow2TeamModule {}
