import { ITrader } from './trader.model';

export interface ITeam {
    id?: number;
    name?: string;
    traders?: ITrader[];
}

export class Team implements ITeam {
    constructor(public id?: number, public name?: string, public traders?: ITrader[]) {}
}
