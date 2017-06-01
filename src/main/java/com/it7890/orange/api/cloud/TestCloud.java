package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.avos.avoscloud.*;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Astro on 17/5/24.
 */
public class TestCloud {

	private static final Logger logger = LogManager.getLogger(AppAdvertCloud.class);

	@EngineFunction("updateTitlePic")
	public static String updateTitlePic(@EngineFunctionParam("skip") int skip, @EngineFunctionParam("limit") int limit) {
		List<AVObject> fileList = new ArrayList<>();
		try {
			AVQuery<AVObject> query = new AVQuery<>("_File");
			query.addAscendingOrder("createdAt");
			query.skip(skip);
			query.limit(limit);
			fileList.addAll(query.find());
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("article count: {}", fileList.size());

		List<AVObject> medias = new ArrayList<>();
		AVObject mediaInfo;
		for (AVObject avFile : fileList) {
			String imageUrl = avFile.getString("url");
			if (StringUtil.isNotEmpty(imageUrl)) {
				Map<String, Integer> map = getImageWidthHeight(imageUrl);
				if (null != map) {
					logger.info("Image url:{}, widht:{}, height:{}", imageUrl, map.get("width"), map.get("height"));
					mediaInfo = new AVObject("MediaInfo");
					mediaInfo.put("fileObj", AVObject.createWithoutData("_File", avFile.getObjectId()));
					mediaInfo.put("width", map.get("width"));
					mediaInfo.put("height", map.get("height"));
					medias.add(mediaInfo);
				} else {
					logger.info("文件不存在 fileId: {}", avFile.getObjectId());
				}
			}

			if (medias.size() == 100) {
				try {
					AVObject.saveAll(medias);
					medias.clear();
				} catch (AVException e) {
					e.printStackTrace();
				}
			}
		}

		if (medias.size() > 0) {
			try {
				AVObject.saveAll(medias);
				medias.clear();
			} catch (AVException e) {
				e.printStackTrace();
			}
		}


		return "success";
	}

	private static Map<String, Integer> getImageWidthHeight(String ImageUrl) {
		BufferedImage bufferedImage = null;
		try {
			URL url = new URL(ImageUrl);
			bufferedImage = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (null != bufferedImage) {
			Map<String, Integer> map = new HashMap<>();
			map.put("width", bufferedImage.getWidth());
			map.put("height", bufferedImage.getHeight());
			return map;
		} else {
			return null;
		}
	}
}
