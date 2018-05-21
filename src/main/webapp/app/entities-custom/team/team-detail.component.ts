import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TeamService } from '../../entities/team/team.service';
import { Team } from '../../entities/team/team.model';

@Component({
    selector: 'team-detail',
    templateUrl: './team-detail.component.html',
    styleUrls: [
        'team.scss'
    ]
})
export class TeamDetailComponent implements OnInit, OnDestroy {

    @Input('team')
    team: Team;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private teamService: TeamService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        // this.subscription = this.route.params.subscribe((params) => {
        //     this.load(params['id']);
        // });
        this.registerChangeInTeam();
    }

    load(id) {
        this.teamService.find(id).subscribe((team) => {
            this.team = team;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        // this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTeam() {
        this.eventSubscriber = this.eventManager.subscribe(
            'teamListModification',
            (response) => this.load(this.team.id)
        );
    }
}
