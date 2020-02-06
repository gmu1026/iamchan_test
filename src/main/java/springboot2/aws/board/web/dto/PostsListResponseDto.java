package springboot2.aws.board.web.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import springboot2.aws.board.domain.posts.Posts;

@Getter
public class PostsListResponseDto {
	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedDate;
	
	public PostsListResponseDto(Posts entry) {
		this.id = entry.getId();
		this.title = entry.getTitle();
		this.author = entry.getAuthor();
		this.modifiedDate = entry.getModifiedDate();
	}
}
