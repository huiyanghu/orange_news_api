package com.it7890.orange.api.dto;


import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.cloud.AppTopCloud;
import com.it7890.orange.api.dao.MediaInfoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ImageInfoDTO {
    private static Logger logger = LogManager.getLogger(ImageInfoDTO.class);
    private String ImageUrl;
    private int ImageWidth;
    private int ImageHeight;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getImageWidth() {
        return ImageWidth;
    }

    public void setImageWidth(int imageWidth) {
        ImageWidth = imageWidth;
    }

    public int getImageHeight() {
        return ImageHeight;
    }

    public void setImageHeight(int imageHeight) {
        ImageHeight = imageHeight;
    }

    public static List<ImageInfoDTO> buildImageInfoDTO(List<AVFile> titlePics){
        List<ImageInfoDTO> titlePicInfo = new ArrayList<ImageInfoDTO>();

        ImageInfoDTO imageInfoDTO = null;
        for (AVFile titlePic : titlePics) {
            imageInfoDTO = new ImageInfoDTO();
            logger.info("fileid===={}",titlePic.getObjectId());
            AVObject avoFile = new MediaInfoDao().getByFileId(titlePic.getObjectId());
            imageInfoDTO.setImageUrl(titlePic.getUrl());
            imageInfoDTO.setImageWidth(avoFile.getInt("width"));
            imageInfoDTO.setImageHeight(avoFile.getInt("height"));
            titlePicInfo.add(imageInfoDTO);
        }
        return titlePicInfo;
    }
}
