import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Trader } from '../../entities/trader/trader.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TraderService {

    private resourceUrl = SERVER_API_URL + 'api/trader';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/trader';

    constructor(private http: Http) { }

    searchByCode(req?: any): Observable<Trader> {
        return this.http.get(this.resourceSearchUrl + '/byCode/' + req)
            .map((res: Response) => {
                return res.json();
            });
    }

}
