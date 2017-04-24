package org.manuel.teambuilting.sports.listeners;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.messages.PlayerDeletedEvent;
import org.manuel.teambuilting.messages.PlayerRegisteredEvent;
import org.manuel.teambuilting.sports.model.UserData;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.sports.repositories.UserDataRepository;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


/**
 * Listener for the player topic
 *
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${messaging.amqp.player.queue.name}",
            durable = "${messaging.amqp.player.queue.durable}", autoDelete = "${messaging.amqp.player.queue.autodelete}"),
        exchange = @Exchange(value = "${messaging.amqp.player.exchange.name}", type = ExchangeTypes.TOPIC,
            durable = "${messaging.amqp.player.exchange.durable}", autoDelete = "${messaging.amqp.player.exchange.autodelete}"),
        key = "${messaging.amqp.player.queue.binding}"))
@Component
@AllArgsConstructor
public class PlayerListener {

    public static final String LISTENER_ID = "SportPlayerListenerId";

    private final UserDataRepository userDataRepository;
    private final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

    @RabbitHandler
    public void handle(final PlayerRegisteredEvent event) {
        final UserData userData = UserData.builder().userId(event.getUserId()).playerId(event.getPlayerId()).build();
        userDataRepository.save(userData);
    }

    @RabbitHandler
    public void handle(final PlayerDeletedEvent event) {
        playerToTeamSportDetailsRepository.delete(playerToTeamSportDetailsRepository.findByPlayerId(event.getPlayerId()));
    }

}
