package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @Operation(
            summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарии получены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )

            },
            tags = "Комментарии"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@Parameter(description = "id объявления") @PathVariable Integer id, Authentication authentication) {
        List<Comment> comments = commentService.getComments(id, authentication);
        CommentsDTO commentsDTO = new CommentsDTO();

        List<CommentDTO> listOfCommentDTO = comments.stream()
                .map(comment -> {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO = commentMapper.entityToDTO(comment);
                    return commentDTO;
                })
                .collect(Collectors.toList());

        commentsDTO.setCount(listOfCommentDTO.size());
        commentsDTO.setResults(listOfCommentDTO);

        if (commentsDTO != null) {
            return ResponseEntity.ok(commentsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Добавление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий добавлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            },
            tags = "Комментарии"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComments(@Parameter(description = "id объявления")
                                                  @PathVariable Integer id, @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO, Authentication authentication) {
        Comment comment = commentMapper.CreateOrUpdateCommentDTOToEntity(createOrUpdateCommentDTO);
        commentService.addComment(id, comment, authentication);


        return ResponseEntity.ok(commentMapper.entityToDTO(comment));

    }


    @Operation(
            summary = "Удаление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            },
            tags = "Комментарии"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@Parameter(description = "id объявления и комментария") @PathVariable Integer adId, @PathVariable Integer commentId, Authentication authentication) {

        commentService.deleteComment(adId, commentId, authentication);
    }

    @Operation(
            summary = "Обновление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий обновлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            },
            tags = "Комментарии"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> patchComment(@Parameter(description = "id объявления и комментария, + текст комментария")
                                                   @PathVariable Integer adId, @PathVariable Integer commentId,
                                                   @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                                   Authentication authentication) {

        Comment comment = commentMapper.CreateOrUpdateCommentDTOToEntity(createOrUpdateCommentDTO);
        commentService.patchComment(adId, commentId, comment.getText(), authentication);
        CommentDTO commentDTO = commentMapper.entityToDTO(comment);

        if (commentDTO != null) {
            return ResponseEntity.ok(commentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
}
