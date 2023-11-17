package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

      @Operation(summary = "Получение всех объявлений",
              responses = {
                      @ApiResponse(
                              responseCode = "200",
                              description = "Найдены и представлены все размещенные объявления",
                              content = @Content(
                                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                                      schema = @Schema(implementation = AdsDTO.class)
                              )
                      ),
                      @ApiResponse(
                              responseCode = "404",
                              description = "Объявления не найдены",

                              content = @Content(
                                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                                      schema = @Schema(implementation = AdsDTO.class)
                              )
                      )
              },
              tags = "Объявления"
      )
    @GetMapping
    public ResponseEntity<List<AdsDTO>> getAllAds() {
        List<AdsDTO> list = new ArrayList<>();
        return ResponseEntity.ok(list);
    }

      @Operation(
              summary = "Добавление объявления",
              responses = {
                      @ApiResponse(
                              responseCode = "200",
                              description = "Объявление добавлено",
                              content = @Content(
                                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                                      schema = @Schema(implementation = AdDTO.class)
                              )
                      )
              },
              tags = "Объявления",
              requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                      description = "Параметры нового объявления",
                      content = @Content(
                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                              schema = @Schema(implementation = AdDTO.class)
                      )
              )
      )
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestBody @Valid CreateOrUpdateAdDTO ad, @RequestParam MultipartFile image, Authentication authentication) throws IOException {
        return ResponseEntity.ok(new AdDTO());
    }

     @Operation(summary = "Получение информации об объявлении по id",
             responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Найдено и представлено размещенное объявление",
                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation = ExtendedAdDTO.class)
                             )
                     ),
                     @ApiResponse(
                             responseCode = "404",
                             description = "Объявление не найдено",

                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation = ExtendedAdDTO.class)
                             )
                     )
             },
             tags = "Объявления"
     )
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAdById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ExtendedAdDTO());
    }

     @Operation(summary = "Удаление объявления по id",
             responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Объявление удалено",
                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation = ExtendedAdDTO.class)
                             )
                     ),
                     @ApiResponse(
                             responseCode = "401",
                             description = "Несанкционированное действие"
                     ),
                     @ApiResponse(
                             responseCode = "403",
                             description = "Ограничение или Отсутствие доступа"
                     ),
                     @ApiResponse(
                             responseCode = "404",
                             description = "Объявление не найдено"
                     )
             },
             tags = "Объявления"
     )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAd(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

     @Operation(summary = "Обновление информации об объявлении",
             responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Объявление найдено и обновлено",
                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation = Ad.class)
                             )
                     ),
                     @ApiResponse(
                             responseCode = "401",
                             description = "Несанкционированное действие"
                     ),
                     @ApiResponse(
                             responseCode = "403",
                             description = "Ограничение или Отсутствие доступа"
                     ),
                     @ApiResponse(
                             responseCode = "404",
                             description = "Объявление не найдено"
                     )
             },
             tags = "Объявления"
     )
    @PatchMapping("/update/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable("id") Long id, @RequestBody @Valid CreateOrUpdateAdDTO ad, Authentication authentication) {
        return ResponseEntity.ok(new AdDTO());
    }


     @Operation(summary = "Получение объявлений авторизованного пользователя",
             responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Найдены и представлены размещенные объявления",
                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation = AdsDTO.class)
                             )
                     ),
                     @ApiResponse(
                             responseCode = "404",
                             description = "Пользователь не найден",

                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation = AdsDTO.class)
                             )
                     )
             },
             tags = "Объявления"
     )
    @GetMapping("/me")
    public ResponseEntity<List<AdsDTO>> getAllAdsByUser(Authentication authentication) {
        List<AdsDTO> list = new ArrayList<>();
        return ResponseEntity.ok(list);
    }

     @Operation(summary = "Обновление картинки объявления",
             responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Объявление найдено и обновлено",
                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(implementation = Image.class)
                             )
                     ),
                     @ApiResponse(
                             responseCode = "401",
                             description = "Несанкционированное действие"
                     ),
                     @ApiResponse(
                             responseCode = "403",
                             description = "Ограничение или Отсутствие доступа"
                     ),
                     @ApiResponse(
                             responseCode = "404",
                             description = "Объявление не найдено"
                     )
             },
             tags = "Объявления"
     )
    @PatchMapping("/update/{id}/image")
    public ResponseEntity<String> updateImageOfAd(@PathVariable("id") Long id, @NotNull @RequestParam MultipartFile image, Authentication authentication) throws IOException {
        return ResponseEntity.ok(new String());
    }
}
