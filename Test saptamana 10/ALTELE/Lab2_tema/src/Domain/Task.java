package Domain;

public abstract class Task{
    private Long id;
    private String desc;



    public abstract void execute();

    @Override
    public String toString() {
        return "Domain.Task{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }

    public Task(Long id, String desc){
        this.id = id;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
