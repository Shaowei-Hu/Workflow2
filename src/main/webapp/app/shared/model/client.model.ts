export interface IClient {
    id?: number;
    clientCode?: number;
    companyName?: string;
    location?: string;
    manager?: string;
    diagnostic?: string;
    companyNanme?: string;
    alert?: string;
    score?: number;
    businessSirteria1?: string;
    businessSirteria2?: string;
    businessData?: string;
    traderId?: number;
    regionName?: string;
    regionId?: number;
    typeName?: string;
    typeId?: number;
}

export class Client implements IClient {
    constructor(
        public id?: number,
        public clientCode?: number,
        public companyName?: string,
        public location?: string,
        public manager?: string,
        public diagnostic?: string,
        public companyNanme?: string,
        public alert?: string,
        public score?: number,
        public businessSirteria1?: string,
        public businessSirteria2?: string,
        public businessData?: string,
        public traderId?: number,
        public regionName?: string,
        public regionId?: number,
        public typeName?: string,
        public typeId?: number
    ) {}
}
