import { ITeam } from './team.model';

export interface ITrader {
    id?: number;
    name?: string;
    teams?: ITeam[];
}

export class Trader implements ITrader {
    constructor(public id?: number, public name?: string, public teams?: ITeam[]) {}
}
