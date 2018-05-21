import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Workflow2SharedModule } from 'app/shared';
import {
    TypeService,
    TypeComponent,
    TypeDetailComponent,
    TypeUpdateComponent,
    TypeDeletePopupComponent,
    TypeDeleteDialogComponent,
    typeRoute,
    typePopupRoute,
    TypeResolve,
    TypeResolvePagingParams
} from './';

const ENTITY_STATES = [...typeRoute, ...typePopupRoute];

@NgModule({
    imports: [Workflow2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TypeComponent, TypeDetailComponent, TypeUpdateComponent, TypeDeleteDialogComponent, TypeDeletePopupComponent],
    entryComponents: [TypeComponent, TypeUpdateComponent, TypeDeleteDialogComponent, TypeDeletePopupComponent],
    providers: [TypeService, TypeResolve, TypeResolvePagingParams],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Workflow2TypeModule {}
