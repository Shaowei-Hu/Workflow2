import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Team } from '../../entities/team/team.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TeamService {

    private resourceUrl = SERVER_API_URL + 'api/team';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/team';

    constructor(private http: Http) { }

    searchByCode(req?: any): Observable<Team> {
        return this.http.get(this.resourceSearchUrl + '/byCode/' + req)
            .map((res: Response) => {
                return res.json();
            });
    }

}
