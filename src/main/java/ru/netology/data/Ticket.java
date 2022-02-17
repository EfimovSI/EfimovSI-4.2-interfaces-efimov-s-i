package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price;
    private String from;
    private String to;
    private int time;

    public boolean matches(String searchFrom, String searchTo) {
        return (this.from.contains(searchFrom) && this.to.contains(searchTo));
    }

    @Override
    public int compareTo(Ticket o) {
        return price - o.price;
    }
}
