import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BigDataForLifeSharedModule } from '../../shared';
import {
    TeamService,
    TeamDetailComponent,
} from './';

@NgModule({
    imports: [
        BigDataForLifeSharedModule,
    ],
    declarations: [
        TeamDetailComponent,
    ],
    entryComponents: [
    ],
    providers: [
        TeamService,

    ],
    exports: [
        TeamDetailComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BigDataForLifeTeamCustomModule {}
