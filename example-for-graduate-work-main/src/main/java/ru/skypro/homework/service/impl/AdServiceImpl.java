package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
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
    private final AdMapper adMapper;
    private final UserRepository usersRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;
    private final ImageService imageService;

    @Override
    public AdsDTO getAllAds() {
        List<AdDTO> list = adMapper.adDTOToList(adRepository.findAll());
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setCount(list.size());
        adsDTO.setResults(list);
        return adsDTO;
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO ad,
                       MultipartFile image,
                       Authentication authentication) throws IOException {

        User user = usersRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Ad adN = adMapper.createOrUpdateAdDTOToEntity(ad);

        Image picture = imageService.saveToDb(image);

        adN.setImage(picture);
        adN.setAuthor(user);

        Ad savedAd = adRepository.save(adN);

        AdDTO adDTO = adMapper.entityToAdDTO(savedAd);

        return adDTO;
    }

    @Override
    public ExtendedAdDTO getAds(Integer id) {
        Ad ad = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));
        return adMapper.adToExtended(ad);
    }

    @Override
    public void removeAd(Integer id) {
        Ad ad = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));

        commentRepository.deleteCommentsByAdId(id);
        adRepository.deleteById(id);
        imageRepository.deleteById(ad.getImage().getId());

    }

    @Override
    public AdDTO updateAds(Integer id, CreateOrUpdateAdDTO ad, Authentication authentication) {
        User user = usersRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Ad adN = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));
        adN.setTitle(ad.getTitle());
        adN.setPrice(ad.getPrice());
        adN.setDescription(ad.getDescription());

        AdDTO adDTO = adMapper.entityToAdDTO(adN);
        return adDTO;
    }

    @Override
    public AdsDTO getAdsMe(Authentication authentication) {
        User user = usersRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        List<Ad> ads = adRepository.findAllByAuthor(user);

        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setCount(ads.size());
        adsDTO.setResults(adMapper.adDTOToList(ads));

        return adsDTO;
    }

    @Override
    public String updateImage(Integer id, MultipartFile image, Authentication authentication) throws IOException {
        usersRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Ad ad = adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));

        imageRepository.deleteById(ad.getImage().getId());

        Image newImage = imageService.saveToDb(image);

        ad.setImage(newImage);

        return adMapper.imageToString(newImage);
    }


    public Ad findAdById(Integer id){
        return  adRepository.findAdById(id).orElseThrow(() -> new AdNotFoundException("Ad not found"));
    }
}

