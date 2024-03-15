//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package container;

import model.Task;

public class StackContainer extends SuperContainer {
    public StackContainer() {}

    public Task remove() {
        if (!this.isEmpty()) {
            --this.size;
            return this.tasks[this.size];
        } else {
            return null;
        }
    }
}
