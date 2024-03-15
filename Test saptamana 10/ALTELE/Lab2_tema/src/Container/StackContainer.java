package Container;

import Domain.Task;

public class StackContainer extends SuperClassContainer{
    public StackContainer() {
        super();
    }

    @Override
    public Task remove() {
        if(!isEmpty()){
            this.size = this.size-1;
            return this.elems[this.size];
        }
        else{
            return null;
        }
    }
}
