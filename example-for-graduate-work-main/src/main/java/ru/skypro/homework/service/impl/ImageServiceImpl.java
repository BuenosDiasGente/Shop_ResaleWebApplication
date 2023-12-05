package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.rmi.NotBoundException;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
    private final ImageRepository imageRepository;

    @Override
    public Image saveToDb(MultipartFile multipartFile) {
        log.info("ImageServiceImpl : ->saveToDb");

        Image image = new Image();
        try {
            image.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("ImageServiceImpl: saveToDb: 'multipartFile' IOException");
        }

        log.info("ImageServiceImpl : <- saveToDo");
        return imageRepository.save(image);
    }

    @Override
    public byte[] getById(Integer id) {
        log.info("ImageServiceImpl : ->getById");

            Image imageById = imageRepository.findImageById(id);

        //    log.error("ImageServiceImpl: getById: 'imageById' NotFoundException");

        log.info("ImageServiceImpl : <- getById");
        return imageById.getImage();
    }

}
