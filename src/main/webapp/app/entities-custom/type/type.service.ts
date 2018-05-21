import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Type } from '../../entities/type/type.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TypeService {

    private resourceUrl = SERVER_API_URL + 'api/type';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/type';

    constructor(private http: Http) { }

    searchByCode(req?: any): Observable<Type> {
        return this.http.get(this.resourceSearchUrl + '/byCode/' + req)
            .map((res: Response) => {
                return res.json();
            });
    }

}
