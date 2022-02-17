package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    Ticket first =new Ticket(1, 4993, "DME", "AER", 145);
    Ticket second =new Ticket(2, 4698, "DME", "AER", 140);
    Ticket third =new Ticket(3, 5685, "VKO", "AER", 145);
    Ticket fourth =new Ticket(4, 7169, "SVO", "AER", 165);
    Ticket fifth =new Ticket(5, 4320, "VKO", "AER", 150);
    Ticket sixth =new Ticket(6, 9112, "SVO", "AER", 170);

    @BeforeEach
    void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    void shouldAddToEmpty() {
        TicketRepository repository1 = new TicketRepository();
        TicketManager manager1 = new TicketManager(repository1);

        manager1.add(first);

        assertArrayEquals(new Ticket[]{first}, repository1.findAll());
    }

    @Test
    void shouldRemoveByIdWhenExist() {
        manager.removeById(3);

        assertArrayEquals(new Ticket[]{first, second, fourth, fifth, sixth}, repository.findAll());
    }

    @Test
    void shouldRemoveByIdWhenNotExist() {
        manager.removeById(7);

        assertArrayEquals(new Ticket[]{first, second, third, fourth, fifth, sixth}, repository.findAll());
    }

    @Test
    void shouldFindVKOAndSortByPrice() {

        assertArrayEquals(new Ticket[]{fifth, third}, manager.findAll("VKO", "AER"));
    }

    @Test
    void shouldFindSeveralAndSortByPrice() {

        assertArrayEquals(new Ticket[]{fifth, third, fourth, sixth}, manager.findAll("O", "AER"));
    }

    @Test
    void shouldNotFindIATAWithoutCrash() {

        assertArrayEquals(new Ticket[0], manager.findAll("MOW", "KUF"));
    }

    @Test
    void shouldNotFindIATAWhenOneNotExistWithoutCrash() {    //Тест написан для 100%-го покрытия по brnch'ам
        assertArrayEquals(new Ticket[0], manager.findAll("SVO", "KUF"));
    }

    @Test
    void shouldNotFindInEmptyWithoutCrash() {
        TicketRepository repository1 = new TicketRepository();
        TicketManager manager1 = new TicketManager(repository1);

        assertArrayEquals(new Ticket[0], manager1.findAll("SVO", "AER"));
    }

    @Test
    void shouldCompareIATAWhenMatches() {
        assertTrue(second.matches("DME", "AER"));
    }

    @Test
    void shouldCompareIATAWhenOneNotMatch() {
        assertFalse(second.matches("MOW", "AER"));
    }

    @Test
    void shouldCompareIATAWhenBothNotMatch() {
        assertFalse(second.matches("MOW", "KUF"));
    }
}