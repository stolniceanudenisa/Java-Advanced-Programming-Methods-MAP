public abstract class Task {
    private String task_id;
    private String descriere;

    public Task(String task_id, String descriere) {
        this.task_id = task_id;
        this.descriere = descriere;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getTask_id() {
        return task_id;
    }

    public String getDescriere() {
        return descriere;
    }

    @Override
    public void execute() {
        System.out.println("Id:" + task_id);
        System.out.println("Desriere:" + descriere);
    }

}
