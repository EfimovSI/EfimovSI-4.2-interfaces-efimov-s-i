package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.data.Ticket;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public void add(Ticket ticket) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        tmp[tmp.length - 1] = ticket;
        this.tickets = tmp;
    }

    public Ticket[] findAll() {
        return this.tickets;
    }

    public void removeById(int id) {
        int deleteIndex = -1;
        for (Ticket ticket : tickets) {
            deleteIndex++;
            if (ticket.getId() == id) {
                int index = 0;
                int i = 0;
                Ticket[] tmp = new Ticket[tickets.length - 1];
                for (Ticket ticket1 : tickets) {
                    if (index != deleteIndex) {
                        tmp[i] = ticket1;
                        i++;
                    }
                    index++;
                }
                this.tickets = tmp;
            }
        }
        return;
    }
}
