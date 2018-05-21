import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BigDataForLifeSharedModule } from '../../shared';
import {
    TraderService,
    TraderDetailComponent,
} from './';

@NgModule({
    imports: [
        BigDataForLifeSharedModule,
    ],
    declarations: [
        TraderDetailComponent,
    ],
    entryComponents: [
    ],
    providers: [
        TraderService,

    ],
    exports: [
        TraderDetailComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BigDataForLifeTraderCustomModule {}
