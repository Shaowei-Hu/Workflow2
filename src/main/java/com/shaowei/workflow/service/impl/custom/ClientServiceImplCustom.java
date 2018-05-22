package com.shaowei.workflow.service.impl.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaowei.workflow.domain.Client;
import com.shaowei.workflow.domain.Trader;
import com.shaowei.workflow.repository.ClientRepository;
import com.shaowei.workflow.repository.custom.ClientRepositoryCustom;
import com.shaowei.workflow.service.ClientService;
import com.shaowei.workflow.service.custom.ClientServiceCustom;
import com.shaowei.workflow.service.custom.TraderServiceCustom;
import com.shaowei.workflow.service.dto.ClientDTO;
import com.shaowei.workflow.service.mapper.ClientMapper;

/**
 * Service Implementation for custom code of  Client.
 */
@Service
@Transactional
public class ClientServiceImplCustom implements ClientServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImplCustom.class);
    private final ClientRepository clientRepository;

    private final ClientRepositoryCustom clientRepositoryCustom;
    private final ClientService clientService;
    private final TraderServiceCustom traderServiceCustom;
    
    private final ClientMapper clientMapper;

    public ClientServiceImplCustom(ClientRepository clientRepository, ClientService clientService,
    ClientRepositoryCustom clientRepositoryCustom, TraderServiceCustom traderServiceCustom,
    ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientRepositoryCustom = clientRepositoryCustom;
        this.clientService = clientService;
        this.traderServiceCustom = traderServiceCustom;
        this.clientMapper = clientMapper;
    }

    /**
     * Save a client of current user.
     *
     * @param clientDTO the entity of current user to save
     * @return the persisted entity
     */
    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        log.debug("Request to save Client : {}", clientDTO);
        if(clientDTO.getId() == null){
            Trader trader = traderServiceCustom.findCurrentTrader();
            clientDTO.setTraderId(trader.getId());
        }
        ClientDTO clientDTOSaved = clientService.save(clientDTO);

        return  clientDTOSaved;
    }
    
    /**
     * Save a client.
     *
     * @param client the entity  to save
     * @return the persisted entity
     */
    @Override
    public Client save(Client client) {
        log.debug("Request to save Client : {}", client);
        return  clientRepository.save(client);
    }

    /**
     * Get all the clients of current user.
     *
     * @param pageable the pagination information
     * @return the list of entities of current user
     */
	@Override
	@Transactional(readOnly = true)
	public Page<ClientDTO> findClientsOfCurrentTrader(Pageable pageable) {
        log.debug("Request to get all Clients");
        Trader trader = traderServiceCustom.findCurrentTrader();
        return clientRepositoryCustom.findByTrader(trader, pageable)
            .map(clientMapper::toDto);
	}
	
    /**
     * Get all the clients of current user.
     *
     * @param pageable the pagination information
     * @return the list of entities of current user
     */
	@Override
	@Transactional(readOnly = true)
	public Page<ClientDTO> findClientsOfCurrentTraderTeam(Pageable pageable) {
        log.debug("Request to get all Clients");
        Trader trader = traderServiceCustom.findCurrentTrader();
        return clientRepositoryCustom.findByTraderNotAndTrader_Teams_Traders(trader, trader, pageable)
            .map(clientMapper::toDto);
	}
}
