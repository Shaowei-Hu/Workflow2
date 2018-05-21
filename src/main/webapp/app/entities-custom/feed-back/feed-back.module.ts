import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BigDataForLifeSharedModule } from '../../shared';
import {
    FeedBackService,
    FeedBackDetailComponent,
} from './';

@NgModule({
    imports: [
        BigDataForLifeSharedModule,
    ],
    declarations: [
        FeedBackDetailComponent,
    ],
    entryComponents: [
    ],
    providers: [
        FeedBackService,

    ],
    exports: [
        FeedBackDetailComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BigDataForLifeFeedBackCustomModule {}
