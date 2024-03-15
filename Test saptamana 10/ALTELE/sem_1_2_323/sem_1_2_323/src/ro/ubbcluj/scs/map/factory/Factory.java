package ro.ubbcluj.scs.map.factory;

import ro.ubbcluj.scs.map.container.Container;


public interface Factory {
    Container createContainer(Strategy startegy);
}
