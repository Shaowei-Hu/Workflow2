import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Workflow2TraderModule } from './trader/trader.module';
import { Workflow2TeamModule } from './team/team.module';
import { Workflow2ClientModule } from './client/client.module';
import { Workflow2ActionModule } from './action/action.module';
import { Workflow2FeedBackModule } from './feed-back/feed-back.module';
import { Workflow2RegionModule } from './region/region.module';
import { Workflow2TypeModule } from './type/type.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Workflow2TraderModule,
        Workflow2TeamModule,
        Workflow2ClientModule,
        Workflow2ActionModule,
        Workflow2FeedBackModule,
        Workflow2RegionModule,
        Workflow2TypeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Workflow2EntityModule {}
