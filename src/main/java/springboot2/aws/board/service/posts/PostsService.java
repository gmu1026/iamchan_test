package springboot2.aws.board.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import springboot2.aws.board.domain.posts.Posts;
import springboot2.aws.board.domain.posts.PostsRepository;
import springboot2.aws.board.web.dto.PostsListResponseDto;
import springboot2.aws.board.web.dto.PostsResponseDto;
import springboot2.aws.board.web.dto.PostsSaveRequestDto;
import springboot2.aws.board.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository
				.findById(id)
				.orElseThrow(
						() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
		
		posts.update(requestDto.getTitle(), requestDto.getContent());
		
		return id;
	}
	
	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository
				.findById(id)
				.orElseThrow(
						() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
		
		return new PostsResponseDto(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
		
		postsRepository.delete(posts);
	}
}
