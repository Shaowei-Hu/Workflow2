import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Client } from '../../entities/client/client.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ClientService {

    private resourceUrl = SERVER_API_URL + 'api/client';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/client';

    constructor(private http: Http) { }

    searchByCode(req?: any): Observable<Client> {
        return this.http.get(this.resourceSearchUrl + '/byCode/' + req)
            .map((res: Response) => {
                return res.json();
            });
    }

}
