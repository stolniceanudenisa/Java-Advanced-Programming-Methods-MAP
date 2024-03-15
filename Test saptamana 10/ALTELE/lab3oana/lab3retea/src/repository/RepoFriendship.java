package repository;

import domain.*;

import java.util.*;

public class RepoFriendship implements IRepository<Friendship> {
    List<Friendship> friendships;

    public RepoFriendship() {
        this.friendships = new ArrayList<>();
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    @Override
    public Friendship findOne(long aLong) {
        for (Friendship f : friendships) {
            long index = (long) f.getId();
            if (index == aLong)
                return f;
        }
        return null;
    }

    @Override
    public void add(Friendship entity) {
        this.friendships.add(entity);
    }

    @Override
    public void delete(long aLong) {
        Friendship f = findOne(aLong);
        if (friendships.contains(f)) {
            friendships.remove(f);
        }
    }
}
