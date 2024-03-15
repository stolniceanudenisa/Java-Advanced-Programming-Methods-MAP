package Repo;

import Domain.Friendship;

import java.util.ArrayList;
import java.util.List;

public class RepoFriendship implements IRepo<Friendship> {
    List<Friendship> friendships;

    public RepoFriendship() {
        this.friendships = new ArrayList<>();
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    @Override
    public Friendship find_one(int id_to_find) {
        for (Friendship friendship : friendships) {
            if (friendship.getId_entity() == id_to_find)
                return friendship;
        }
        return null;
    }

    @Override
    public void add(Friendship entity) {
        this.friendships.add(entity);
    }

    @Override
    public void delete(int id_to_delete) {
        if (find_one(id_to_delete) != null) {
            friendships.remove(find_one(id_to_delete));
        }
    }
}

































