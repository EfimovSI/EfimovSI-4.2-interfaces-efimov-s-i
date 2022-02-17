package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.data.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketManager {
    private TicketRepository repository = new TicketRepository();

    public void add(Ticket ticket) {
        repository.add(ticket);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Ticket[] findAll(String from,String to) {
        Ticket[] result = new Ticket[0];
        for(Ticket ticket : repository.findAll()) {
            if (ticket.matches(from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}
