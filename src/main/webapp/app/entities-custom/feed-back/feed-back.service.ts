import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { FeedBack } from '../../entities/feed-back/feed-back.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class FeedBackService {

    private resourceUrl = SERVER_API_URL + 'api/feed-back';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/feed-back';

    constructor(private http: Http) { }

    searchByCode(req?: any): Observable<FeedBack> {
        return this.http.get(this.resourceSearchUrl + '/byCode/' + req)
            .map((res: Response) => {
                return res.json();
            });
    }

}
