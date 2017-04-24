package org.manuel.teambuilting.sports.listeners;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.messages.PlayerRegisteredEvent;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.sports.repositories.UserDataRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Manuel Doncel Martos
 * @since 17/04/2017.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@RabbitListenerTest(capture = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PlayerListenerTest {

    @Value("${messaging.amqp.player.exchange.name}")
    private String playerExchange;

    @Inject
    private PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

    @Inject
    private UserDataRepository userDataRepository;

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private RabbitListenerTestHarness harness;


    @Test
    public void playerRegisteredTest() throws InterruptedException {

        final PlayerRegisteredEvent event = PlayerRegisteredEvent.builder().userId("userId").playerId(new BigInteger("1")).build();

        rabbitTemplate.convertAndSend(playerExchange, PlayerRegisteredEvent.ROUTING_KEY, event);

        final RabbitListenerTestHarness.InvocationData data = harness.getNextInvocationDataFor(PlayerListener.LISTENER_ID, 5, TimeUnit.SECONDS);
        assertNotNull(data);
        assertEquals(1, data.getArguments().length);
        assertEquals(event, data.getArguments()[0]);
        assertEquals(1, userDataRepository.findAll().size());
    }

}
