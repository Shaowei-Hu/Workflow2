import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BigDataForLifeSharedModule } from '../../shared';
import {
    RegionService,
    RegionDetailComponent,
} from './';

@NgModule({
    imports: [
        BigDataForLifeSharedModule,
    ],
    declarations: [
        RegionDetailComponent,
    ],
    entryComponents: [
    ],
    providers: [
        RegionService,

    ],
    exports: [
        RegionDetailComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BigDataForLifeRegionCustomModule {}
