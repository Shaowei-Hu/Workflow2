import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrader } from 'app/shared/model/trader.model';

type EntityResponseType = HttpResponse<ITrader>;
type EntityArrayResponseType = HttpResponse<ITrader[]>;

@Injectable()
export class TraderService {
    private resourceUrl = SERVER_API_URL + 'api/traders';

    constructor(private http: HttpClient) {}

    create(trader: ITrader): Observable<EntityResponseType> {
        return this.http.post<ITrader>(this.resourceUrl, trader, { observe: 'response' });
    }

    update(trader: ITrader): Observable<EntityResponseType> {
        return this.http.put<ITrader>(this.resourceUrl, trader, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITrader>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITrader[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
