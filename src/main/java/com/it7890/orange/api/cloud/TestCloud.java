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

//	@EngineFunction("addPublications")
//	public static String addPublications() {
//		String names = "FHM,康健,非凡大探索,odigo,Looker,痞客邦,iPeen 愛評網,overdope,mydaily,nommagazine,開飯喇,Esquire,世界日報,迴紋針,Yanko Design,adaymagazine,柯夢波丹,Quartz,Tripadvisor,世界電影,Jezebel,開飯喇澳門,watchharry,nellydyu,柯夢波丹 香港,開飯喇香港,msn,AV No.1,Ikea,koreastardaily,紐約時報中文網,張曼娟小日子,Design Milk,食力,GQ,nmplus,KLOOK,GQ TW,yahoo,ELLE,cheers,國王車訊,OK GO,Gamme,BAZAAR,聯合新聞網,欣傳媒,icook,Lavie行動家,旅飯,路透社,狂熱球電影資訊網,DiGJAPAN,早安健康,Elle,天下雜誌,Zeenews,Hypebeast,India Time,HuffPost,情報局,vogue,UDN,旅遊雲,GQ JP";
//		String urls = "www.fhm.com.tw/sexlove,http://www.commonhealth.com.tw/masterChannel.action?uuid=1a5d9d94-3fbb-4b92-834c-19865a68b961,http://ustv.bobitag.com/site/subject_main,https://tw.odigo.jp/,http://www.nowlooker.com/nsfw.html,https://food.pixnet.net/category/26,http://www.ipeen.com.tw/,http://overdope.com/archives/category/footwear,http://china.mydaily.co.kr/new_yk/china/news_list.php?cate=992&chk=bun,https://nommagazine.com/topics/%E9%A3%B2%E9%A3%9F%E8%A7%80%E9%BB%9E-opinions/,https://tw.openrice.com/zh/taipei,https://www.esquire.tw/category/food-drink/,http://www.worldjournal.com/category/%E7%BE%8E%E9%A3%9F/,http://www.christabelle.idv.tw/,http://www.yankodesign.com/,http://www.adaymag.com/food-travel/food/,http://www.cosmopolitan.com.tw/fashion,https://qz.com/top/,https://www.tripadvisor.com.tw/Restaurants,http://www.worldscreen.com.tw/h/goods/1.html#tops,http://jezebel.com/tag/porn-industry,https://www.openrice.com/zh/macau,http://www.watchharry.com/?cat=32,http://nellydyu.tw/blog/post/40288747,http://www.cosmopolitan.com.hk/fashion,https://www.openrice.com/zh/hongkong,http://www.msn.com/zh-tw/autos,http://stno1.playno1.com/stno1/sk/,http://www.ikea.com/tw/zh/ideas/,http://www.koreastardaily.com/tc/magazine,https://cn.nytstyle.com/food-wine/zh-hant/,http://www.oneday.com.tw/category/objective/,http://design-milk.com/,http://www.foodnext.net/life/,http://www.gq.com.tw/life/food/,http://www.nmplus.hk/sport/,https://www.klook.com/zh-TW/blog/category/%E5%90%83%E8%B2%A8%E5%9C%B0%E5%9C%96/,http://www.gq.com.tw/women/sex/,https://tw.travel.yahoo.com/,http://www.elle.com.tw/lifestyle/gourment,http://www.cheers.com.tw/subChannel.action?idSubChannel=106,https://www.kingautos.net/all,http://okgo.tw/meal/,https://news.gamme.com.tw/category/tasty,http://www.harpersbazaar.com.tw/wishlist/gourmet/,https://udn.com/news/cindex/1013,http://solomo.xinmedia.com/gourmet,https://icook.tw/,https://www.wowlavie.com/life_unit.php?article_id=AE1602453,http://pantravel.life/archives/category/%E6%97%85%E9%A3%AF%E8%A6%8B,http://cn.reuters.com/life/technology,http://www.hypesphere.com/category/hs-weekly-2,https://digjapan.travel/zh_tw/list3/?fq%5B%5D=ext_col_04_strs_zh-tw%3A%28%E7%BE%8E%E9%A3%9F%29,https://www.everydayhealth.com.tw/category/46/index/1,http://www.elle.com.tw/lifestyle/design,http://www.cw.com.tw/subchannel.action?idSubChannel=397,http://zeenews.india.com/tags/porn.html,https://hypebeast.com/,http://timesofindia.indiatimes.com/,http://www.huffingtonpost.com/topic/pornography,https://kn.gamme.com.tw/category/fashion,https://www.vogue.com.tw/feature/foods/index.asp,https://health.udn.com/health/cate/5686,http://travel.ettoday.net/focus/%E7%BE%8E%E9%A3%9F%E5%9C%B0%E5%9C%96/,https://gqjapan.jp/life/food-restaurant";
//
//		String[] name_list = names.split(",");
//		String[] url_list = urls.split(",");
//
//		List<AVObject> publicationInfoList = new ArrayList<>();
//		int i = 0;
//		for (String name : name_list){
//			AVObject publicationInfo = new AVObject("con_publications");
//			if (name.equals("Esquire") || name.equals("GQ")) {
//				i++;
//				continue;
//			}
//			publicationInfo.put("description", name);
//			publicationInfo.put("name", name);
//			publicationInfo.put("url", url_list[i]);
//			publicationInfoList.add(publicationInfo);
//			i++;
//		}
//		try {
//			logger.info("publicationInfoList length: {}", publicationInfoList.size());
//			AVObject.saveAll(publicationInfoList);
//		} catch (AVException e) {
//			e.printStackTrace();
//		}
//
//		return "success";
//	}


//	@EngineFunction("updateArticle")
//	public static String updateArticle(@EngineFunctionParam("skip") int skip) {
//		try {
//			AVCloudQueryResult queryResult = AVQuery.doCloudQuery("select objectId from conarticle where grabListRuleObj = pointer('GrabListRule', '594b9b49728670006577054c') order by createdAt limit ?, 30", skip);
//			List<AVObject> articleList = (List<AVObject>) queryResult.getResults();
//			logger.info("articleList length: {}", articleList.size());
//
//			AVQuery<AVObject> query = AVQuery.getQuery("con_articles_content");
//			query.whereContainedIn("articleObj", articleList);
//			List<AVObject> acList = query.find();
//			logger.info("articleContentList length: {}", acList.size());
//
//			for (AVObject acObj : acList) {
//				String content = acObj.getString("content");
//
//				if (content.contains("<!--揪in bottom-->")) {
//					content = content.substring(0, content.indexOf("<!--揪in bottom-->"));
//					content += "</p>";
//				}
//				content = content.replaceAll("&nbsp;", "");
//				content = content.replaceAll("<br>", "");
//				acObj.put("content", content);
//			}
//			AVObject.saveAll(acList);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return "success";
//	}


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
		int index = 0;
		for (AVObject avFile : fileList) {
			index += 1;
			String imageUrl = avFile.getString("url");
			if (StringUtil.isNotEmpty(imageUrl)) {
				logger.info("imageUrl: {}, =====>{}", imageUrl, index);
				Map<String, Integer> map = getImageWidthHeight(imageUrl);
				if (null != map) {
//					logger.info("Image url:{}, widht:{}, height:{}", imageUrl, map.get("width"), map.get("height"));
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

	private static Map<String, Integer> getImageWidthHeight(String imageUrl) {
		BufferedImage bufferedImage = null;
		try {
			URL url = new URL(imageUrl);
			bufferedImage = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
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
