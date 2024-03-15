package model;

import utils.Constants;
import java.time.LocalDateTime;

public class MessageTask extends Task {
    public String message;
    public String from;
    public String to;
    public LocalDateTime date;
    public MessageTask(String _taskId,
                       String _description,
                       String message,
                       String from,
                       String to,
                       LocalDateTime date
    ) {

        super(_taskId, _description);
        this.message = message;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public void execute() {
        System.out.println(message + " la " + date.format(Constants.DATE_TIME_FORMATTER));
    }
    @Override
    public String toString(){
        return super.toString() + " " + getMessage() + " " + getFrom() + " " + getTo() +
                date.format(Constants.DATE_TIME_FORMATTER);
    }
}
