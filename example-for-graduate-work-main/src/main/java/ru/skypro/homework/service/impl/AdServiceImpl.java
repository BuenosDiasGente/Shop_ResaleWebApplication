package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdSMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdSMapper adSMapper;
    private final UserRepository usersRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    private final ImageService imageService;

    /**
     * Метод получения всех объявлений, размещенных на площадке
     *  @return AdsDTO
     */

    @Override
    public AdsDTO getAllAds() {
        List<AdDTO> list = adSMapper.adDTOToList(adRepository.findAll());
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setCount(list.size());
        adsDTO.setResults(list);
        return adsDTO;
    }

    /**
     * Метод добавления объявления. Доступен только зарегистрированным пользователям
     * @param ad
     * @param image
     * @param authentication
     * @return AdDTO
     * @throws IOException
     */

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO ad,
                       MultipartFile image,
                       Authentication authentication) throws IOException {

        User user = usersRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Ad adN = adSMapper.createOrUpdateAdDTOToEntity(ad);
        Image picture = imageService.saveToDb(image);
        adN.setImage(picture);
        adN.setAuthor(user);
        Ad savedAd = adRepository.save(adN);

        AdDTO adDTO = adSMapper.entityToAdDTO(savedAd);

        return adDTO;
    }

    /**
     * Метод получения расширенной информации по объявлению
     * @param id
     * @return ExtendedAdDTO
     */
    @Override
    public ExtendedAdDTO getAds(Integer id) {
        Ad ad = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));
        return adSMapper.adToExtended(ad);
    }

    /**
     * Метод удаления объявления. Доступен только админу и пользователю, разместившему объявление
     * @param id
     */
    @Override
    public void removeAd(Integer id) {
        Ad ad = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));

        commentRepository.deleteCommentsByAdId(id);
        adRepository.deleteById(id);
        imageRepository.deleteById(ad.getImage().getId());
    }

    /**
     * Метод обновления информации по объявлению. Доступен только админу и пользователю, разместившему объявление
     * @param id
     * @param ad
     * @return AdDTO
     */
    @Override
    public AdDTO updateAds(Integer id, CreateOrUpdateAdDTO ad) {
        Ad adN = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));
        adN.setTitle(ad.getTitle());
        adN.setPrice(ad.getPrice());
        adN.setDescription(ad.getDescription());
        adRepository.save(adN);

        return adSMapper.entityToAdDTO(adN);
    }

    /**
     * Метод получения всех размещенных пользователем на площадке объявлений
     * @param authentication
     * @return AdsDTO
     */
    @Override
    public AdsDTO getAdsMe(Authentication authentication) {
        User user = usersRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        List<Ad> ads = adRepository.findAllByAuthor(user);

        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setCount(ads.size());
        adsDTO.setResults(adSMapper.adDTOToList(ads));

        return adsDTO;
    }

    /**
     * Метод обновления картинки объявления. Доступен только админу и пользователю, разместившему объявление
     * @param id
     * @param image
     * @return String
     * @throws IOException
     */
    @Override
    public String updateImage(Integer id, MultipartFile image) throws IOException {
        Ad ad = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));

        Image imageN = imageService.saveToDb(image);
        ad.setImage(imageN);

        adRepository.save(ad);

        return adSMapper.imageToString(imageN);
    }
}
