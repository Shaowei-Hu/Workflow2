import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Region } from '../../entities/region/region.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RegionService {

    private resourceUrl = SERVER_API_URL + 'api/region';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/region';

    constructor(private http: Http) { }

    searchByCode(req?: any): Observable<Region> {
        return this.http.get(this.resourceSearchUrl + '/byCode/' + req)
            .map((res: Response) => {
                return res.json();
            });
    }

}
