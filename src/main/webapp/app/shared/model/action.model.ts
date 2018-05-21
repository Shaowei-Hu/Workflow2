import { Moment } from 'moment';

export interface IAction {
    id?: number;
    name?: string;
    manager?: string;
    date?: Moment;
    feedBackId?: number;
    clientId?: number;
}

export class Action implements IAction {
    constructor(
        public id?: number,
        public name?: string,
        public manager?: string,
        public date?: Moment,
        public feedBackId?: number,
        public clientId?: number
    ) {}
}
