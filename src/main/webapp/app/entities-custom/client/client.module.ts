import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BigDataForLifeSharedModule } from '../../shared';
import {
    ClientService,
    ClientDetailComponent,
} from './';

@NgModule({
    imports: [
        BigDataForLifeSharedModule,
    ],
    declarations: [
        ClientDetailComponent,
    ],
    entryComponents: [
    ],
    providers: [
        ClientService,

    ],
    exports: [
        ClientDetailComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BigDataForLifeClientCustomModule {}
