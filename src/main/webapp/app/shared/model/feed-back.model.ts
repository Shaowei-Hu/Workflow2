export interface IFeedBack {
    id?: number;
    question1?: string;
    question2?: string;
    question3?: string;
    question4?: string;
}

export class FeedBack implements IFeedBack {
    constructor(
        public id?: number,
        public question1?: string,
        public question2?: string,
        public question3?: string,
        public question4?: string
    ) {}
}
