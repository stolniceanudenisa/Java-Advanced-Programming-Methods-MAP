package Domain;

import Utils.Util;

import java.time.LocalDateTime;


public class MessageTask extends Task {
    private String from;
    private String to;
    private LocalDateTime date;


    public MessageTask(Long id, String desc, String from, String to, LocalDateTime date) {
        super(id, desc);
        this.from = from;
        this.to = to;
        this.date = date;
    }


    @Override
    public void execute() {
        System.out.println("Taskul "+super.toString()+" \n a fost executat la data de: "+date.format(Util.formatter));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
