package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private List<Post> posts = new CopyOnWriteArrayList<>();
    private AtomicLong countPosts = new AtomicLong(1);

    public List<Post> all() {
        return posts.stream()
                .filter(post -> !post.isReadyToRemoved())
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        Optional<Post> postOptional = posts.stream()
                .filter(post -> id == post.getId() && !post.isReadyToRemoved())
                .findAny();
        if (postOptional.isPresent()){
            return postOptional;
        } else {
            throw new NotFoundException();
        }
    }
    public Post save(Post post) {
        if (post.getId()==0){
            Post newPost = new Post(countPosts.get(), post.getContent());
            countPosts.incrementAndGet();
            posts.add(newPost);
            return newPost;
        } else {
            if (posts.size()==0){
                try {
                    throw new NotFoundException();
                } catch (NotFoundException e){
                    e.printStackTrace();
                }
            }
            for(Post post1 : posts){
                if (post1.getId()==post.getId() && !post1.isReadyToRemoved()){
                    post1.setContent(post.getContent());
                    return post1;
                } else {
                    try {
                        throw new NotFoundException();
                    } catch (NotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public void removeById(long id) {
        posts.stream()
                .filter(post -> post.getId()==id)
                .findFirst()
                .ifPresent(post -> post.setReadyToRemoved(true));
    }
}
