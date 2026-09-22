package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
import com.back.shared.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post/posts")
public class ApiV1PostController {
    private final PostFacade postFacade;

    @GetMapping
    @Transactional(readOnly = true) // STUDY: 왜 컨트롤러에서 트랜잭션 선언하지?? 컨트롤러부터 트랜잭션이 열리는거라.. 상관없다..? Facade와 중첩되는 것 아닌가? 아니다. TDD할 때 유용하다.
    public List<PostDto> getItems() {
        return postFacade
                .findByOrderByIdDesc()
                .stream()
                .map(Post::toDto) // NOTE: new PostDto(post)랑 같다.
                .toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public PostDto getItem(@PathVariable int id){
        return postFacade
                .findById(id)
                .map(Post::toDto) // STUDY: 왜 여기서는 stream을 열지 않는가?
                .get();
    }
}
