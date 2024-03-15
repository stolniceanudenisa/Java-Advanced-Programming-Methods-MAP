package ro.ubbcluj.scs.map.domain;

import java.time.LocalDateTime;

import ro.ubbcluj.scs.map.utils.Utils;

public class MessageTask extends Task{
    private String from;
    private String to;
    private LocalDateTime date;

    public MessageTask(Long id, String desc, String from, String to, LocalDateTime date) {
        super(id, desc);
        this.from=from;
        this.to=to;
        this.date=date;
    }
    @Override
    public void execute() {
        System.out.println("Taskul "+super.toString()+" a fost executat la data de:"+date.format(Utils.formatter));

    }
}
