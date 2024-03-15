package Domain;

import jdk.jshell.execution.Util;

import java.time.LocalDateTime;


public class MessageTask extends Task {
        private String from;
        private String to;
        private String message;
        private LocalDateTime date;


    public MessageTask(Long id, String desc, String from, String to, String message, LocalDateTime date) {
        super(id, desc);
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = date;
    }


    @Override
    public void execute() {
        System.out.println("Mesajul:" + message);
        System.out.println("Trimis de:" + from);
        System.out.println("Catre: " + to);
        System.out.println("La data de: " + date.format(Util.formatter));
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
