import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Action } from '../../entities/action/action.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ActionService {

    private resourceUrl = SERVER_API_URL + 'api/action';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/action';

    constructor(private http: Http) { }

    searchByCode(req?: any): Observable<Action> {
        return this.http.get(this.resourceSearchUrl + '/byCode/' + req)
            .map((res: Response) => {
                return res.json();
            });
    }

}
